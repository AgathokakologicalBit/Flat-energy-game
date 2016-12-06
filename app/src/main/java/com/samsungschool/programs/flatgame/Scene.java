package com.samsungschool.programs.flatgame;

import android.graphics.Canvas;

import java.util.ArrayList;

public class Scene {
    private ArrayList<Entity> entities;
    private ArrayList<Entity> entitiesAddQueue;
    private ArrayList<Entity> entitiesDestroyQueue;

    private Canvas canvas;

    private long lastTime;
    private float timeScale = 1;

    static Scene currentScene;

    private Scene()
    {
        this.entities = new ArrayList<>(100);
        this.entitiesAddQueue = new ArrayList<>(10);
        this.entitiesDestroyQueue = new ArrayList<>(10);

        this.timeScale = 1;
    }

    /**
     * Creates empty scene, and opens it
     * @return Instance of created scene
     */
    public static Scene openEmptyScene() { return Scene.currentScene = new Scene(); }

    /**
     * Changes current scene to passed
     * @param scene Target scene
     */
    public static void setScene(Scene scene) { Scene.currentScene = scene; }

    /**
     * Sets canvas to draw on
     * @param canvas Target canvas
     */
    void setCanvas(Canvas canvas) { this.canvas = canvas; }

    void update()
    {
        // Recalculate delta time
        long time = System.nanoTime() / 1_000_000;
        float deltaTime = (time - lastTime) / 1000f * timeScale;
        lastTime = time;

        // Remove all objects that should be destroyed
        for (Entity entity: this.entitiesDestroyQueue)
                this.entities.remove(entity);
        this.entitiesDestroyQueue.clear();

        // Add all objects, and then execute their 'start' function
        for (Entity entity : this.entitiesAddQueue) this.entities.add(entity);
        for (Entity entity : this.entitiesAddQueue) entity.start();
        this.entitiesAddQueue.clear();


        // Update all entities
        for (Entity entity : this.entities)
            if (entity.isActive())
                entity.update(deltaTime);

        // Execute postUpdate method on entities
        for (Entity entity : this.entities)
            if (entity.isActive())
                entity.postUpdate(deltaTime);

        // Execute standart gameobject's update function, to reacalculate it's position
        for (Entity entity : this.entities)
            if (entity.isActive() && entity instanceof GameObject)
                ((GameObject) entity).gameObjectUpdate(deltaTime);
    }

    void draw()
    {
        // Draw all active objects
        for (Entity entity: entities)
            if (entity.isActive() && entity instanceof DrawableObject)
                ((DrawableObject) entity).draw(canvas);
    }


    /**
     * Finds all objects on scene, that have certain type
     * @param objectClass Target object's class, that extends `Entity`
     * @param <T> Target class
     * @return ArrayList of found objects
     */
    public static <T extends Entity> ArrayList<T> findObjectsWithType(Class<T> objectClass)
    {
        ArrayList<T> objects = new ArrayList<>(currentScene.entities.size());

        for (Entity entity: currentScene.entities)
            if (entity.getClass() == objectClass)
                objects.add((T) entity);

        return objects;
    }

    /**
     * Creates new instance of Entity with given type,
     * and adds instantiated object to scene's `entities add queue`
     * @param entityType Type of entity to instantiate
     * @param <T> Type of entity
     * @return Instance of given class, that was added to scene
     */
    public static <T extends Entity> T instantiate(Class<T> entityType)
    {
        try {
            T e = entityType.newInstance();
            Scene.currentScene.entitiesAddQueue.add(e);
            return e;
        }
        catch (Exception ex) { ex.printStackTrace();  }

        return null;
    }

    /**
     * Destroys specified entity
     * @param entity Reference to target entity
     */
    public static void destroyEntity(Entity entity) { currentScene.entitiesDestroyQueue.add(entity); }

    /**
     * Returns current scene's time scale
     * @return Time scale of current scene
     */
    public static float getTimeScale() { return currentScene.timeScale; }

    /**
     * Sets times scale of current scene
     * @param timeScale New time scale
     */
    public static void setTimeScale(float timeScale) { currentScene.timeScale = Mathf.clamp(timeScale, 0, 1000); }


    /**
     * Touch listener, that gets touch position from view
     * @param touchPosition Position of touch in pixels
     */
    void onTouch(Vector2D touchPosition)
    {
        for (Entity entity: entities) {
            if (entity instanceof GameObject && entity instanceof TouchableObject)
            {
                Vector2D pos = ((GameObject) entity).getPosition();
                Vector2D scale = ((GameObject) entity).getScale();

                if(Mathf.pointInBox(touchPosition, pos.sub(scale.div(2)), scale))
                    ((TouchableObject) entity).onTouch(touchPosition);
            }
        }
    }
}

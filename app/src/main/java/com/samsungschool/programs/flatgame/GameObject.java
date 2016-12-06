package com.samsungschool.programs.flatgame;

/**
 * Default class for all objects on scene, that should be rendered
 * Extends Entity to get all standart methods, calling by `Scene`
 */
public abstract class GameObject extends Entity implements DrawableObject {
    /**
     * Current position in units as Vector2D
     */
    private Vector2D position = new Vector2D();
    /**
     * Current scale in units as Vector2D
     */
    private Vector2D scale = new Vector2D(25, 25);
    /**
     * Current rotation in radians
     */
    private float rotation = 0;

    /**
     * Current gameobject's velocity
     * Increasing position by it's value, multiplied on delta time, each frame
     */
    private Vector2D velocity = new Vector2D();

    final void gameObjectUpdate(float deltaTime) { this.position = this.position.add(velocity.mult(deltaTime)); }

    /**
     * Sets gameobject's position in game units(1 unit = 1 pixel)
     * @param position position in units
     */
    public final void setPosition(Vector2D position) { this.position = position.copy(); }

    /**
     * Sets gameobject's scale in game units(1 unit = 1 pixel)
     * @param scale scale in units
     */
    public final void setScale(Vector2D scale) { this.scale = scale; }

    /**
     * Sets gameobject's rotation in radians
     * @param rotation rotation in radians
     */
    public final void setRotation(float rotation) { this.rotation = rotation; }

    /**
     * Sets gameobject's velocity in game units(1 unit = 1 pixel)
     * @param velocity Velocity vector in units
     */
    public final void setVelocity(Vector2D velocity) { this.velocity = velocity; }


    /**
     * Returns current gameobject's position in units
     * @return position as Vector2D
     */
    public final Vector2D getPosition() { return this.position.copy(); }

    /**
     * Returns current gameobject's scale in units
     * @return scale as Vector2D
     */
    public final Vector2D getScale() { return this.scale.copy(); }

    /**
     * Returns current gameobject's rotation in radians
     * @return rotation in radians
     */
    public final float getRotation() { return this.rotation; }

    /**
     * Returns current gameobject's velocity in units
     * @return velocity as Vector2D
     */
    public final Vector2D getVelocity() { return this.velocity.copy(); }

    /**
     * Rotates object by given angle in radians
     * @param radians angle in radians
     */
    public final void rotateBy(float radians) { this.rotation += radians; }

    /**
     * Increase current gameobject's velocity by given acceleration
     * @param direction acceleration as Vector2D
     */
    public final void accelerate(Vector2D direction) { this.velocity = this.velocity.add(direction); }

    /**
     * Changes gameobject's position in units. (*Acts like `setPosition`)
     * @param position position as Vector2D
     */
    public final void moveTo(Vector2D position) { this.position = position.copy(); }

    /**
     * Increase current gameobject's position by given vector
     * @param direction direction as Vector2D
     */
    public final void moveBy(Vector2D direction) { this.position = this.position.add(direction); }

    /**
     * Creates new object on scene, and returns it's instance
     * @param entityType Class of entity
     * @param <T> Class of entity
     * @return Instance of object, that was added to scene
     */
    public static <T extends Entity> T instantiate(Class<T> entityType) { return Scene.instantiate(entityType); }

    /**
     * Destroys target entity from scene after update of all objects
     * @param entity target entity to destroy
     */
    public static void destroy(Entity entity) { Scene.destroyEntity(entity); }
}

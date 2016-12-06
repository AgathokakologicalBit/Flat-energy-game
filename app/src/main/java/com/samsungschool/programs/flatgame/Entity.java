package com.samsungschool.programs.flatgame;

public abstract class Entity {
    private boolean _isActive = true;

    public void start() {}
    public void update(float deltaTime) {}
    public void postUpdate(float deltaTime) {}

    public final void setActive(boolean isActive) { this._isActive = isActive; }
    public final boolean isActive() { return this._isActive; }
}

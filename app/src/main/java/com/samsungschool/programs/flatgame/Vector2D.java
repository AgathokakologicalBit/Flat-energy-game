package com.samsungschool.programs.flatgame;

import java.util.Locale;

public class Vector2D {
    public float x, y;

    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2D(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D v)
    {
        this.x = v.x;
        this.y = v.y;
    }

    public Vector2D add(final Vector2D v) { return new Vector2D(this.x + v.x, this.y + v.y); }
    public Vector2D add(float x, float y) { return new Vector2D(this.x + x, this.y + y); }
    public Vector2D add(float n) { return new Vector2D(this.x + n, this.y + n); }

    public Vector2D sub(final Vector2D v) { return new Vector2D(this.x - v.x, this.y - v.y); }
    public Vector2D sub(float x, float y) { return new Vector2D(this.x - x, this.y - y); }
    public Vector2D sub(float n) { return new Vector2D(this.x - n, this.y - n); }

    public Vector2D mult(final Vector2D v) { return new Vector2D(this.x * v.x, this.y * v.y); }
    public Vector2D mult(float x, float y) { return new Vector2D(this.x * x, this.y * y); }
    public Vector2D mult(float n) { return new Vector2D(this.x * n, this.y * n); }

    public Vector2D div(final Vector2D v) { return new Vector2D(this.x / v.x, this.y / v.y); }
    public Vector2D div(float x, float y) { return new Vector2D(this.x / x, this.y / y); }
    public Vector2D div(float n) { return new Vector2D(this.x / n, this.y / n); }

    public float getAngle() { return (float) Math.atan2(this.y, this.x); }
    public float getLength() { return (float) Math.sqrt(this.x*this.x + this.y*this.y); }
    public float getLengthSquared() { return this.x*this.x + this.y * this.y; }

    public Vector2D copy() { return new Vector2D(this.x, this.y); }

    public Vector2D rotated(float theta)
    {
        float angle = getAngle();
        float length = getLength();
        final float TWO_PI = (float) (Math.PI * 2);

        float newAngle = (TWO_PI + angle + theta % TWO_PI) % TWO_PI;

        return new Vector2D(
                (float) Math.cos(newAngle) * length,
                (float) Math.sin(newAngle) * length
        );
    }

    public Vector2D normalized()
    {
        float length = getLength();
        if (length == 0) return new Vector2D();

        return new Vector2D(this.x / length, this.y / length);
    }

    @Override
    public String toString() { return String.format(Locale.getDefault(), "(%.2f, %.2f)", this.x, this.y); }
}

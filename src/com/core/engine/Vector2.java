package com.core.engine;

public class Vector2 {

    public float X;
    public float Y;

    public Vector2(float X,float Y)
    {
        this.X = X;
        this.Y = Y;
    }

    public String ToString()
    {
        return "("+ X + " " + Y + ")";
    }

    public float getX() {
        return X;
    }

    public void getX(float x) {
        X = x;
    }

    public float getY() {
        return Y;
    }

    public void SetY(float y) {
        Y = y;
    }


    public boolean IsDifferent(Vector2 vec)
    {
    if(vec.X != X || vec.Y != Y)
    {
        return  true;
    }
    return false;
    }

    public  float Length()
    {
        return (float)Math.sqrt(X * X + Y * Y);
    }

    public float dot(Vector2 v)
    {
        return  X * v.getX() + Y * v.getY();
    }

    public Vector2 normalize()
    {
        float length = Length();
        X /= length;
        Y /= length;

        return this;

    }

    public Vector2 Rotate(float angle)
    {
        double rad = Math.toRadians(angle);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);

        return new Vector2((float)(X * cos - Y*sin),(float)(X*sin + Y*cos ));
    }

    public Vector2 Add(Vector2 v)
    {
        return new Vector2(X+v.getX(),Y+v.getY());
    }
    public Vector2 Add(float v)
    {
        return new Vector2(X+v,Y+v);
    }

    public Vector2 Sub(Vector2 v)
    {
        return new Vector2(X-v.getX(),Y-v.getY());
    }
    public Vector2 Sub(float v)
    {
        return new Vector2(X-v,Y-v);
    }


    public Vector2 Mul(Vector2 v)
    {
        return new Vector2(X*v.getX(),Y*v.getY());
    }
    public Vector2 Mul(float v)
    {
        return new Vector2(X*v,Y*v);
    }


    public Vector2 Div(Vector2 v)
    {
        return new Vector2(X/v.getX(),Y/v.getY());
    }
    public Vector2 Div(float v)
    {
        return new Vector2(X/v,Y/v);
    }



}

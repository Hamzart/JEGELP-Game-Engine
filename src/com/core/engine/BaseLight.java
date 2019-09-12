package com.core.engine;


public class BaseLight
{
    private Vector3 color;
    private float intensity;

    public BaseLight(Vector3 color, float intensity)
    {
        this.color = color;
        this.intensity = intensity;
    }

    public Vector3 getColor()
    {
        return color;
    }

    public void setColor(Vector3 color)
    {
        this.color = color;
    }

    public float getIntensity()
    {
        return intensity;
    }

    public void setIntensity(float intensity)
    {
        this.intensity = intensity;
    }
}
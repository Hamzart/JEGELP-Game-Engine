package com.core.engine;
//package com.core.engine.BaseLight;



public class DirectionalLight
{
    private BaseLight base;
    private Vector3 direction;

    public DirectionalLight(BaseLight base, Vector3 direction)
    {
        this.base = base;
        this.direction = direction.normalized();
    }

    public BaseLight getBase()
    {
        return base;
    }

    public void setBase(BaseLight base)
    {
        this.base = base;
    }

    public Vector3 getDirection()
    {
        return direction;
    }

    public void setDirection(Vector3 direction)
    {
        this.direction = direction;
    }
}
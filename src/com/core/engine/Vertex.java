package com.core.engine;


public class Vertex
{
    public static final int SIZE = 8;

    private Vector3 pos;
    private Vector2 texCoord;
    private Vector3 normal;

    public Vertex(Vector3 pos)
    {
        this(pos, new Vector2(0,0));
    }

    public Vertex(Vector3 pos, Vector2 texCoord)
    {
        this(pos, texCoord, new Vector3(0,0,0));
    }

    public Vertex(Vector3 pos, Vector2 texCoord, Vector3 normal)
    {
        this.pos = pos;
        this.texCoord = texCoord;
        this.normal = normal;
    }

    public Vector3 getPos()
    {
        return pos;
    }

    public void setPos(Vector3 pos)
    {
        this.pos = pos;
    }

    public Vector2 getTexCoord()
    {
        return texCoord;
    }

    public void setTexCoord(Vector2 texCoord)
    {
        this.texCoord = texCoord;
    }

    public Vector3 getNormal()
    {
        return normal;
    }

    public void setNormal(Vector3 normal)
    {
        this.normal = normal;
    }
}
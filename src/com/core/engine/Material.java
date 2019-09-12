package com.core.engine;



public class Material {

    private Texture texture;
    private Vector3 color;


    public Material(Texture texture, Vector3 color)
    {
        this.texture = texture;
        this.color = color;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Vector3 getColor() {
        return color;
    }

    public void setColor(Vector3 color) {
        this.color = color;
    }
}

package com.core.engine;

public class BasicShader extends Shader
{
    public static final BasicShader instance = new BasicShader();

    public static BasicShader getInstance()
    {
        return instance;
    }

    public BasicShader()
    {
        super();

        addVertexShader(ResourceLoader.loadShader("basicVertex.shader"));
        addFragmentShader(ResourceLoader.loadShader("basicFragment.shader"));
        compileShader();

        addUniform("transform");
        addUniform("color");
    }

    public void updateUniforms(Matrix4x4 worldMatrix, Matrix4x4 projectedMatrix, Material material)
    {
        if(material.getTexture() != null)
            material.getTexture().bind();
        else
            RenderUtility.unbindTextures();

        setUniform("transform", projectedMatrix);
        setUniform("color", material.getColor());

    }
}
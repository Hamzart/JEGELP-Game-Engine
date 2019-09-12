package com.core.engine;


public class PhongShader extends Shader
{
    public static final PhongShader instance = new PhongShader();

    public static PhongShader getInstance()
    {
        return instance;
    }

    private static Vector3 ambientLight = new Vector3(0.1f,0.1f,0.1f);
    private static DirectionalLight directionalLight = new DirectionalLight(new BaseLight(new Vector3(0,0,0), 0), new Vector3(0,0,0));

    public PhongShader()
    {
        super();

        addVertexShader(ResourceLoader.loadShader("phongVertex.shader"));
        addFragmentShader(ResourceLoader.loadShader("phongFragment.shader"));
        compileShader();

        addUniform("transform");
        addUniform("transformProjected");
        addUniform("baseColor");
        addUniform("ambientLight");

        addUniform("directionalLight.base.color");
        addUniform("directionalLight.base.intensity");
        addUniform("directionalLight.direction");
    }

    public void updateUniforms(Matrix4x4 worldMatrix, Matrix4x4 projectedMatrix, Material material)
    {
        if(material.getTexture() != null)
            material.getTexture().bind();
        else
            RenderUtility.unbindTextures();

        setUniform("transformProjected", projectedMatrix);
        setUniform("transform", worldMatrix);
        setUniform("baseColor", material.getColor());
        setUniform("ambientLight", ambientLight);
        setUniform("directionalLight", directionalLight);
    }

    public static Vector3 getAmbientLight()
    {
        return ambientLight;
    }

    public static void setAmbientLight(Vector3 ambientLight)
    {
        PhongShader.ambientLight = ambientLight;
    }

    public static void setDirectionalLight(DirectionalLight directionalLight)
    {
        PhongShader.directionalLight = directionalLight;
    }

    public void setUniform(String uniformName, BaseLight baseLight)
    {
        setUniform(uniformName + ".color", baseLight.getColor());
        setUniformf(uniformName + ".intensity", baseLight.getIntensity());
    }

    public void setUniform(String uniformName, DirectionalLight directionalLight)
    {
        setUniform(uniformName + ".base", directionalLight.getBase());
        setUniform(uniformName + ".direction", directionalLight.getDirection());
    }
}
package com.core.engine;

public class Transform {

    private static Camera camera;

    private static  float nearClipping;
    private static  float farClipping;
    private static  float width;
    private static  float height;
    private static  float fov;



    // private Vector3 Position;
   // private Vector3 Rotation;
   // private Vector3 Scale;
    private  Vector3 translation;
    private  Vector3 rotation;
    private  Vector3 scale;



    public Transform()
    {
        translation = new Vector3(0,0,0);
        rotation = new Vector3(0,0,0);
        scale = new Vector3(0,0,0);


    }


    public Matrix4x4 getTransformation()
    {
        Matrix4x4 translationMatrix = new Matrix4x4().initTranslation(translation.getX(),translation.getY(),translation.getZ());
        Matrix4x4 rotationMatrix = new Matrix4x4().initRotation(rotation.getX(),rotation.getY(),rotation.getZ());
        Matrix4x4 scaleMatrix = new Matrix4x4().initScale(scale.getX(),scale.getY(),scale.getZ());

        return translationMatrix.mul(rotationMatrix.mul(scaleMatrix));
    }

    public Vector3 getTranslation() {

        return translation;
    }

    public void setTranslation(Vector3 translation) {


        this.translation = translation;
    }

    public void setTranslation(float x , float y, float z) {


        this.translation = new Vector3(x,y,z);
    }

    public String toString()
    {


        return translation.getX()+" "+translation.getY()+" "+translation.getZ();
    }

    public Vector3 getRotation()
    {
        return rotation;
    }

    public void setRotation(Vector3 rotation)
    {
        this.rotation = rotation;
    }

    public void setRotation(float x,float y ,float z)
    {
        this.rotation = new Vector3(x,y,z);
    }

    public Vector3 getScale()
    {
        return scale;
    }

    public void setScale(Vector3 scale)
    {
        this.scale = scale;
    }

    public void setScale(float x,float y ,float z) {
        this.scale = new Vector3(x,y,z);
    }


    public  Matrix4x4 getProjectedTransformation()
    {
        Matrix4x4 transformationMatrix = getTransformation();
        Matrix4x4 projectionMatrix = new Matrix4x4().initProjection(fov,width,height,nearClipping,farClipping);
        Matrix4x4 cameraRotation = new Matrix4x4().initCamera(camera.getForward(),camera.getUp());
        Matrix4x4 cameraTranslation = new Matrix4x4().initTranslation(-camera.getPosition().getX(),-camera.getPosition().getY(),-camera.getPosition().getZ());

        return projectionMatrix.mul(cameraRotation.mul(cameraTranslation.mul(transformationMatrix)));

    }

    public static void setProjection(float fov,float width,float height,float nearClipping,float farClipping)
    {
        Transform.fov = fov;
        Transform.width = width;
        Transform.height = height;
        Transform.nearClipping = nearClipping;
        Transform.farClipping = farClipping;


    }

    public static Camera getCamera() {
        return camera;
    }

    public static void setCamera(Camera camera) {
        Transform.camera = camera;
    }
}

package com.core.engine;

public class Quaternion {


    private float X;
    private float Y;
    private float Z;
    private float W;


    public Quaternion(float x,float y,float z,float w)
    {
      this.X = x;
      this.Y = y;
      this.Z = z;
      this.W = w;
    }


    public float length()
    {
        return (float)Math.sqrt(X * X + Y * Y + Z * Z + W * W);
    }

    public Quaternion normalize()
    {
        float length = length();
        X /= length;
        Y /= length;
        Z /= length;
        W /= length;

        return this;
    }
    public Quaternion inverse()
    {
        return new Quaternion(-X,-Y,-Z,W);
    }
    public Quaternion mul(Quaternion q)
    {
        float w = W * q.getW() - X * q.getX() - Y * q.getY() - Z * q.getZ();
        float x = X * q.getW() + W * q.getX() + Y * q.getZ() - Z * q.getY();
        float y = Y * q.getW() + W * q.getY() + Z * q.getX() - X * q.getZ();
        float z = Z * q.getW() + W * q.getZ() + X * q.getY() - Y * q.getX();

        return new Quaternion(x,y,z,w);
    }

    public Quaternion mul(Vector3 v)
    {
        float w = -X * v.getX() - Y * v.getY() - Z * v.getZ();
        float x =  W * v.getX() + Y * v.getZ() - Z * v.getY();
        float y =  W * v.getY() + Z * v.getX() - X * v.getZ();
        float z =  W * v.getZ() + X * v.getY() - Y * v.getX();

        return  new Quaternion(x,y,z,w);
    }

    public float getX() {
        return X;
    }

    public void setX(float x) {
        X = x;
    }

    public float getY() {
        return Y;
    }

    public void setY(float y) {
        Y = y;
    }

    public float getZ() {
        return Z;
    }

    public void setZ(float z) {
        Z = z;
    }

    public float getW() {
        return W;
    }

    public void setW(float w) {
        W = w;
    }


}

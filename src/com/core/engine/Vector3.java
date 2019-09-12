package com.core.engine;

public class Vector3 {

    public float X;
    public float Y;
    public float Z;

    public Vector3(float X,float Y,float Z)
    {
        this.X = X;
        this.Y = Y;
        this.Z = Z;
    }

    public String ToString()
    {
        return "("+ X + " " + Y + " " + Z + ")";
    }

    public float getX() {
        return X;
    }

    public void SetX(float x) {
        X = x;
    }

    public float getY() {
        return Y;
    }

    public void SetY(float y) {
        Y = y;
    }

    public float getZ() {
        return Z;
    }

    public void SetZ(float z) {
        Z = z;
    }


    public boolean isDifferent(Vector3 vec)
    {
        if((vec.X != X ) || (vec.Y != Y) ||( vec.Z != Z))
        {
            return  true;
        }
        return false;
    }

    public  float length()
    {
        return (float)Math.sqrt(X * X + Y * Y + Z * Z);
    }

    public float dot(Vector3 v)
    {
        return  X * v.getX() + Y * v.getY() + Z * v.getZ();
    }

    public Vector3 normalized()
    {

        float length = length();
        return new Vector3(X/length,Y/length,Z/length);

    }

    public  Vector3 cross(Vector3 v)
    {
        float x = Y*v.getZ() - Z*v.getY();
        float y = Z*v.getX() - X*v.getZ();
        float z = X*v.getY() - Y*v.getX();

        return new Vector3(x,y,z);
    }

    public Vector3 rotate(float angle,Vector3 axis)
    {
        float sinHalfAngle = (float)Math.sin(Math.toRadians(angle /2));
        float cosHalfAngle = (float)Math.cos(Math.toRadians(angle /2));

        float rx = axis.getX()*sinHalfAngle;
        float ry= axis.getY()*sinHalfAngle;
        float rz= axis.getZ()*sinHalfAngle;
        float rw=cosHalfAngle;

        Quaternion rotation = new Quaternion(rx,ry,rz,rw);
        Quaternion inverse = rotation.inverse();
        //Quaternion w = rotation.mul(this).mul(inverse);
        Quaternion w = rotation.mul(this).mul(inverse);
        X = w.getX();
        Y = w.getY();
        Z = w.getZ();
        return this;
    }

    public Vector3 add(Vector3 v)
    {
        return new Vector3(X+v.getX(),Y+v.getY(),Z+v.Z);
    }
    public Vector3 add(float v)
    {
        return new Vector3(X+v,Y+v,Z+v);
    }

    public Vector3 sub(Vector3 v)
    {
        return new Vector3(X-v.getX(),Y-v.getY(),Z-v.getZ());
    }
    public Vector3 sub(float v)
    {
        return new Vector3(X-v,Y-v,Z-v);
    }


    public Vector3 mul(Vector3 v)
    {
        return new Vector3(X*v.getX(),Y*v.getY(),Z*v.getZ());
    }
    public Vector3 mul(float v)
    {
        return new Vector3(X*v,Y*v,Z*v);
    }


    public Vector3 div(Vector3 v)
    {
        return new Vector3(X/v.getX(),Y/v.getY(),Z/v.Z);
    }
    public Vector3 div(float v)
    {
        return new Vector3(X/v,Y/v,Z/v);
    }

    public Vector3 abs()
    {
        return new Vector3(Math.abs(X),Math.abs(Y),Math.abs(Z));
    }


}

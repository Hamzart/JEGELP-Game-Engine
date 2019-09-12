package com.core.engine;

//import org.lwjgl.input.Keyboard;

public class Camera {

    private static  final Vector3 yAxis = new Vector3(0,1,0);
    private Vector3 position;
    private Vector3 forward;
    private Vector3 up;

    public static Vector2 centerPosition = new Vector2(Window.getWidth()/2, Window.getHeight()/2);
    boolean mouseLocked = false;


    public Camera()
    {
        this(new Vector3(0,0,0),new Vector3(0,0,1),new Vector3(0,1,0));
    }
    public Camera(Vector3 position,Vector3 forward,Vector3 up)
    {
        this.position = position;
        this.forward = forward;
        this.up = up;

        up = up.normalized();
        forward= forward.normalized();
    }




    public void Input ()
    {

        float sensitivity = 0.5f;
        if(mouseLocked)
        {
            if(Input.getKey(Input.KEY_ESCAPE))
            {
                System.out.println("key clicked");

                Input.SetCursor(true);
                mouseLocked = false;

            }

            Vector2 deltaPos = Input.GetMousePosition().Sub(centerPosition);

            boolean rotY = deltaPos.getX() != 0;
            boolean rotX = deltaPos.getY() != 0;

            if(rotY)
                rotateY(deltaPos.getX() * sensitivity);
            if(rotX)
                rotateX(-deltaPos.getY() * sensitivity);

            if(rotY || rotX)
                Input.SetMousePosition(new Vector2(Window.getWidth()/2, Window.getHeight()/2));
        }

        if(Input.getMouse(0))
        {
            System.out.println("Mouse clicked");
            Input.SetMousePosition(centerPosition);
            Input.SetCursor(false);
            mouseLocked = true;
        }

    }


    public void rotateY(float angle)
    {

        Vector3 Haxis = forward.cross(yAxis).normalized();
        //Haxis.normalized();

        forward = forward.rotate(angle,yAxis).normalized();


        up = Haxis.cross(forward).normalized();
        //up.normalized();
    }

    public void rotateX(float angle)
    {
        Vector3 Haxis = yAxis.cross(forward).normalized();
        //Haxis.normalized();

        forward = forward.rotate(angle,Haxis).normalized();
       // forward.normalized();

        up = forward.cross(Haxis).normalized();
       // up.normalized();
    }

    public  Vector3 getLeft()
    {
        Vector3 left = forward.cross(up).normalized();
        return left;
    }

    public  Vector3 getRight()
    {
        Vector3 right = up.cross(forward).normalized();
        return right;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public Vector3 getUp() {
        return up;
    }

    public void setUp(Vector3 up) {
        this.up = up;
    }

    public Vector3 getForward() {
        return forward;
    }

    public void setForward(Vector3 forward) {
        this.forward = forward;
    }

    public void move(Vector3 direction, float distance)
    {
        position = position.add(direction.mul(distance));
    }



}

package com.core.engine;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;


public class Engine {

    public static int width = 800;
    public static int height = 600;
    public static String title = "3D Engine";

    private static  boolean isPlaying = false;
    private static  double FRAME_CAP = 5000.0;

    private int frameRate = 0;
    private double frameCounter = 0;
    private long lastTime = Time.getTime();
    private double unprocessedTime = 0;
    final double frameTime = 1.0/FRAME_CAP;
    private Game game;

    // CoreEngine Method
    public Engine()
    {
        RenderUtility.InitGraphics();
        isPlaying = false;
        game = new Game();
    }

    //  Starting the Engine

    public  void Start()
    {
        System.out.println(RenderUtility.OpenglVersion());
        if(isPlaying)
            return;
        isPlaying = true;
        Update();
    }

    //  Update method

    public void Update()
    {


        while(isPlaying)
        {
            boolean render = false;
            long startTime = Time.getTime();
            long passedTime = startTime - lastTime;
            lastTime = startTime;
            unprocessedTime += passedTime / (double)Time.SECOND;

            while(unprocessedTime > frameTime)
            {
                render = true;
                unprocessedTime -= frameTime;
                frameCounter += passedTime;

                if(Window.isCloseRequested())
                    Exit();

                Time.setDelta(frameTime);
                Input.update();

                // TODO: Update Game

                game.Input();
                game.Update();

                if(frameCounter >= Time.SECOND )
                {
                    System.out.println(frameRate);
                    frameRate = 0;
                    frameCounter = 0;

                }
            }



            if(render)
            {
                Render();
                frameRate++;
            }
            else
            {
                try
                {
                    Thread.sleep(1);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }


        }
        Clean();
    }

    // Closing the Engine

    public  void Exit()
    {
       // System.out.print("is Exeting");
        if(!isPlaying)
            return;
        isPlaying = false;
    }

    // Rendering method
    private  void Render()
    {
        RenderUtility.ClearScreen();
        game.Render();
        Window.render();
    }

    // Cleaning

    public  void Clean()
    {
         Window.clean();
        Keyboard.destroy();
        Mouse.destroy();
    }



    // Main Entry Point
    public static void main(String[]args)
    {
        Window.createWindow(width,height,title);
        Engine mygame = new Engine();
        mygame.Start();
    }
}

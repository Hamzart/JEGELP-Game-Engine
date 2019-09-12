package com.core.engine;

public class Time {

    private static double delta;
    public static final long SECOND = 1000000000L;


    public static long getTime()
    {
        return System.nanoTime();
    }

    public static double getDelta()
    {
        return delta;
    }
    public static void setDelta(double delta)
    {
        Time.delta = delta;
    }
}

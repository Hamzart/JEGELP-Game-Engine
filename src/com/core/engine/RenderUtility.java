package com.core.engine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;
public class RenderUtility {

    public static void ClearScreen()
    {
        //TODO: Stencil Buffer
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

    }


    public static void InitGraphics()
    {
        glClearColor(0.0f,0.0f,0.0f,0.0f);

        glFrontFace(GL_CW);
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);
        glEnable(GL_DEPTH_TEST);

        // TODO: Clamp for later

        glEnable(GL_TEXTURE_2D);
        //glEnable(GL_FRAMEBUFFER_SRGB);
    }

    public static String OpenglVersion()
    {
        return glGetString(GL_VERSION);
    }


    public static void setTexture(boolean enabled)
    {
        if(enabled)
            glEnable(GL_TEXTURE_2D);
        else
            glDisable(GL_TEXTURE_2D);
    }

    public static void setClearColor(Vector3 v)
    {
        glClearColor(v.getX(),v.getY(),v.getZ(),1.0f);
    }

    public static void unbindTextures()
    {
        glBindTexture(GL_TEXTURE_2D,0);
    }
}

package com.core.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.opengl.TextureLoader;

public class ResourceLoader {

    public static String loadShader(String fileName)
    {
        StringBuilder shaderSource = new StringBuilder();
        BufferedReader shaderReader = null;

        try
        {
            shaderReader = new BufferedReader(new FileReader("./res/shaders/"+ fileName));
            String line;

            while((line = shaderReader.readLine()) != null)
            {
                shaderSource.append(line).append("\n");
            }
            shaderReader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }

        return shaderSource.toString();
    }

    public static Mesh loadMesh(String fileName)
    {
        String[] splitArray = fileName.split("\\.");
        String ext = splitArray[splitArray.length -1];

        System.out.println(ext);
        if(!ext.equals("obj") )
        {
            System.err.println("Invalid File Format: "+ext+" file format is not supported for loading Meshes");
            new Exception().printStackTrace();
            System.exit(1);
        }

        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        ArrayList<Integer> indices = new ArrayList<Integer>();
        BufferedReader meshReader;

        try
        {
            meshReader = new BufferedReader(new FileReader("./res/models/"+ fileName));
            String line;

            while((line = meshReader.readLine()) != null)
            {
                String[] tokens = line.split(" ");
                tokens = Utility.removeEmptyStrings(tokens);

                if((tokens.length == 0) || tokens[0].equals("#"))
                    continue;
                else if(tokens[0].equals("v"))
                {
                    vertices.add(new Vertex(new Vector3(Float.valueOf(tokens[1]),
                                                        Float.valueOf(tokens[2]),
                                                        Float.valueOf(tokens[3]))));
                }

                else if(tokens[0].equals("f"))
                {
                   indices.add(Integer.parseInt(tokens[1])-1);
                    indices.add(Integer.parseInt(tokens[2])-1);
                    indices.add(Integer.parseInt(tokens[3])-1);
                }
            }
            meshReader.close();
            Mesh res= new Mesh();

            Vertex[] vertexData = new Vertex[vertices.size()];
            vertices.toArray(vertexData);

            Integer[] indexData = new Integer[(indices.size())];
            indices.toArray(indexData);

            res.AddVertices(vertexData,Utility.toIntArray(indexData));

            return res;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static Texture loadTexture(String fileName)
    {
        String[] splitArray = fileName.split("\\.");
        String ext = splitArray[splitArray.length -1];

        System.out.println(ext);

        try
        {
           int id = TextureLoader.getTexture(ext,new FileInputStream(new File("./res/textures/"+fileName))).getTextureID();

           return new Texture(id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }
}

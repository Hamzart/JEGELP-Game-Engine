package com.core.engine;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

public class Mesh {

    private int vbo;
    private int ibo;
    private int SIZE;

    public Mesh()
    {
        vbo = glGenBuffers();
        ibo = glGenBuffers();

        SIZE = 0;
    }

    public void AddVertices(Vertex[] vertices, int[] indices)
    {
        AddVertices(vertices,indices,false);
    }
    public void AddVertices(Vertex[] vertices, int[] indices,boolean calcNormal)
    {
        if(calcNormal)
        {
            calcNormals(vertices,indices);
        }
        SIZE = indices.length ;
        glBindBuffer(GL_ARRAY_BUFFER,vbo);
        glBufferData(GL_ARRAY_BUFFER,Utility.createFlippedBuffer(vertices),GL_STATIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER,Utility.createFlippedBuffer(indices),GL_STATIC_DRAW);

    }
    public void Draw()
    {
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);

        glBindBuffer(GL_ARRAY_BUFFER,vbo);

        glVertexAttribPointer(0,3,GL_FLOAT,false,Vertex.SIZE * 4,0);
        glVertexAttribPointer(1,2,GL_FLOAT,false,Vertex.SIZE * 4,12);
        glVertexAttribPointer(2,3,GL_FLOAT,false,Vertex.SIZE * 4,20);


        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ibo);
        glDrawElements(GL_TRIANGLES,SIZE,GL_UNSIGNED_INT,0);


        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);


    }

    private void calcNormals(Vertex[] vertices, int[] indices)
    {
        for(int i = 0;i<indices.length;i+=3)
        {
            int i0 = indices[i];
            int i1 = indices[i+1];
            int i2 = indices[i+2];

            Vector3 v1 = vertices[i1].getPos().sub(vertices[i0].getPos());
            Vector3 v2 = vertices[i2].getPos().sub(vertices[i0].getPos());
            Vector3 normal = v1.cross(v2).normalized();

            vertices[i0].setNormal(vertices[i0].getNormal().add(normal));
            vertices[i1].setNormal(vertices[i1].getNormal().add(normal));
            vertices[i2].setNormal(vertices[i2].getNormal().add(normal));

        }


        for(int i = 0;i<vertices.length;i++)
        {
        vertices[i].setNormal(vertices[i].getNormal().normalized());
        }


    }
}

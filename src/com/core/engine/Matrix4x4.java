package com.core.engine;

public class Matrix4x4 {

    private float[][] m;
    public Matrix4x4()
    {
     m = new float[4][4];
    }

    public Matrix4x4 initIdentity()
    {
        m[0][0] = 1;       m[0][1] = 0;          m[0][2] = 0;        m[0][3] = 0;
        m[1][0] = 0;       m[1][1] = 1;          m[1][2] = 0;        m[1][3] = 0;
        m[2][0] = 0;       m[2][1] = 0;          m[2][2] = 1;        m[2][3] = 0;
        m[3][0] = 0;       m[3][1] = 0;          m[3][2] = 0;        m[3][3] = 1;
        return this;
    }

    public Matrix4x4 initTranslation(float x,float y,float z)
    {
        m[0][0] = 1;       m[0][1] = 0;          m[0][2] = 0;        m[0][3] = x;
        m[1][0] = 0;       m[1][1] = 1;          m[1][2] = 0;        m[1][3] = y;
        m[2][0] = 0;       m[2][1] = 0;          m[2][2] = 1;        m[2][3] = z;
        m[3][0] = 0;       m[3][1] = 0;          m[3][2] = 0;        m[3][3] = 1;
        return this;
    }
    public Matrix4x4 initRotation(float x, float y, float z)
    {
        Matrix4x4 rx = new Matrix4x4();
        Matrix4x4 ry = new Matrix4x4();
        Matrix4x4 rz = new Matrix4x4();

        x = (float)Math.toRadians(x);
        y = (float)Math.toRadians(y);
        z = (float)Math.toRadians(z);

        rz.m[0][0] = (float)Math.cos(z);rz.m[0][1] = -(float)Math.sin(z);rz.m[0][2] = 0;				rz.m[0][3] = 0;
        rz.m[1][0] = (float)Math.sin(z);rz.m[1][1] = (float)Math.cos(z);rz.m[1][2] = 0;					rz.m[1][3] = 0;
        rz.m[2][0] = 0;					rz.m[2][1] = 0;					rz.m[2][2] = 1;					rz.m[2][3] = 0;
        rz.m[3][0] = 0;					rz.m[3][1] = 0;					rz.m[3][2] = 0;					rz.m[3][3] = 1;

        rx.m[0][0] = 1;					rx.m[0][1] = 0;					rx.m[0][2] = 0;					rx.m[0][3] = 0;
        rx.m[1][0] = 0;					rx.m[1][1] = (float)Math.cos(x);rx.m[1][2] = -(float)Math.sin(x);  rx.m[1][3] = 0;
        rx.m[2][0] = 0;					rx.m[2][1] = (float)Math.sin(x);rx.m[2][2] = (float)Math.cos(x);  rx.m[2][3] = 0;
        rx.m[3][0] = 0;					rx.m[3][1] = 0;					rx.m[3][2] = 0;					rx.m[3][3] = 1;

        ry.m[0][0] = (float)Math.cos(y) ;ry.m[0][1] = 0;					ry.m[0][2] = -(float)Math.sin(y); ry.m[0][3] = 0;
        ry.m[1][0] = 0;					ry.m[1][1] = 1;					ry.m[1][2] = 0;					ry.m[1][3] = 0;
        ry.m[2][0] = (float)Math.sin(y) ;ry.m[2][1] = 0;					ry.m[2][2] = (float)Math.cos(y); ry.m[2][3] = 0;
        ry.m[3][0] = 0;					ry.m[3][1] = 0;					ry.m[3][2] = 0;					ry.m[3][3] = 1;

        m = rz.mul(ry.mul(rx)).get();

        return this;
    }

    public Matrix4x4 initScale(float x,float y,float z)
    {
        m[0][0] = x;       m[0][1] = 0;          m[0][2] = 0;        m[0][3] = 0;
        m[1][0] = 0;       m[1][1] = y;          m[1][2] = 0;        m[1][3] = 0;
        m[2][0] = 0;       m[2][1] = 0;          m[2][2] = z;        m[2][3] = 0;
        m[3][0] = 0;       m[3][1] = 0;          m[3][2] = 0;        m[3][3] = 1;
        return this;
    }

    public Matrix4x4 initProjection(float fov,float width,float height,float nearClipping,float farClipping)
    {
        float aspectRatio = width/height;
        float tanHalfFOV = (float)Math.tan(fov / 2);
        float zRange = nearClipping - farClipping;

        m[0][0] = 1.0f / (tanHalfFOV * aspectRatio);	m[0][1] = 0;					    m[0][2] = 0;	                                    m[0][3] = 0;
        m[1][0] = 0;						            m[1][1] = 1.0f / tanHalfFOV;	    m[1][2] = 0;	                                    m[1][3] = 0;
        m[2][0] = 0;						            m[2][1] = 0;					    m[2][2] = (-nearClipping -farClipping)/zRange;	    m[2][3] = 2 * farClipping * nearClipping / zRange;
        m[3][0] = 0;						            m[3][1] = 0;					    m[3][2] = 1;	                                    m[3][3] = 0;


        return this;
    }

    public Matrix4x4 initCamera(Vector3 forward, Vector3 up)
    {
       Vector3 f = forward.normalized();
       // f.normalized();

        Vector3 r = up.normalized();
        //r.normalized();
        r=r.cross(f);

        Vector3 u = f.cross(r);

        m[0][0] = r.getX();       m[0][1] = r.getY();          m[0][2] = r.getZ();        m[0][3] = 0;
        m[1][0] = u.getX();;       m[1][1] = u.getY();;          m[1][2] = u.getZ();;        m[1][3] = 0;
        m[2][0] = f.getX();;       m[2][1] = f.getY();;          m[2][2] = f.getZ();;        m[2][3] = 0;
        m[3][0] = 0;       m[3][1] = 0;          m[3][2] = 0;        m[3][3] = 1;
        return this;
    }


    public Matrix4x4 mul(Matrix4x4 r)
  {
      Matrix4x4 res = new Matrix4x4();
      for(int i=0;i<4;i++)
      {
          for(int j = 0; j < 4; j++)
          {
              res.setAt(m[i][0] * r.getAt(0, j) + m[i][1] * r.getAt(1, j) + m[i][2] * r.getAt(2, j) + m[i][3] * r.getAt(3, j),i,j);
          }
      }

      return res;

  }

    public float[][] get() {

        float[][] res = new float[4][4];

        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                res[i][j] = m[i][j];

        return res;

    }

    public void set(float[][] m) {
        this.m = m;
    }

    public float getAt(int x,int y)
    {
        return m[x][y];
    }

    public void setAt(float val,int x,int y)
    {
        m[x][y] = val;
    }
}
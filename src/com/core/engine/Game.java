package com.core.engine;


public class Game {

    private Mesh mesh;
    private  Transform transform;
    //private  Texture texture;
    private Camera camera;
    private PhongShader shader;
    private Material material;




    public  Game()
    {
    mesh = new Mesh();
   // mesh = ResourceLoader.loadMesh("box.obj");
    camera = new Camera();
    material = new Material(ResourceLoader.loadTexture("squares.png"),new Vector3(1,1,1));
    shader = new PhongShader().getInstance();

        Vertex[] vertices = new Vertex[] { new Vertex( new Vector3(-1.0f, -1.0f, 0.5773f),	new Vector2(0.0f, 0.0f)),
                new Vertex( new Vector3(0.0f, -1.0f, -1.15475f),		new Vector2(0.5f, 0.0f)),
                new Vertex( new Vector3(1.0f, -1.0f, 0.5773f),		new Vector2(1.0f, 0.0f)),
                new Vertex( new Vector3(0.0f, 1.0f, 0.0f),      new Vector2(0.5f, 1.0f)) };

        int indices[] = { 0, 3, 1,
                1, 3, 2,
                2, 3, 0,
                1, 2, 0 };
    mesh.AddVertices(vertices,indices,true);

    transform = new Transform();
    Transform.setProjection(70,Window.getWidth(),Window.getHeight(),0.001f,1000);
    Transform.setCamera(camera);

    }

    public  void Input()
    {



        camera.Input();



    }



    float tmp = 0.0f;

    public  void Update()
    {
       tmp += Time.getDelta();
        float sinTMP = (float)Math.sin(tmp);
        transform.setTranslation(0,0,5);
        transform.setRotation(0,transform.getRotation().Y + 0.02f,0);
        transform.setScale(1,1,1);
        shader.setAmbientLight(new Vector3(0.2f,0.2f,0.2f));
        shader.setDirectionalLight(new DirectionalLight(new BaseLight(new Vector3(1,1,1), 0.5f), new Vector3(1,1,0)));



    }

    public void Render()
    {

        RenderUtility.setClearColor(Transform.getCamera().getPosition().div(2048f).abs());
        shader.updateUniforms(transform.getTransformation(),transform.getProjectedTransformation(),material);
        shader.bind();
        mesh.Draw();
    }
}

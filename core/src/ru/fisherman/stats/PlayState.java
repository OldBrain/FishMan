package ru.fisherman.stats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.fisherman.StartGame;
import sprites.FisherMan;
import sprites.Sea;

public class PlayState extends State {
  private Texture menu;
  private FisherMan fisherMan;
  private Sea sea;
  private float cameraRotationSpeed;
  private float cameraMoveSpeed;
  private final float FISHERMAN_SPEED = 15f;


  public PlayState(GameStateManager gsm) {
    super(gsm);
    cameraRotationSpeed = 0.25f;
    cameraMoveSpeed = 0.625f;

//    cameraController = new CameraController();
    camera.setToOrtho(false, StartGame.HEIGHT, StartGame.WIDTH / 2);
//    menu = new Texture("menu.png");
    sea = new Sea();
    fisherMan = new FisherMan(50, sea.getSEA_HEIGHT());
  }


  @Override
  protected void update(float dt) {
    handleInput();
    fisherMan.update(dt);
  }

  @Override
  protected void render(SpriteBatch sb) {

    sb.setProjectionMatrix(camera.combined);
    camera.update();

    sb.begin();
    handleInput();
    sb.draw(sea.getSeaTexture(), 0, 0, StartGame.WIDTH, sea.getSEA_HEIGHT());
    sb.draw(fisherMan.getFisherManTexture(), fisherMan.getPosition().x, fisherMan.getPosition().y - 12, 100, 30);
//    sb.draw(fisherMan.getFisherManTexture(), fisherMan.getPosition().x, fisherMan.getPosition().y-12,
//        100, 30,0,0,0,0.5f,3,3,3,
//        3,3,false,false);
    sb.end();
  }

  @Override
  protected void dispose() {

  }


  @Override
  public void handleInput() {
//    System.out.println(camera.position.x);
    if (Gdx.input.isTouched()) {
      fisherMan.setV(FISHERMAN_SPEED);

      if ((camera.position.x < 560) && !(camera.position.x - fisherMan.getPosition().x >= 195)) {
        camera.translate(0.1f, 0, 0);

      }
    }
    else {
        fisherMan.setV(0f);

      }

      if (Gdx.input.isKeyPressed(Input.Keys.A)) {
        camera.zoom += 0.02;
      }
      if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
        camera.zoom -= 0.02;
      }
      if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
        if (camera.position.x > 242)
          camera.translate(cameraMoveSpeed * (-1), 0, 0);
      }
      if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
        System.out.println(camera.position.x);
        if (camera.position.x < 560) {

          camera.translate(cameraMoveSpeed, 0, 0);
        }
      }

      if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
        if (camera.position.y > 0)
          camera.translate(0, -3, 0);
      }
      if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
        if (camera.position.y < 1024)
          camera.translate(0, 3, 0);
      }
      if (Gdx.input.isKeyPressed(Input.Keys.W)) {
        camera.rotate(-cameraRotationSpeed, 0, 0, 1);
      }
      if (Gdx.input.isKeyPressed(Input.Keys.E)) {
        camera.rotate(cameraRotationSpeed, 0, 0, 1);
      }
    }
  }

package ru.fisherman.stats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.fisherman.StartGame;
import sprites.*;
import sun.security.ssl.HandshakeOutStream;

public class PlayState extends State {
  private Texture menu;
  private Boat boat;
  private Sea sea;
  private float cameraRotationSpeed;
  private float cameraMoveSpeed;
  private final float FISHERMAN_SPEED = 15f;
  private House house;


  public PlayState(GameStateManager gsm) {
    super(gsm);
    cameraRotationSpeed = 0.25f;
    cameraMoveSpeed = 0.625f;

//    cameraController = new CameraController();
    camera.setToOrtho(false, StartGame.HEIGHT, StartGame.WIDTH / 2);
//    menu = new Texture("menu.png");
    sea = new Sea();
    house = new House();
    boat = new Boat(10, sea.getSEA_HEIGHT());
  }


  @Override
  protected void update(float dt) {
    handleInput();
    boat.update(dt);
  }

  @Override
  protected void render(SpriteBatch sb) {

    sb.setProjectionMatrix(camera.combined);
    camera.update();

    sb.begin();
    handleInput();

    camera.position.x = boat.getPosition().x + 230;
    sb.draw(house.getHouseTexture(), 0, sea.getSEA_HEIGHT(), house.getHeight(), house.getWidth());
    sb.draw(sea.getSeaTexture(), 0, 0, StartGame.WIDTH, sea.getSEA_HEIGHT());
    sb.draw(boat.getFisherManTexture(), boat.getPosition().x,
    boat.getPosition().y - boat.getDraft(), boat.getBoatHeight(),
    boat.getBoatWidth());

    sb.end();
  }

  @Override
  protected void dispose() {

  }


  @Override
  public void handleInput() {

    if (Gdx.input.isTouched()) {
      if (Gdx.input.getX() > boat.getPosition().x+boat.getBoatHeight()) {
        System.out.println(boat.getPosition().x);
        if ((boat.getPosition().x < StartGame.WIDTH / 2 - boat.getBoatWidth()))
             {
          boat.setV(FISHERMAN_SPEED);
        }
      }
      // Двигаемся назад
      else {
        if ((boat.getPosition().x >=boat.getBoatStartPositionX())) {
          boat.setV(FISHERMAN_SPEED*(-1));
        }
      }
    }
    else {
        boat.setV(0f);

      }

//      if (Gdx.input.isKeyPressed(Input.Keys.A)) {
//        camera.zoom += 0.02;
//      }
//      if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
//        camera.zoom -= 0.02;
//      }
//      if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
//        if (camera.position.x > 242)
//          camera.translate(cameraMoveSpeed * (-1), 0, 0);
//      }
//      if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
//      if (camera.position.x < 560) {
//          camera.translate(cameraMoveSpeed, 0, 0);
//        }
//      }
//
//      if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
//        if (camera.position.y > 0)
//          camera.translate(0, -3, 0);
//      }
//      if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
//        if (camera.position.y < 1024)
//          camera.translate(0, 3, 0);
//      }
//      if (Gdx.input.isKeyPressed(Input.Keys.W)) {
//        camera.rotate(-cameraRotationSpeed, 0, 0, 1);
//      }
//      if (Gdx.input.isKeyPressed(Input.Keys.E)) {
//        camera.rotate(cameraRotationSpeed, 0, 0, 1);
//      }
    }
  }

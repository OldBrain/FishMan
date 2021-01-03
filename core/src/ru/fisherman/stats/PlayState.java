package ru.fisherman.stats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
  private FisherMan men;
  private Bait bait;
  private float baitBeginPositionX;
  private float baitBeginPositionY;
  private boolean isMomentum;
  ShapeRenderer shapeRenderer;

  public PlayState(GameStateManager gsm) {
    super(gsm);
    cameraRotationSpeed = 0.25f;
    cameraMoveSpeed = 0.625f;

//    cameraController = new CameraController();
//    camera.setToOrtho(false, StartGame.HEIGHT, StartGame.WIDTH / 2);
//    menu = new Texture("menu.png");

    sea = new Sea();
    house = new House();
    men = new FisherMan();
    boat = new Boat(10, sea.getSEA_HEIGHT());

    bait = new Bait(boat.getBoatStartPositionX() + boat.getBoatHeight() + 6, boat.getPosition().y + 20);

    baitBeginPositionX = bait.getPosition().x+bait.getSizeH()/2;
    baitBeginPositionY = bait.getPosition().y+bait.getSizeH()/2;
    camera.setToOrtho(false, 200, 200);

    camera.position.x = boat.getPosition().x + 100;
    camera.position.y = boat.getPosition().y;

    shapeRenderer = new ShapeRenderer();


  }


  @Override
  protected void update(float dt) {



    handleInput();
    boat.update(dt);
    bait.update(dt, isMomentum, 7.5f, Math.PI / 4);

  }

  @Override
  protected void render(SpriteBatch sb) {


    sb.setProjectionMatrix(camera.combined);

//    camera.update();
    sb.begin();

    camera.update();
    handleInput();

//    camera.position.x = boat.getPosition().x + boat.getBoatHeight();
    camera.position.x = bait.getPosition().x;
    camera.position.y = bait.getPosition().y;


//    camera.translate(boat.getPosition());



    sb.draw(house.getHouseTexture(), 0, sea.getSEA_HEIGHT(), house.getHeight(), house.getWidth());
    sb.draw(sea.getSeaTexture(), 0, 0, StartGame.WIDTH, sea.getSEA_HEIGHT());
    sb.draw(boat.getFisherManTexture(), boat.getPosition().x,
        boat.getPosition().y - boat.getDraft(), boat.getBoatHeight(),
        boat.getBoatWidth());


    sb.draw(men.getTextureMen(), boat.getPosition().x + boat.getBoatHeight() - 20, boat.getPosition().y - 5, men.getSizeW(), men.getSizeH());
    sb.draw(bait.getBaitTexture(), bait.getPosition().x, bait.getPosition().y, bait.getSizeW(), bait.getSizeH());

    System.out.println(bait.getPosition().y);
    sb.end();
    showCord();

  }

  private void showCord() {
    shapeRenderer.setColor(Color.BLACK);
    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
    shapeRenderer.setProjectionMatrix(camera.combined);
//    camera.position.x = bait.getPosition().x;
//    camera.position.y = bait.getPosition().y;

    shapeRenderer.line(baitBeginPositionX,baitBeginPositionY,bait.getPosition().x+bait.getSizeH()/2,bait.getPosition().y+bait.getSizeH()/2);

    shapeRenderer.end();
  }

  @Override
  protected void dispose() {
    shapeRenderer.dispose();

  }


  @Override
  public void handleInput() {

    if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
      isMomentum = true;
    } else{
//      isMomentum = false;
    }

    if (Gdx.input.isTouched()) {

      if (Gdx.input.getX() > boat.getPosition().x + boat.getBoatHeight()) {
//        System.out.println(boat.getPosition().x + boat.getBoatHeight() + 6);
        if ((boat.getPosition().x < StartGame.WIDTH / 2 - boat.getBoatWidth())) {
          boat.setV(FISHERMAN_SPEED);
          bait.setV(FISHERMAN_SPEED, 0);

        }
      }
      // Двигаемся назад
      else {
        if ((boat.getPosition().x >= boat.getBoatStartPositionX())) {
          boat.setV(FISHERMAN_SPEED * (-1));
          bait.setV(FISHERMAN_SPEED * (-1), 0);
        }
      }
    } else {
      boat.setV(0f);
      bait.setV(0f, 0);
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

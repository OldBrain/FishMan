package ru.fisherman.stats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import fishing_accessories.Bait;
import fishing_accessories.Boat;
import fishing_accessories.FishingRod;
import ru.fisherman.StartGame;
import ru.fisherman.controller.Joystick;
import sprites.*;
import util.TextView;

public class PlayState extends State {

  float dt;
  private Texture menu;
  private fishing_accessories.Boat boat;
  private Sea sea;
  private float cameraRotationSpeed;
  private float cameraMoveSpeed;
  private final float BOAT_SPEED = 30f;

//  private TextView textView;

  private House house;
  private FisherMan men;
  private fishing_accessories.Bait bait;
  private float baitBeginPositionX;
  private float baitBeginPositionY;
  private boolean isMomentum;
  private ShapeRenderer shapeRenderer;
  private Joystick joystick;
  private fishing_accessories.FishingRod rod;
  private Vector2 touchCoordinate;
  private Vector2 cursorCoordinate;
  private Vector2 spriteCoordinate;
  private BitmapFont font;
  StringBuilder strB;

  public PlayState(GameStateManager gsm) {
    super(gsm);
    font = new BitmapFont(Gdx.files.internal("font.fnt"));
    strB = new StringBuilder();
    cursorCoordinate = new Vector2(0, 0);

    touchCoordinate = new Vector2(0, 0);
    spriteCoordinate = new Vector2(0, 0);
    cameraRotationSpeed = 0.25f;
    cameraMoveSpeed = 0.625f;
    sea = new Sea();

    house = new House(0, sea.getSEA_HEIGHT(), 160, 100);

    boat = new Boat(0, sea.getSEA_HEIGHT(), 250, 75);
    men = new FisherMan(boat.getPosition().x + boat.getBoatWidth() - 30, boat.getPosition().y, 17 * 6, 26 * 6);
    rod = new FishingRod(men.getPosition().x + 10, men.getPosition().y + men.getSizeW() / 3 + 10, 70, 200);
    bait = new Bait(rod.getPosition().x + rod.getSizeW() / 2 - 2f, rod.getPosition().y + rod.getSizeH() - 2.5f, 15f, 15f);
    baitBeginPositionX = bait.getPosition().x + bait.getSizeH() / 2;
    baitBeginPositionY = bait.getPosition().y + bait.getSizeH() / 2;
    joystick = new Joystick(rod.getPosition().x, rod.getPosition().y, rod.getSizeH());
    shapeRenderer = new ShapeRenderer();
  }


  @Override
  protected void update(float dt) {

    handleInput();
    isMomentum = joystick.isMomentum();
    boat.update(dt);
    men.setPosition(boat.getPosition().x + boat.getBoatWidth() - 30, boat.getPosition().y);
    rod.setPosition(men.getPosition().x + 10, men.getPosition().y + men.getSizeW() / 3 + 10);
    joystick.setPosition(rod.getPosition().x, rod.getPosition().y);

    if (isMomentum) {
      bait.update(dt, isMomentum, 30f, joystick.getAlpha());
      camera.position.x = bait.getPosition().x;
      camera.position.y = bait.getPosition().y;

    } else {

      camera.position.x = boat.getPosition().x + StartGame.WIDTH / 2;
      camera.position.y = boat.getPosition().y;
      bait.update(dt, isMomentum, 30f, joystick.getAlpha());
      bait.setPosition(rod.getPosition().x - 5, rod.getPosition().y + rod.getSizeH() - 50);

    }
    makeString();

  }

  private void makeString() {
    strB.delete(0, strB.length());
    strB.append("X:" + (int)getCursorCoordinate().x + " Y:" + (int)getCursorCoordinate().y);
    strB.append(" baitX:" + (int)bait.getPosition().x + " baitY" + (int)bait.getPosition().y);
//    strB.append(" camX:" + (int)camera.position.x + " camY:" + (int)camera.position.y);
  }

  @Override
  protected void render(SpriteBatch sb) {
    dt = Gdx.graphics.getDeltaTime();
    sb.setProjectionMatrix(camera.combined);
    sb.begin();


    sb.draw(house.getHouseTexture(), 0, sea.getSEA_HEIGHT(), house.getSizeW(), house.getSizeH());
    sb.draw(sea.getSeaTexture(), 0, 0, sea.getSEA_WIDTH(), sea.getSEA_HEIGHT());

    sb.draw(boat.getBoatTexture(), boat.getPosition().x,
        boat.getPosition().y - boat.getDraft(), boat.getBoatWidth(),
        boat.getBoatHeight());

    sb.draw(men.getTexture(), men.getPosition().x, men.getPosition().y, men.getSizeW(), men.getSizeH());

    sb.draw(rod.getTexture(), rod.getPosition().x, rod.getPosition().y, rod.getSizeW(), rod.getSizeH());

    sb.draw(bait.getBaitTexture(), bait.getPosition().x, bait.getPosition().y, bait.getSizeW(), bait.getSizeH());

    if (joystick.isShow) {
      joystick.show(sb, joystick.getPosition().x, joystick.getPosition().y, rod);
//      sb.draw(rod.getTexture(), rod.getPosition().x, rod.getPosition().y,rod.getSizeW(),rod.getSizeH());
    }
//    System.out.println(bait.getPosition().y);

    font.draw(sb,strB, camera.position.x-camera.viewportWidth/2+50, camera.position.y,StartGame.WIDTH-50,2,true);

    camera.update();
    sb.end();
    if (isMomentum) {
      showCord();
    }

  }

  private void showCord() {
    shapeRenderer.setColor(Color.BLACK);
    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
    shapeRenderer.setProjectionMatrix(camera.combined);

    shapeRenderer.line(baitBeginPositionX, baitBeginPositionY, bait.getPosition().x + bait.getSizeH() / 2, bait.getPosition().y + bait.getSizeH() / 2);
    shapeRenderer.end();
  }

  @Override
  protected void dispose() {
    shapeRenderer.dispose();


  }

  private Vector2 getCursorCoordinate(){
    cursorCoordinate.x = Gdx.input.getX()+camera.position.x-camera.viewportWidth/2;
    cursorCoordinate.y = (camera.viewportHeight-Gdx.input.getY())+camera.position.y-camera.viewportHeight/2;
    return cursorCoordinate;
  }

//  private Vector2 getTouchCoordinate(float x, float y) {
//    touchCoordinate.x = x;
////    coordinate.y = Math.abs(y-StartGame.HEIGHT);
//    touchCoordinate.y = StartGame.HEIGHT - y;
////        - camera.viewportWidth/2;
//
//    return touchCoordinate;
//  }

//  public Vector2 getSpriteCoordinate(float x, float y) {
//    spriteCoordinate.x = x;
//    spriteCoordinate.y = y - sea.getSEA_HEIGHT() + StartGame.HEIGHT / 2;
//    return spriteCoordinate;
//  }

  @Override
  public void handleInput() {

    if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
      isMomentum = true;
    } else {
//      isMomentum = false;
    }

    if (Gdx.input.isTouched()) {

      if (Math.abs(rod.getPosition().x - getCursorCoordinate().x) < 100
          && (Math.abs(rod.getPosition().y - getCursorCoordinate().y)) < 100) {

        joystick.isShow = true;
      } else {
//        joystick.isShow = false;
      }


//      if (Gdx.input.getX() > boat.getPosition().x + boat.getBoatHeight()) {
      if ((StartGame.WIDTH / 2 - Gdx.input.getX() < 0) && (!joystick.isShow)) {
        if ((boat.getPosition().x < sea.getSEA_WIDTH() - boat.getBoatWidth())) {
          boat.setV(BOAT_SPEED);
//          bait.setV(BOAT_SPEED, 0);

        }
      }
      // Двигаемся назад
      else {
        if ((boat.getPosition().x >= boat.getBoatStartPositionX()) && (!joystick.isShow)) {
          boat.setV(BOAT_SPEED * (-1));
          bait.setV(BOAT_SPEED * (-1), 0);
        }
      }
    } else {
      boat.setV(0f);
      bait.setV(0f, 0);
      joystick.isShow = false;
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

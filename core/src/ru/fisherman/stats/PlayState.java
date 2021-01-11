package ru.fisherman.stats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import ru.fisherman.StartGame;
import ru.fisherman.controller.Joystick;
import sprites.*;

public class PlayState extends State {


  private Texture menu;
  private Boat boat;
  private Sea sea;
  private float cameraRotationSpeed;
  private float cameraMoveSpeed;
  private final float BOAT_SPEED = 15f;
  private House house;
  private FisherMan men;
  private Bait bait;
  private float baitBeginPositionX;
  private float baitBeginPositionY;
  private boolean isMomentum;
  private ShapeRenderer shapeRenderer;
  private Joystick joystick;
  private FishingRod rod;
  OrthographicCamera camera;


  public PlayState(GameStateManager gsm) {
    super(gsm);
    camera = new OrthographicCamera();
    camera.setToOrtho(false,StartGame.WIDTH, StartGame.HEIGHT);
    cameraRotationSpeed = 0.25f;
    cameraMoveSpeed = 0.625f;

    sea = new Sea();
    house = new House(0,0,160,100);

    boat = new Boat(0, sea.getSEA_HEIGHT(),250,75);
    men = new FisherMan(boat.getPosition().x + boat.getBoatWidth() - 30, boat.getPosition().y ,17 * 6,26*6);
    rod = new FishingRod(men.getPosition().x,men.getPosition().y+men.getSizeW()/3+10,150/2,150);


    bait = new Bait(rod.getPosition().x+rod.getSizeW()/2-2f, rod.getPosition().y+rod.getSizeH()-2.5f,15f,15f);

    baitBeginPositionX = bait.getPosition().x+bait.getSizeH()/2;
    baitBeginPositionY = bait.getPosition().y+bait.getSizeH()/2;


//    joystick = new Joystick(baitBeginPositionX-25,baitBeginPositionY-25,50);
    joystick = new Joystick(rod.getPosition().x,rod.getPosition().y,rod.getSizeH());

//    camera.setToOrtho(false, 200, 200);
    camera.position.x = boat.getPosition().x+StartGame.WIDTH/2;
    camera.position.y = bait.getPosition().y-StartGame.HEIGHT/4;


    shapeRenderer = new ShapeRenderer();


  }


  @Override
  protected void update(float dt) {
//    camera.position.x = boat.getPosition().x+StartGame.WIDTH/2;
//    camera.position.y = bait.getPosition().y-StartGame.HEIGHT/4;
    handleInput();

    isMomentum = joystick.isMomentum();
    boat.update(dt);
    men.setPosition(boat.getPosition().x + boat.getBoatWidth() - 30, boat.getPosition().y );
    rod.setPosition(men.getPosition().x, men.getPosition().y + men.getSizeW() / 3 + 10);

    if (isMomentum) {
      bait.update(dt, isMomentum, 22f, Math.PI/8f );
      camera.position.x = bait.getPosition().x;
      camera.position.y = bait.getPosition().y;

    } else {
      camera.position.x = boat.getPosition().x+StartGame.WIDTH/2;
      camera.position.y = bait.getPosition().y-StartGame.HEIGHT/4;
      bait.setPosition(rod.getPosition().x+rod.getSizeW()/2-2f, rod.getPosition().y+rod.getSizeH()-2.5f);
    }



  }

  @Override
  protected void render(SpriteBatch sb) {
    sb.setProjectionMatrix(camera.combined);
    sb.begin();



    sb.draw(house.getHouseTexture(), 0, sea.getSEA_HEIGHT(), house.getSizeW(), house.getSizeH());
    sb.draw(sea.getSeaTexture(), 0, 0,  sea.getSEA_WIDTH(),sea.getSEA_HEIGHT());

    sb.draw(boat.getBoatTexture(), boat.getPosition().x,
        boat.getPosition().y - boat.getDraft(), boat.getBoatWidth(),
        boat.getBoatHeight());

    sb.draw(men.getTexture(), men.getPosition().x , men.getPosition().y, men.getSizeW(), men.getSizeH());

    sb.draw(rod.getTexture(), rod.getPosition().x, rod.getPosition().y,rod.getSizeW(),rod.getSizeH());

    sb.draw(bait.getBaitTexture(), bait.getPosition().x, bait.getPosition().y, bait.getSizeW(), bait.getSizeH());

    if (joystick.isShow) {
   joystick.show(sb,joystick.getPosition().x, joystick.getPosition().y);

    }
//    System.out.println(bait.getPosition().y);

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
      System.out.println(rod.getPosition().y +"<>"+ Gdx.input.getY());
//      System.out.println(rod.getPosition().y - Gdx.input.getY());

      if (Math.abs(rod.getPosition().x - Gdx.input.getX()) < 100
          && (Math.abs(rod.getPosition().y - Gdx.input.getY()-sea.getSEA_WIDTH()/2) < 200)) {

        joystick.isShow = true;
      } else {
        joystick.isShow = false;
      }


//      if (Gdx.input.getX() > boat.getPosition().x + boat.getBoatHeight()) {
      if ((StartGame.WIDTH/2-Gdx.input.getX() <0)&(!joystick.isShow) ) {
        if ((boat.getPosition().x < StartGame.WIDTH / 2 - boat.getBoatWidth())) {
          boat.setV(BOAT_SPEED);
//          bait.setV(BOAT_SPEED, 0);

        }
      }
      // Двигаемся назад
      else {
        if ((boat.getPosition().x >= boat.getBoatStartPositionX())) {
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

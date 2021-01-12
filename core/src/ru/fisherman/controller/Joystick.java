package ru.fisherman.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import ru.fisherman.StartGame;
import sprites.Sea;

public class Joystick {
  Texture circle;



  Texture inCircleTexture;

  float width;
  float height;
  Vector2 position;
  float joystickR;
  float inJoystickR;
  Vector2 v;
  float alpha;
  float x;
  float y;
  float x1;
  float y1;
  private boolean isMomentum;
  public boolean isShow;
  Vector2 touchCoordinate;
  Vector2 spriteCoordinate;

  public Joystick(float x,float y,float joystickR) {
   this.circle = new Texture("dj.png");
    this.inCircleTexture = new Texture("u3.png");
    this.position = new Vector2(x,y);
    this.touchCoordinate = new Vector2(0, 0);
    this.spriteCoordinate = new Vector2(0, 0);

    this.width = joystickR;
    this.height = joystickR/2;
    this.joystickR = joystickR;
//    sprite = new Sprite(inCircleTexture, 0,0, (int) width,(int) height);
    this.isMomentum = false;
    this.isShow = false;

  }

  public void setPosition(float x,float y) {
    this.position.x = x;
    this.position.y = y;
  }

  public float getAlpha() {
    return (float) Math.toRadians(alpha);
  }

  public Texture getInCircleTexture() {
    return inCircleTexture;
  }

  public void setAlpha(float alpha) {
    this.alpha = alpha;
  }

  public void setMomentum(boolean momentum) {
    isMomentum = momentum;
  }

  public boolean isMomentum() {
    return isMomentum;
  }

  public float getWidth() {
    return width;
  }

  public float getHeight() {
    return height;
  }

  public Vector2 getPosition() {
    return position;
  }

  public void show(SpriteBatch batch, float x, float y) {
    batch.draw(circle, x-height/2, y+2,height,height);
    batch.draw(inCircleTexture, x, y, height/2+2, 2, height, width, 1 , 1,  alpha, 0, 0, inCircleTexture.getWidth(), inCircleTexture.getHeight(), false, false);
    Gdx.input.setInputProcessor(new InputProcessor() {

      @Override
      public boolean keyDown(int keycode) {
        return false;
      }

      @Override
      public boolean keyUp(int keycode) {
        return false;
      }

      @Override
      public boolean keyTyped(char character) {
        return false;
      }

      @Override
      public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        x1 = getTouchCoordinate(screenX, screenY).x;
        y1 = getTouchCoordinate(screenX, screenY).y;
        makeAlpha(x1,y1);
        return false;

      }

      @Override
      public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        x1 = getTouchCoordinate(screenX, screenY).x;
        y1 = getTouchCoordinate(screenX, screenY).y;
        makeAlpha(x1,y1);
        isMomentum = true;
        return false;
      }

      @Override
      public boolean touchDragged(int screenX, int screenY, int pointer) {
        x1 = getTouchCoordinate(screenX, screenY).x;
        y1 = getTouchCoordinate(screenX, screenY).y;
        makeAlpha(x1,y1);
        return false;
      }

      @Override
      public boolean mouseMoved(int screenX, int screenY) {
//        alpha += 1;

        return false;
      }

      @Override
      public boolean scrolled(float amountX, float amountY) {
        return false;
      }
    });

  }

  private void makeAlpha(float x1, float y1) {
    x = getSpriteCoordinate(position.x, position.y).x;
    y = getSpriteCoordinate(position.x, position.y).y;
    System.out.println(x+"<>"+y);
    float a = x - x1;
    float b = y - y1;
    float tmp = (float) Math.toDegrees(Math.atan((a) / (b)))*(-1);

    if (tmp >= 0 && tmp <= 90) {
      alpha = (float) Math.toDegrees(Math.atan((a) / (b)));
      alpha =   alpha*(-1);
    }



  }

  private Vector2 getTouchCoordinate(float x, float y) {
    touchCoordinate.x = x;
//    coordinate.y = Math.abs(y-StartGame.HEIGHT);
    touchCoordinate.y = StartGame.HEIGHT-y;
//        - camera.viewportWidth/2;

    return touchCoordinate;
  }

  public Vector2 getSpriteCoordinate(float x, float y) {
    spriteCoordinate.x = x;
    spriteCoordinate.y = y - Sea.SEA_HEIGHT+StartGame.HEIGHT/2;
    return spriteCoordinate;
  }
}

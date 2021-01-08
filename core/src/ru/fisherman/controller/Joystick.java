package ru.fisherman.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import ru.fisherman.StartGame;

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

 public boolean isShow;

  public Joystick(float x,float y,float joystickR) {
   circle = new Texture("dj.png");
    inCircleTexture = new Texture("u.png");
    position = new Vector2(x,y);


    this.width = joystickR;
    this.height = joystickR/2;
    this.joystickR = joystickR;
//    sprite = new Sprite(inCircleTexture, 0,0, (int) width,(int) height);

    this.isShow = false;

  }

  public Texture getInCircleTexture() {
    return inCircleTexture;
  }

  public void setAlpha(float alpha) {
    this.alpha = alpha;
  }

  public float getWidth() {
    return width;
  }

  public float getHeight() {
    return height;
  }


  public void show(SpriteBatch batch,  float x, float y,OrthographicCamera camera) {

    this.x = camera.position.x;
    this.y = camera.position.y;
    batch.draw(circle, x-height/2, y+2,height,height);
//    System.out.println("a= "+alpha);
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
        x1 = screenX;
        y1 = screenY;
        makeAlpha(x1,y1);
//        alpha += 1;
        return false;

      }

      @Override
      public boolean touchUp(int screenX, int screenY, int pointer, int button) {
//        alpha = 0;
        makeAlpha(screenX,screenY);
//        System.out.println(screenX);
        return false;
      }

      @Override
      public boolean touchDragged(int screenX, int screenY, int pointer) {
//        alpha += 1;
//        System.out.println(x +"--"+ screenX+" / "+screenY);
//        System.out.println(pointer);
        makeAlpha(screenX, screenY);
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

    x1 = x1 + x - StartGame.WIDTH / 2;
    y1 = Math.abs(y1 - StartGame.HEIGHT) + y - StartGame.HEIGHT / 2;
    float a = x - x1;
    float b = y - y1;
    float tmp = (float) Math.toDegrees(Math.atan((a) / (b)))*(-1);

    if (tmp >= 0 && tmp <= 90) {
      alpha = (float) Math.toDegrees(Math.atan((a) / (b)));
      alpha =   alpha*(-1);
    }



  }


}

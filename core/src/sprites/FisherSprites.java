package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import ru.fisherman.StartGame;


public abstract class FisherSprites {
   float sizeW;
   float sizeH;
   float time;
   Texture texture;
   Vector2 position;
   Vector2 v;
   Vector2 touchCoordinate;
   Vector2 spriteCoordinate;

  public FisherSprites(float x, float y,float sizeW, float sizeH) {
    this.sizeW = sizeW;
    this.sizeH = sizeH;
    this.position = new Vector2(x, y);
    this.v = new Vector2(0, 0);
    this.touchCoordinate = new Vector2(0, 0);
    this.spriteCoordinate = new Vector2(0, 0);
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

  public Vector2 getPosition() {
    return position;
  }

  public Vector2 getV() {
    return v;
  }

  public float getSizeW() {
    return sizeW;
  }

  public float getSizeH() {
    return sizeH;
  }

  public Texture getTexture() {
    return texture;
  }

  public void setPosition(float x,float y) {
    this.position.x = x;
    this.position.y = y;
  }

  public void setV(float x,float y) {
    this.v.x = x;
    this.v.y = y;
  }



}

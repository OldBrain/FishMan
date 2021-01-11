package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public abstract class FisherSprites {
   float sizeW;
   float sizeH;
   float time;
   Texture texture;
   Vector2 position;
   Vector2 v;

  public FisherSprites(float x, float y,float sizeW, float sizeH) {
    this.sizeW = sizeW;
    this.sizeH = sizeH;
    this.position = new Vector2(x, y);
    this.v = new Vector2(0, 0);

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

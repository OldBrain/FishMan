package fishing_accessories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import sprites.Sea;

public class Bait {
  private int cordLength;
  private float sizeW;
  private float sizeH;
  private float time;
  private Texture bait;
  private Vector2 position;
  private Vector2 v;
  private float baitBeginPositionX;
  private float baitBeginPositionY;

  public Bait(float x, float y,float sizeW,float sizeH) {
    this.cordLength = 800;
    this.position = new Vector2(x,y);
    this.v = new Vector2(0f,0f);
    this.bait = new Texture("bool.png");
    this.sizeH = sizeH;
    this.sizeW = sizeW;
    this.v = new Vector2(0,0);

//    shapeRenderer = new ShapeRenderer();
  }

  public void setPosition(float x,float y) {
    this.position.x = x;
    this.position.y = y;

  }


  public void update(float dt, boolean isMomentum, float v0, double alpha) {
    if (position.y <= 0) {
      isMomentum = false;
    }
    v.scl(dt);
    position.add(v.x, 0);
    if (isMomentum) {
      castingFishingRod(dt, v0, alpha,0f);
    } else {
      baitBeginPositionX = position.x;
      baitBeginPositionY = position.y;
    }

  }

  public void render(SpriteBatch batch) {


  }



  public void castingFishingRod(float dt,float v0,double alpha,float g) {
    time += dt;


    if (position.y >= 0) {

//      System.out.println(position.y);

    if (getFreeCord(position.x, position.y) < cordLength) {

      v.x = v0 * (float) Math.sin(alpha);
      v.y = (float) (v0 * (float) Math.cos(alpha) - g * time);
//      v.x =  (float) Math.sin(alpha);
//      v.y = (float) ((float) Math.cos(alpha) - g * time);
      v.nor();
      if (position.y > 1440) {
               v.scl(v0);
      }

      position.add(v.x, v.y);
    } else { // Шнур вымотан полностью

      v.x = v.x-10;

        v.y = (float) (v0 * (float) Math.cos(alpha) - g * time);
      if (v.y > 0) {

        v.y = v.y * (-1);
      }


      v.nor();
      if (position.y > 1440) {
        v.scl(v0);
      }

      position.add(v.x, v.y);

    }
    }
  }

  public float getSizeW() {
    return sizeW;
  }

  public float getSizeH() {
    return sizeH;
  }

  public Texture getBaitTexture() {
    return bait;
  }

  public Vector2 getPosition() {
    return position;
  }

  public void setV(float x,float y) {
    this.v.x = x;
    this.v.y = y;
  }
  private float getFreeCord(float x, float y) {
//    System.out.println(baitBeginPositionX+"***"+baitBeginPositionY);
    float a = Math.abs(x - baitBeginPositionX);
    float b = Math.abs(y - baitBeginPositionY);
    float c = (float) Math.sqrt(a * a + b * b);
//    System.out.println(a+"***"+b+" c= "+c);
    return c;
  }

  public void setBaitBeginPosition(float x,float y) {
    this.baitBeginPositionX = x;
    this.baitBeginPositionY = y;
  }
}

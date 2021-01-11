package sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import ru.fisherman.StartGame;

public class Bait {
//  private float baitBeginPositionY;
//  private float baitBeginPositionX;
  private int cordLength;
  private float sizeW;
  private float sizeH;
  private float time;
  private Texture bait;
  private Vector2 position;
  private Vector2 v;


  public Bait(float x, float y,float sizeW,float sizeH) {
    this.cordLength = 20;
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
    v.scl(dt);
    position.add(v.x, 0);
    if (isMomentum) {
//      baitBeginPositionX = position.x+sizeH/2;
//      baitBeginPositionY = position.y+sizeW/2;
      castingFishingRod(dt,v0,alpha);

    }
  }

  public void castingFishingRod(float dt,float v0,double alpha) {
    time += dt;
    if (position.y > cordLength) {
    v.x = v0*(float) Math.sin(alpha);
    v.y = (float) (v0*(float) Math.cos(alpha)-9.8*time);
    v.scl(time);

      position.add(v.x, v.y);
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
}

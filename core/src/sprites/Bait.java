package sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import ru.fisherman.StartGame;

public class Bait {
  int cordLength;
  int sizeW;
  int sizeH;
  float time;
  Texture bait;
  private Vector3 position;
  private Vector3 v;
//  ShapeRenderer shapeRenderer;


  public Bait(float x, float y) {
    this.cordLength = 20;
    this.position = new Vector3(x,y,0);
    this.v = new Vector3(0f,0f,0f);
    this.bait = new Texture("bool.png");
    this.sizeH = 5;
    this.sizeW = 5;
    this.v = new Vector3(0,0,0f);
//    shapeRenderer = new ShapeRenderer();
  }

  public void setPosition(Vector3 position) {
    this.position = position;
  }



  public void update(float dt, boolean isMomentum,float v0,double alpla) {
    v.scl(dt);
    position.add(v.x, 0, 0);
//    shapeRenderer.setProjectionMatrix();
//    shapeRenderer.setColor(Color.BLACK);
//    shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//    shapeRenderer.rectLine(position.x,position.y, 0, 0, 1);
//    shapeRenderer.end();
    if (isMomentum) {
      castingFishingRod(dt,v0,alpla);

    }
  }

  public void castingFishingRod(float dt,float v0,double alpla) {
    time += dt;
    if (position.y > cordLength) {
    v.x = v0*(float) Math.sin(alpla);
    v.y = (float) (v0*(float) Math.cos(alpla)-9.8*time);
    v.scl(time);
//    System.out.println(this.v);

      position.add(v.x, v.y, v.z);
    }
  }

  public int getSizeW() {
    return sizeW;
  }

  public int getSizeH() {
    return sizeH;
  }

  public Texture getBaitTexture() {
    return bait;
  }

  public Vector3 getPosition() {
    return position;
  }

  public void setV(float x,float y) {
    this.v.x = x;
    this.v.y = y;
  }
}

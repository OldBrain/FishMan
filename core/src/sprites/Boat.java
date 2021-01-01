package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Boat {
  private int boatWidth;
  private int boatHeight;
  private float boatStartPositionX;

  private final float GRAVITY=-0.1f;
  private Vector3 position;
  private Vector3 v;
  private int draft; // Осадка лодки
  private Texture fisherMan;

  public Boat(float x, float y) {
    this.position = new Vector3(x,y,0);
    this.v = new Vector3(0f,0f,0f);
    this.fisherMan = new Texture("bot.png");
    this.draft = 12;
    this.boatWidth = 30;
    this.boatHeight = 100;
    this.boatStartPositionX = x;
  }

  public float getBoatStartPositionX() {
    return boatStartPositionX;
  }

  public int getBoatHeight() {
    return boatHeight;
  }

  public int getBoatWidth() {
    return boatWidth;
  }

  public int getDraft() {
    return draft;
  }

  public void setDraft(int draft) {
    this.draft = draft;
  }

  public void setV(float vX) {
    this.v.x=vX;

  }

  public Vector3 getPosition() {
    return position;
  }

  public Texture getFisherManTexture() {
    return fisherMan;
  }


  public void update(float dt) {
//    forceOfGravity(false,dt);


//    v.x = 0.02f;
//    v.add(0, 0, 0);
    v.scl(dt);
    position.add(v.x, 0, 0);
//    v.scl(1 / dt);
  }
  private void forceOfGravity(boolean status,float dt) {
    if (status) {
      v.add(0, GRAVITY, 0);
      v.scl(dt);
      position.add(0, v.y, 0);
      v.scl(1 / dt);
    } else {
      v.add(0, 0, 0);
      v.scl(dt);
      position.add(0, v.y, 0);
      v.scl(1 / dt);
    }

  }
}

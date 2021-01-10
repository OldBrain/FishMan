package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Boat {
  private float boatWidth;
  private float boatHeight;
  private float boatStartPositionX;

  private final float GRAVITY=-0.1f;
  private Vector2 position;
  private Vector2 v;
  private int draft; // Осадка лодки
  private Texture boat;


  public Boat(float x, float y,float boatWidth,float boatHeight) {
    this.position = new Vector2(x,y);
    this.v = new Vector2(0f,0f);
    this.boat = new Texture("boat.png");
    this.draft = 12;
//    this.boatWidth = 30;
    this.boatWidth = boatWidth;
//    this.boatHeight = 100;
    this.boatHeight = boatHeight;
    this.boatStartPositionX = x;
  }

  public float getBoatStartPositionX() {
    return boatStartPositionX;
  }

  public float getBoatHeight() {
    return boatHeight;
  }

  public float getBoatWidth() {
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

  public Vector2 getPosition() {
    return position;
  }

  public Texture getBoatTexture() {
    return boat;
  }


  public void update(float dt) {
    v.scl(dt);
    position.add(v.x, 0);
  }
  private void forceOfGravity(boolean status,float dt) {
    if (status) {
      v.add(0, GRAVITY);
      v.scl(dt);
      position.add(0, v.y);
      v.scl(1 / dt);
    } else {
      v.add(0, 0);
      v.scl(dt);
      position.add(0, v.y);
      v.scl(1 / dt);
    }

  }
}

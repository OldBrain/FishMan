package fishing_accessories;

import com.badlogic.gdx.graphics.Texture;
import sprites.FisherSprites;

public class FishingRod extends FisherSprites {
  // Характиристики спининга.
  private float maxForce; // Сила заброса
  private float cordLight;// Длина шнура

  public FishingRod(float x, float y, float sizeW, float sizeH) {
    super(x, y, sizeW, sizeH);
    texture = new Texture("u3.png");
  }
  public void setSpecification(float maxForce,float cordLight) {
    this.maxForce = maxForce;
    this.cordLight = cordLight;
  }

  public float getMaxForce() {
    return maxForce;
  }

  public float getCordLight() {
    return cordLight;
  }
}

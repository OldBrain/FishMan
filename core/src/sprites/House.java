package sprites;

import com.badlogic.gdx.graphics.Texture;

public class House extends FisherSprites{
//  private int width;
//  private int height;
//  Texture houseTexture;
  //
//  this.height = 50;
//    this.width =80;


  public House(float x, float y, float sizeW, float sizeH) {
    super(x, y, sizeW, sizeH);
    this.texture = new Texture("house.png");
  }

  public Texture getHouseTexture() {
    return texture;
  }
}

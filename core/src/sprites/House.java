package sprites;

import com.badlogic.gdx.graphics.Texture;

public class House {
  private int width;
  private int height;
  Texture houseTexture;

  public House() {
    houseTexture = new Texture("house.png");
    this.height = 50;
    this.width =80;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public Texture getHouseTexture() {
    return houseTexture;
  }
}

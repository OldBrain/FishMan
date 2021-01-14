package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public class FisherMan extends FisherSprites {

  public FisherMan(float x, float y, float sizeW, float sizeH) {
    super(x, y, sizeW, sizeH);
    this.texture = new Texture("fm.png");
    this.sizeW = sizeW;
//        = 17 * 2;
    this.sizeH = sizeH;
//        = 26 * 2;
  }

  public Texture getTexture() {
    return texture;
  }


}

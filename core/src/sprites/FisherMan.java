package sprites;

import com.badlogic.gdx.graphics.Texture;

import java.awt.*;

public class FisherMan {
  Texture men;
  int sizeW;
  int sizeH;

  public FisherMan() {
    this.men = new Texture("men.png");
    this.sizeW = sizeW=17*2;
    this.sizeH = sizeH=26*2;
  }

  public Texture getTextureMen() {
    return men;
  }

  public int getSizeW() {
    return sizeW;
  }

  public int getSizeH() {
    return sizeH;
  }
}

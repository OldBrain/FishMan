package sprites;

import com.badlogic.gdx.graphics.Texture;
import ru.fisherman.StartGame;

public class Sea {
  Texture sea;
//  private final float SEA_WIDTH = StartGame.WIDTH*3;
  private final float SEA_WIDTH = 800*5;//800*5= 4000;
  private final float SEA_HEIGHT = 480*3;//480*3=1440
//  private final float X=SEA_WIDTH/-2;
  private final float X=0;



  public Texture getSeaTexture() {
    return sea;
  }

  public float getX() {
    return X;
  }

  public float getSEA_WIDTH() {
    return SEA_WIDTH;
  }

  public float getSEA_HEIGHT() {
    return SEA_HEIGHT;
  }

  public Sea() {
    sea = new Texture("water.png");
  }

  public void update(float dt) {

  }
}

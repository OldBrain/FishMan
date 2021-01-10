package sprites;

import com.badlogic.gdx.graphics.Texture;
import ru.fisherman.StartGame;

public class Sea {
  Texture sea;
  private final float SEA_WIDTH = StartGame.WIDTH;
  private final float SEA_HEIGHT = StartGame.HEIGHT;

  public Texture getSeaTexture() {
    return sea;
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

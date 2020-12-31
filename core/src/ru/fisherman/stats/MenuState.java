package ru.fisherman.stats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.fisherman.StartGame;

public class MenuState extends State {

  Texture fon;
  Texture key;
  public MenuState(GameStateManager gsm) {
    super(gsm);
    fon = new Texture("water.png");
    key = new Texture("menu.png");

  }

  @Override
  protected void handleInput() {
    if (Gdx.input.justTouched()) {
      gsm.set(new PlayState(gsm));
    }
  }

  @Override
  protected void update(float dt) {
    handleInput();
  }

  @Override
  protected void render(SpriteBatch sb) {
    sb.begin();
    sb.draw(fon, 0,0, StartGame.WIDTH, StartGame.HEIGHT);
    sb.draw(key, 0, StartGame.HEIGHT-50, 50, 50);
    sb.end();
  }

  @Override
  protected void dispose() {
    fon.dispose();
    key.dispose();

  }
}

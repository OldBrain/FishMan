package ru.fisherman;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.fisherman.stats.GameStateManager;
import ru.fisherman.stats.MenuState;

public class StartGame extends ApplicationAdapter {
  SpriteBatch batch;
  private GameStateManager gsm;
  public static final int WIDTH=800;
  public static final int HEIGHT=480;


  @Override
  public void create() {
    batch = new SpriteBatch();
    gsm = new GameStateManager();
    Gdx.gl.glClearColor(0.2f,0.0f,0.3f,1);
    gsm.push(new MenuState(gsm));

  }

  @Override
  public void render() {
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    gsm.update(Gdx.graphics.getDeltaTime());
    gsm.render(batch);

  }
}

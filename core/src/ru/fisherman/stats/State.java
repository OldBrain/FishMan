package ru.fisherman.stats;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
  OrthographicCamera camera;
  Vector3 mouse;
  GameStateManager gsm;

  public State(GameStateManager gsm) {
    this.gsm = gsm;
//    camera = new OrthographicCamera();
    mouse = new Vector3();
  }

  protected abstract void handleInput();
  protected abstract void update(float dt);
  protected abstract void render(SpriteBatch sb);
  protected abstract void dispose();
}

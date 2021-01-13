package util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextView {
  private BitmapFont font;
  StringBuilder strB;
  OrthographicCamera camera;

  public TextView(OrthographicCamera camera) {
    font = new BitmapFont(Gdx.files.internal("font.fnt"));
    strB = new StringBuilder();
    this.camera = camera;
  }

  public void update(float dt) {
    strB.delete(0, strB.length());
  strB.append("X:" + Gdx.input.getX() + " Y:" + Gdx.input.getY());


  }


  public void render(SpriteBatch sb,float dt) {
    update(dt);


    font.draw(sb,strB, camera.position.x-camera.viewportWidth/2+50, camera.position.y-200,80,1,false);


  }

  }


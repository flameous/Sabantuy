package bash.bashhack.sabantuy;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class HighscoreScreen extends ScreenAdapter {
    MySabantuyGame game;
    OrthographicCamera camera;

    Vector3 touchPoint;

    HighscoreScreen(MySabantuyGame game) {
        this.game = game;

        camera = new OrthographicCamera(300, 500);
        camera.position.set(300 / 2, 500 / 2, 0);

        touchPoint = new Vector3();
    }

    private void update() {

    }

    private void draw() {

    }

    @Override
    public void render (float delta) {
        update();
        draw();
    }
}

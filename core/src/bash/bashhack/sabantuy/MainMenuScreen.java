package bash.bashhack.sabantuy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class MainMenuScreen extends ScreenAdapter {
    MySabantuyGame game;
    OrthographicCamera camera;

    Rectangle playButton;
    Rectangle highscoreButton;
    Rectangle exitButton;
    Rectangle languageButton;

    Vector3 touchPoint;

    MainMenuScreen(MySabantuyGame game) {
        this.game = game;

        camera = new OrthographicCamera(Const.SCREEN_WIDHT, Const.SCREEN_HEIGHT);
        camera.position.set(Const.CAMERA_CENTER);

        playButton = new Rectangle(130, 730, 500, 140);
        highscoreButton = new Rectangle(130, 500, 500, 140);
        exitButton = new Rectangle(130, 270, 500, 140);

        languageButton = new Rectangle(550, 1180, 180, 100);

        touchPoint = new Vector3();
    }

    public void update() {
        if (Gdx.input.justTouched()) {
            camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (playButton.contains(touchPoint.x, touchPoint.y)) {
                game.setScreen(new MenuScreen(game, this));
            }
            if (highscoreButton.contains(touchPoint.x, touchPoint.y)) {
                //TODO: скрин для рекордов
            }
            if (exitButton.contains(touchPoint.x, touchPoint.y)) {
                Gdx.app.exit();
            }
            if (languageButton.contains(touchPoint.x, touchPoint.y)) {
                MySabantuyGame.isBash = !MySabantuyGame.isBash;
            }
        }
    }

    public void draw() {
        GL20 gl = Gdx.gl;
        gl.glClearColor(98, 215, 203, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.enableBlending();
        game.batch.begin();

        if (MySabantuyGame.isBash) {
            game.batch.draw(Assets.menuBash, 0, 0);
        } else {
            game.batch.draw(Assets.menuRu, 0, 0);
        }

        game.batch.end();
    }

    @Override
    public void render (float delta) {
        update();
        draw();
    }
}

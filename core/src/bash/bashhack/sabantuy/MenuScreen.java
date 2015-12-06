package bash.bashhack.sabantuy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class MenuScreen extends ScreenAdapter {
    private MySabantuyGame game;
    private MainMenuScreen mainMenuScreen;
    private OrthographicCamera camera;

    private Rectangle findGameButton;
    private Rectangle walkingGameButton;
    private Rectangle shotGameButton;
    private Rectangle backButton;

    private Vector3 touchPoint;

    MenuScreen(MySabantuyGame game, MainMenuScreen mainMenuScreen) {
        this.game = game;
        this.mainMenuScreen = mainMenuScreen;

        camera = new OrthographicCamera(Const.SCREEN_WIDHT, Const.SCREEN_HEIGHT);
        camera.position.set(Const.CAMERA_CENTER);

        findGameButton = new Rectangle(10, 800, 240, 230);
        walkingGameButton = new Rectangle(40, 140, 600, 240);
        shotGameButton = new Rectangle(590, 770, 180, 200);
        backButton = new Rectangle(190, 40, 390, 100);

        touchPoint = new Vector3();
    }

    private void update() {
        if (Gdx.input.justTouched()) {
            camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (findGameButton.contains(touchPoint.x, touchPoint.y)) {
                game.setScreen(new FindGame(game, this));
            }
            if (walkingGameButton.contains(touchPoint.x, touchPoint.y)) {
                game.setScreen(new WalkingGame(game, this));
            }
            if (shotGameButton.contains(touchPoint.x, touchPoint.y)) {

            }
            if (backButton.contains(touchPoint.x, touchPoint.y)) {
                game.setScreen(mainMenuScreen);
            }
        }
    }

    private void draw() {
        GL20 gl = Gdx.gl;
        gl.glClearColor(98, 215, 203, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.enableBlending();
        game.batch.begin();

        if (MySabantuyGame.isBash) {
            game.batch.draw(Assets.mapBash, 0, 0);
        } else {
            game.batch.draw(Assets.mapRus, 0, 0);
        }


        game.batch.end();
    }

    @Override
    public void render (float delta) {
        update();
        draw();
    }
}

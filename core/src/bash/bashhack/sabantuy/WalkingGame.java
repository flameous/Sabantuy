package bash.bashhack.sabantuy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.Random;

import static java.lang.StrictMath.PI;
import static java.lang.StrictMath.abs;
import static java.lang.StrictMath.acos;
import static java.lang.StrictMath.round;
import static java.lang.StrictMath.sin;

public class WalkingGame extends ScreenAdapter {
    private MySabantuyGame game;
    private MenuScreen menuScreen;
    private OrthographicCamera camera;

    private final static double MIN_ANGLE = 5;
    private final static double PERIODE = 2;

    private double amplitude = 0;
    private double distance = 0;
    private double nextAmplitude;

    private boolean isRight;

    private double Time;
    private double TimeStart;

    private float StartAnimate = 2; // Хак

    private State currentState = State.RUNNING;
    private double currentAngle;

    private double animateDistance;
    private boolean isAnimate;

    WalkingGame(MySabantuyGame game, MenuScreen menuScreen) {
        this.game = game;
        this.menuScreen = menuScreen;
        camera = new OrthographicCamera(Const.SCREEN_WIDHT, Const.SCREEN_HEIGHT);
        camera.position.set(Const.CAMERA_CENTER);
    }

    private void update(float delta) {
        Time += delta;
        StartAnimate += delta;
        switch (currentState) {
            case RUNNING:
                double angle = (acos(Gdx.input.getAccelerometerX() / 10) / PI * 180) - 90;

                currentAngle = sin(((Time - TimeStart) / PERIODE) * 2 * PI) * amplitude;
                if (abs(currentAngle) < 0.5) {
                    amplitude += nextAmplitude;
                    nextAmplitude = 0;
                }
                if (!isRight) {
                    currentAngle = -currentAngle;
                }


                if (amplitude < MIN_ANGLE) {
                    amplitude -= 0.1;
                }
                if (amplitude < 0) {
                    amplitude = 0;
                }

                if (Gdx.input.justTouched()) {
                    animateDistance = distance;
                    distance += 10;
                    StartAnimate = 0;
                    if (amplitude < 0.1) {
                        Random random = new Random();
                        isRight = random.nextBoolean();
                        TimeStart = Time;
                        amplitude += distance*0.3;
                    } else {
                        nextAmplitude = distance*0.6;
                    }
                }

                if (animateDistance < distance) {
                    animateDistance += delta*10;
                }

                if (((currentAngle > 0 && angle < 0)
                        || (currentAngle < 0 && angle > 0))
                        && abs(angle) > MIN_ANGLE) {
                    double different = abs(currentAngle - angle);

                    amplitude -= 20/different;
                }
                break;
            case FINISH:

                break;
        }
    }

    private void draw() {
        GL20 gl = Gdx.gl;
        gl.glClearColor(0, 0, 1, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.enableBlending();
        game.batch.begin();

        game.batch.draw(Assets.background, 0, round(-animateDistance * 5), 768, 1800);

        game.batch.draw(Assets.three, 0, round(-animateDistance*5), 768, 1280);

        game.batch.draw(Assets.walkAnimation.getKeyFrame(StartAnimate, Animation.ANIMATION_NONLOOPING),
                84f, 50f, 256f, 0f, 600, 600, 1f, 1f, (float)currentAngle);

        game.batch.end();
    }

    @Override
    public void render (float delta) {
        update(delta);
        draw();
    }

    enum State {
        RUNNING,
        FINISH
    }
}

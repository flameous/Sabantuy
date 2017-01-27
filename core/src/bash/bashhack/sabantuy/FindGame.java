package bash.bashhack.sabantuy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

public class FindGame extends ScreenAdapter {
    private MySabantuyGame game;
    private MenuScreen menuScreen;
    private OrthographicCamera camera;

    private State currentState = State.BRIFIENG;

    private int offset;

    private Item[] items;

    private ArrayList<Item> bashLostItems = new ArrayList<Item>();

    private Vector3 touchPoint = new Vector3();

    private int countNotBashItem = 4;

    private Item babyHouse;
    private Item balalaika;
    private Item bashkirHat;
    private Item bow;
    private Item helmet;
    private Item indHat;
    private Item kurai;
    private Item vikingSword;

    FindGame(MySabantuyGame game, MenuScreen menuScreen) {
        this.game = game;
        this.menuScreen = menuScreen;

        camera = new OrthographicCamera(Const.SCREEN_WIDHT, Const.SCREEN_HEIGHT);
        camera.position.set(Const.CAMERA_CENTER);

        babyHouse = new Item(Assets.baby_house, true, 620, 510, 490, 600, BashItem.baby_house);
        balalaika = new Item(Assets.balalaika, false, 640, 80, 380, 190);
        bashkirHat = new Item(Assets.bashkir_hat, true, 1365, 700, 155, 310, BashItem.bashkir_hat);
        bow = new Item(Assets.bow, true, -100, -100, 430, 430, BashItem.bow);
        helmet = new Item(Assets.helmet, false, 1330, 120, 190, 190);
        indHat = new Item(Assets.ind_hat, false, 1140, 600, 140, 140);
        kurai = new Item(Assets.kurai, true, 1610, 300, 195, 375, BashItem.kurai);
        vikingSword = new Item(Assets.viking_sword, false, 110, 465, 270, 540);

        items = new Item[]{babyHouse, balalaika, bashkirHat, bow, helmet, indHat, kurai, vikingSword};
    }

    private void update(float delta) {
        switch (currentState) {
            case BRIFIENG:
                updateBriefing();
                break;
            case RUNNING:
                updateRunning(delta);
                break;
            case FINISH:
                updateFinish();
                break;
        }
    }

    private void updateBriefing() {
        if (Gdx.input.justTouched()) {
            currentState = State.RUNNING;
        }
    }

    private void updateRunning(float delta) {
        int offsetX;
        if (Gdx.input.isTouched()) {
            offsetX = Gdx.input.getDeltaX();
            offset += offsetX;
        }

        if (offset > 0) {
            offset = 0;
        }
        if (offset < -1025) {
            offset = -1025;
        }
        camera.position.set(Const.CAMERA_CENTER.x - offset,
                Const.CAMERA_CENTER.y , Const.CAMERA_CENTER.z);

        for (Item item: items) {
            item.stepAnimate(delta);

            if (Gdx.input.justTouched()) {
                camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

                Rectangle rectangle = new Rectangle(item.x, item.y, item.width, item.height);

                if (rectangle.contains(touchPoint.x, touchPoint.y)) {
                    item.startAnimate();
                    if (item.isBash) {
                        bashLostItems.add(item);
                    } else {
                        countNotBashItem--;
                    }
                }
            }
        }

        if (countNotBashItem <= 0) {
            currentState = State.FINISH;
        }
    }

    private void updateFinish() {
        camera.position.set(Const.CAMERA_CENTER);

        if (Gdx.input.justTouched()) {
            game.setScreen(menuScreen);
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

        game.batch.draw(Assets.urta, 0, 0);

        for (Item item : items) {
            game.batch.draw(item, item.x, item.y, item.width, item.height);
        }

        if (currentState == State.BRIFIENG) {
            if (MySabantuyGame.isBash) {
                game.batch.draw(Assets.brief_bash, 0, 0);
            } else {
                game.batch.draw(Assets.brief_rus, 0, 0);
            }
        }

        if (currentState == State.FINISH) {
            game.batch.draw(Assets.brief_end, 0, 0);
            if (bashLostItems.contains(bow)) {
                game.batch.draw(Assets.wrong, 35, 820);
            } else {
                game.batch.draw(Assets.right, 35, 820);
            }
            if (bashLostItems.contains(babyHouse)) {
                game.batch.draw(Assets.wrong, 35, 610);
            } else {
                game.batch.draw(Assets.right, 35, 610);
            }
            if (bashLostItems.contains(bashkirHat)) {
                game.batch.draw(Assets.wrong, 35, 400);
            } else {
                game.batch.draw(Assets.right, 35, 400);
            }
            if (bashLostItems.contains(kurai)) {
                game.batch.draw(Assets.wrong, 35, 190);
            } else {
                game.batch.draw(Assets.right, 35, 190);
            }
        }

        game.batch.end();
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();
    }

    enum State {
        BRIFIENG,
        RUNNING,
        FINISH
    }

    class Item extends Sprite {
        public final boolean isBash;
        private int x;
        private int y;
        private final int width;
        private final int height;

        private BashItem bashItem;

        private boolean isAnimate = false;

        Item(TextureRegion textureRegion, boolean isBash, int x, int y, int width, int height) {
            super(textureRegion);
            this.isBash = isBash;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        Item(TextureRegion textureRegion, boolean isBash, int x, int y, int width, int height, BashItem bashItem) {
            super(textureRegion);
            this.isBash = isBash;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.bashItem = bashItem;
        }

        public void startAnimate() {
            isAnimate = true;
        }

        public void stepAnimate(float delta) {
            if (isAnimate) {
                y += delta*2000;
            }
            if (y > 2000) {
                isAnimate = false;
            }
        }
    }

    enum BashItem {
        bow,
        bashkir_hat,
        kurai,
        baby_house
    }
}

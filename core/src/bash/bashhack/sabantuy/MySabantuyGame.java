package bash.bashhack.sabantuy;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MySabantuyGame extends Game {
	public SpriteBatch batch;

    public static boolean isBash = true;

	@Override
	public void create () {
		batch = new SpriteBatch();
		Assets.load();
        setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
        super.render();
	}
}
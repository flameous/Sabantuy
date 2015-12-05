package bash.bashhack.sabantuy.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import bash.bashhack.sabantuy.MySabantuyGame;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		//Custom setting
		config.useAccelerometer = true;
		config.hideStatusBar =  true;

		initialize(new MySabantuyGame(), config);
	}
}

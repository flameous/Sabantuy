package bash.bashhack.sabantuy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

    public static Sprite menuRus;
    public static Sprite menuBash;
    public static Sprite mapRus;
    public static Sprite mapBash;

    public static Sprite urta;

    public static Sprite baby_house;
    public static Sprite balalaika;
    public static Sprite bashkir_hat;
    public static Sprite bow;
    public static Sprite brief_bash;
    public static Sprite brief_end;
    public static Sprite brief_rus;
    public static Sprite helmet;
    public static Sprite ind_hat;
    public static Sprite kurai;
    public static Sprite right;
    public static Sprite viking_sword;
    public static Sprite wrong;

    public static Sprite background;
    public static Sprite three;

    public static Animation walkAnimation;

    public static Music music;

    public static void load () {
        TextureAtlas atlas;
        atlas = new TextureAtlas(Gdx.files.internal("atlas/atlas.atlas"));

        //Menu load
        menuBash = atlas.createSprite("menu/menuBash");
        menuRus = atlas.createSprite("menu/menuRus");
        mapBash = atlas.createSprite("menu/mapBash");
        mapRus = atlas.createSprite("menu/mapRus");

        //Findgame
        urta = atlas.createSprite("findGame/urta");
        baby_house = atlas.createSprite("findGame/baby_house");
        balalaika = atlas.createSprite("findGame/balalaika");
        bashkir_hat = atlas.createSprite("findGame/bashkir_hat");
        bow = atlas.createSprite("findGame/bow");
        brief_bash = atlas.createSprite("findGame/brief_bash");
        brief_end = atlas.createSprite("findGame/brief_end");
        brief_rus = atlas.createSprite("findGame/brief_rus");
        helmet = atlas.createSprite("findGame/helmet");
        ind_hat = atlas.createSprite("findGame/ind_hat");
        kurai = atlas.createSprite("findGame/kurai");
        right = atlas.createSprite("findGame/right");
        viking_sword = atlas.createSprite("findGame/viking_sword");
        wrong = atlas.createSprite("findGame/wrong");

        //walkinggame
        background = atlas.createSprite("walkingGame/background");
        three = atlas.createSprite("walkingGame/three");

        walkAnimation = new Animation(0.1f, atlas.createSprites("walkingGame/animation/walk"));

        music = Gdx.audio.newMusic(Gdx.files.internal("music320.mp3"));
        music.setLooping(true);
        music.setVolume(0.3f);
        music.play();
    }

    public static TextureRegion loadTextureRegion (String file, int width, int height) {
        Texture texture = new Texture(Gdx.files.internal(file));
        return new TextureRegion(texture, 0, 0, width, height);
    }
}

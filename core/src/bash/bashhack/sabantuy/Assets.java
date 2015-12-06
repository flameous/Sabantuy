package bash.bashhack.sabantuy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

    public static TextureRegion menuRu;
    public static TextureRegion menuBash;
    public static TextureRegion menuGamesBash;
    public static TextureRegion menuGamesRu;

    public static TextureRegion urta;

    public static TextureRegion baby_house;
    public static TextureRegion balalaika;
    public static TextureRegion bashkir_hat;
    public static TextureRegion bow;
    public static TextureRegion helmet;
    public static TextureRegion ind_hat;
    public static TextureRegion kurai;
    public static TextureRegion viking_sword;

    public static TextureRegion findItemBriefRu;
    public static TextureRegion findItemBriefBash;
    public static TextureRegion findItemBriefEnd;

    public static TextureRegion rightIcon;
    public static TextureRegion wrongIcon;

    public static Animation walkAnimation;

    public static TextureRegion three;
    public static TextureRegion background;

    public static Music music;

    public static void load () {
        //TODO: Объединить изображения в одно
        menuBash = loadTextureRegion("main_bash.png", 768, 1280);
        menuRu = loadTextureRegion("main_rus.png", 768, 1280);

        menuGamesBash = loadTextureRegion("second_bash.png",768, 1280);
        menuGamesRu = loadTextureRegion("second_rus.png",768, 1280);

        urta = loadTextureRegion("findGame/urta.png",1800 ,1280);

        baby_house = loadTextureRegion("findGame/baby_house.png", 512, 1024);
        balalaika = loadTextureRegion("findGame/balalaika.png", 1024, 512);
        bashkir_hat = loadTextureRegion("findGame/bashkir_hat.png", 512, 1024);
        bow = loadTextureRegion("findGame/bow.png", 512, 512);
        helmet = loadTextureRegion("findGame/helmet.png", 512, 512);
        ind_hat = loadTextureRegion("findGame/ind_hat.png", 512, 512);
        kurai = loadTextureRegion("findGame/kurai.png", 512, 1024);
        viking_sword = loadTextureRegion("findGame/viking_sword.png", 512, 1024);

        findItemBriefRu = loadTextureRegion("findGame/brief_rus.png", 768, 1280);
        findItemBriefBash = loadTextureRegion("findGame/brief_bash.png", 768, 1280);
        findItemBriefEnd = loadTextureRegion("findGame/brief_end.png", 768, 1280);

        rightIcon = loadTextureRegion("findGame/right.png", 128, 128);
        wrongIcon = loadTextureRegion("findGame/wrong.png", 128, 128);

        Texture walk = new Texture(Gdx.files.internal("walkingGame/walking.png"));

        int size = 512;
        walkAnimation = new Animation(0.1f, new TextureRegion(walk, 0, 0, size, size),
                new TextureRegion(walk, size, 0, size, size), new TextureRegion(walk, size*2, 0, size, size),
                new TextureRegion(walk, size*3, 0, size, size), new TextureRegion(walk, size*4, 0, size, size),
                new TextureRegion(walk, size*5, 0, size, size), new TextureRegion(walk, size*6, 0, size, size),
                new TextureRegion(walk, size*7, 0, size, size), new TextureRegion(walk, size*8, 0, size, size),
                new TextureRegion(walk, size*9, 0, size, size), new TextureRegion(walk, size*10, 0, size, size),
                new TextureRegion(walk, size*11, 0, size, size));

        three = loadTextureRegion("walkingGame/three.png", 2048, 3400);

        background = loadTextureRegion("walkingGame/background.png", 2048, 4096);

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

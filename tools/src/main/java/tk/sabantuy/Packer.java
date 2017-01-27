package tk.sabantuy;

import com.badlogic.gdx.tools.texturepacker.*;

public class Packer {
    public static void main (String[] args) throws Exception {
        String inputDir = "android/assets_orig";
        String outputDir = "android/assets/atlas";
        String packFileName = "atlas";

        TexturePacker.Settings settings = new TexturePacker.Settings();

        settings.maxWidth = 4096;
        settings.maxHeight = 4096;
        settings.paddingX = 0;
        settings.paddingY = 0;

        TexturePacker.process(settings ,inputDir, outputDir, packFileName);
    }
}
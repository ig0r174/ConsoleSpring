package com.example.consolespring.tools.ImageTools;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Component
public class ImageSizeTool extends AbstractImageTool {
    @Override
    public String getFunctionDescription() {
        return "Returns image size";
    }

    @Override
    public void function(File file) {
        try{
            BufferedImage image = ImageIO.read(file);
            System.out.printf("Height: %s, Width: %s", image.getHeight(), image.getWidth());
        }
        catch (IOException e){
            System.out.println("Unable to read file " + file.getAbsolutePath());
        }
    }
}

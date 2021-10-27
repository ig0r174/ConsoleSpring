package com.example.consolespring.tools.ImageTools;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.google.common.io.Files;

@Component
public class GreyScaleTool extends AbstractImageTool {
    @Override
    public String getFunctionDescription() {
        return "Creates image in greyscale";
    }

    @Override
    public void function(File file) {
        try {
            BufferedImage image = ImageIO.read(file);
            File newFile = new File(file.getName().replaceFirst("[.][^.]+$", "") + "_greyscale." + Files.getFileExtension(String.valueOf(file)));
            Files.copy(file, newFile);
            createGreyScaleImage(image);
            ImageIO.write(image, Files.getFileExtension(String.valueOf(newFile)), newFile);

            System.out.println("New greyscale path: " + newFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Unable to open image " + file.getAbsolutePath());
        }
    }

    private void createGreyScaleImage(BufferedImage image){
        int w = image.getWidth();
        int h = image.getHeight();
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                Color color = new Color(image.getRGB(x, y));

                int red = (int)(color.getRed() * 0.299);
                int green = (int)(color.getGreen() * 0.587);
                int blue = (int)(color.getBlue() *0.114);

                image.setRGB(x, y, (new Color(red + green + blue, red + green + blue, red + green + blue)).getRGB());
            }
        }
    }


}

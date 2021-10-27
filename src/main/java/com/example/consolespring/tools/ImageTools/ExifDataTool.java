package com.example.consolespring.tools.ImageTools;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class ExifDataTool extends AbstractImageTool {
    @Override
    public String getFunctionDescription() {
        return "Returns EXIF of image";
    }

    @Override
    public void function(File file) {
        try{
            Metadata metadata = ImageMetadataReader.readMetadata(file);
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    System.out.format("[%s] - %s = %s", directory.getName(), tag.getTagName(), tag.getDescription());
                    System.out.println();
                }
                if (directory.hasErrors()) {
                    for (String error : directory.getErrors()) {
                        System.err.format("Ошибка: %s", error);
                        System.out.println();
                    }
                }
            }
        }
        catch (IOException | ImageProcessingException e){
            System.out.println("Не удалось открыть изображение " + file.getAbsolutePath());
        }
    }
}

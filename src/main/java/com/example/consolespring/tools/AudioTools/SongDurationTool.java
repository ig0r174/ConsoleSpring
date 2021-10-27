package com.example.consolespring.tools.AudioTools;

import com.google.common.io.Files;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.springframework.stereotype.Component;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class SongDurationTool extends AbstractAudioTool {
    @Override
    public boolean isSupportedFormat(File file) {
        return Files.getFileExtension(String.valueOf(file)).equals("mp3");
    }

    @Override
    public String getFunctionDescription() {
        return "Возвращает продолжительность аудиофайла в секундах.";
    }

    @Override
    public void function(File file) {
        try (InputStream input = new FileInputStream(file)) {
            Parser parser = new Mp3Parser();

            ContentHandler handler = new DefaultHandler();
            Metadata metadata = new Metadata();
            ParseContext parseCtx = new ParseContext();
            parser.getSupportedTypes(parseCtx);

            parser.parse(input, handler, metadata, parseCtx);

            printSongDuration(metadata);

        } catch (IOException | SAXException | TikaException e) {
            System.out.println(e.getMessage());
        }
    }
    private void printSongDuration(Metadata metadata) {
        String dur = metadata.get("xmpDM:duration");
        System.out.printf("Продолжительность: %s секунд.%n",dur.substring(0, dur.indexOf('.')));
    }
}

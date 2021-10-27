package com.example.consolespring.tools.DirectoryTools;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Stream;

import com.google.common.io.Files;

@Component
public class FrequencyOfExtensions extends AbstractDirectoryTool {
    @Override
    public String getFunctionDescription() {
        return "Returns extensions and their frequency";
    }

    @Override
    public void function(File directory) {

        HashMap<String, Integer> extensions = new HashMap<>();

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if( file.isDirectory() ) continue;
            String extension = Files.getFileExtension(String.valueOf(file));
            extensions.put(extension, extensions.containsKey(extension) ? extensions.get(extension) + 1 : 1);
        }

        if (extensions.keySet().size() == 0) {
            System.out.println("Directory is empty");
        } else {
            extensions.entrySet()
                    .stream()
                    .sorted((x, y) -> y.getValue().compareTo(x.getValue()))
                    .map(pair -> pair.getKey() + ": " + pair.getValue())
                    .forEach(System.out::println);
        }

    }
}

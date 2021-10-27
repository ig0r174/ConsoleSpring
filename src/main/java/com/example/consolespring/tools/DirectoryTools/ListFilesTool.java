package com.example.consolespring.tools.DirectoryTools;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

@Component
public class ListFilesTool extends AbstractDirectoryTool {
    @Override
    public String getFunctionDescription() {
        return "Returns list of files in directory";
    }

    @Override
    public void function(File file) {
        Arrays.stream(Objects.requireNonNull(file.listFiles()))
                .map(x -> (x.isDirectory()? "dir:" : "file:") + "\t" + x.getName())
                .forEach(System.out::println);
    }
}

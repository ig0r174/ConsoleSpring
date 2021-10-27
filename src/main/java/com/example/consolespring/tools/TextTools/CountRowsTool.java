package com.example.consolespring.tools.TextTools;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class CountRowsTool extends AbstractTextTool {

    @Override
    public String getFunctionDescription() {
        return "Returns count of lines in file";
    }

    @Override
    public void function(File file) {
        try {
            int linesQuantity =  Files.readAllLines(file.toPath()).size();
            System.out.printf("In this file %s line", linesQuantity);
        }
        catch (IOException e){
            System.out.println("Unable to open file " + file.getAbsolutePath());
        }
    }
}

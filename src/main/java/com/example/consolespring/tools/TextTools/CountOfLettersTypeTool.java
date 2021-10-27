package com.example.consolespring.tools.TextTools;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

@Component
public class CountOfLettersTypeTool extends AbstractTextTool {
    @Override
    public String getFunctionDescription() {
        return "Returns count the total number of vowels and consonants in a string";
    }

    @Override
    public void function(File file) {
        List<String> str;
        int charCount = 0;
        int counVow = 0;

        try {
            str = Files.readAllLines(Path.of(file.getAbsolutePath()));
        } catch (IOException e) {
            System.out.println("Unable to open file " + file.getAbsolutePath());
            return;
        }

        Scanner input = new Scanner(String.valueOf(str));
        String box = input.nextLine();

        String vowels = "aeiouуеаоэёяию";

        for (char charBox : box.toLowerCase().toCharArray()) {
            if (Character.isLetter(charBox)) {
                charCount++;
                if (vowels.indexOf(charBox) != -1)
                    counVow++;
            }
        }

        System.out.println("Number of vowels: " + counVow);
        System.out.println("Number of consonants: " + (charCount - counVow));

    }
}

package com.example.consolespring;

import com.example.consolespring.tools.Tool;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

@Service
public class FileMaster {

    public void use(String filename) throws FileNotFoundException {

        File file = new File(filename);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }

        ApplicationContext ctx = new AnnotationConfigApplicationContext("com.example.consolespring.tools");
        Tool[] tools = getAvailableTools(file, ctx);

        if (tools.length == 0) {
            System.out.println("No tools for that format.");
            return;
        }

        printToolsForFile(file, tools);
        int number = askUserForNumberOfFunction(tools.length);
        tools[number - 1].function(file);
    }

    private int askUserForNumberOfFunction(int max) {
        while (true) {
            System.out.printf("Enter number of tool below (%d-%d): ", 1, max);
            String input = new Scanner(System.in).nextLine();
            int number;
            try {
                number = Integer.parseInt(input.strip());
                if (number < 1 || number > max) {
                    System.out.println("Incorrect number of tool");
                    continue;
                }
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Incorrect number of tool");
            }
        }
    }

    private void printToolsForFile(File file, Tool[] tools) {
        System.out.println("\n\nThere is some tools for your file " + file.getAbsolutePath() + ":");
        for (int i = 0; i < tools.length; i++) {
            System.out.println((i + 1) + ". " + tools[i].getFunctionDescription());
        }
    }

    private Tool[] getAvailableTools(File file, ApplicationContext ctx) {
        Map<String, Tool> moduleMap = ctx.getBeansOfType(Tool.class);
        return moduleMap.values()
                .stream()
                .filter(tool -> tool.isSupportedFormat(file))
                .toArray(Tool[]::new);
    }

}

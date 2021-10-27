package com.example.consolespring.tools.DirectoryTools;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class SizeFolderFilesTool extends AbstractDirectoryTool {
    @Override
    public String getFunctionDescription() {
        return "Returns size of files in directory";
    }

    @Override
    public void function(File file) {
        try {
            long sum = Files.walk(file.toPath())
                    .mapToLong(p -> p.toFile().length())
                    .sum();
            System.out.printf("Size of folder %s: %s\n",file.getAbsolutePath(), getFormattedSize(sum));
        } catch (IOException e) {
            System.out.println("Unable to count files size in directory " + file.getAbsolutePath());
        }
    }

    private String getFormattedSize(long sum){
        String result = "";
        if (sum < 1024)
            result = sum + "B";
        else if (sum < (1024*1024))
            result = (sum/1024) + "KB";
        else if (sum < (1024*1024*1024))
            result = (sum/1024/1024) + "MB";
        else
            result = (sum/1024/1024/1024) + "GB";
        return result;
    }
}

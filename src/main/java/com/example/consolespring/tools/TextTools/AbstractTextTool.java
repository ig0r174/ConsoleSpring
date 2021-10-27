package com.example.consolespring.tools.TextTools;

import com.example.consolespring.tools.Tool;
import com.google.common.io.Files;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractTextTool implements Tool {
    private static final List<String> _supportedFormats = Arrays.asList("txt", "doc", "docx", "html", "pdf", "fb2" );

    @Override
    public boolean isSupportedFormat(File file){
        return (_supportedFormats.contains(Files.getFileExtension(String.valueOf(file))));
    }

    @Override
    public abstract String getFunctionDescription();

    @Override
    public abstract void function(File file);
}

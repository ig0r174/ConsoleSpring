package com.example.consolespring.tools.ImageTools;

import com.example.consolespring.tools.Tool;
import com.google.common.io.Files;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractImageTool implements Tool {
    static final List<String> _supportedFormats = Arrays.asList("jpg","jpeg","png","bmp", "webp");

    @Override
    public boolean isSupportedFormat(File file) {
        return _supportedFormats.contains(Files.getFileExtension(String.valueOf(file)));
    }

    @Override
    public abstract String getFunctionDescription();

    @Override
    public abstract void function(File file);
}

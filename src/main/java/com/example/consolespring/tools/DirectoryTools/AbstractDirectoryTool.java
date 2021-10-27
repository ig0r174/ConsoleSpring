package com.example.consolespring.tools.DirectoryTools;


import com.example.consolespring.tools.Tool;

import java.io.File;

public abstract class AbstractDirectoryTool implements Tool {
    @Override
    public boolean isSupportedFormat(File file) {
        return file.isDirectory();
    }

    @Override
    public abstract String getFunctionDescription();

    @Override
    public abstract void function(File file);
}

package com.example.consolespring.tools;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public interface Tool {
    boolean isSupportedFormat(File file);
    String getFunctionDescription();
    void function(File file);
}

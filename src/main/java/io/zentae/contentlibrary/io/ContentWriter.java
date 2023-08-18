package io.zentae.contentlibrary.io;

import io.zentae.contentlibrary.file.AdvancedFile;
import io.zentae.contentlibrary.file.Directory;

import java.io.IOException;

public class ContentWriter implements Writer {

    @Override
    public void write(Object data, String path) {
        try {
            // retrieve directory.
            Directory directory = new Directory(path.substring(0, path.lastIndexOf("/")) + 1);
            // check if the directory exists.
            if(!directory.exists())
                // create directory.
                directory.mkdirs();
            // init the configuration file instance.
            AdvancedFile file = new AdvancedFile(path);
            // check if the file doesn't exist.
            if(!file.exists()) {
                // create the file.
                file.createNewFile();
                // copy the default content.
                file.copyContent(getClass().getResourceAsStream(path.substring(path.lastIndexOf("/") - 1)));
            }
        } catch (IOException exception) {
            System.out.println("Error occurred while attempting to write default files: " + exception.getMessage());
            System.exit(-1);
        }
    }
}

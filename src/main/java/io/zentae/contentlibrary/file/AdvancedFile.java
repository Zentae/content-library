package io.zentae.contentlibrary.file;

import java.io.*;
import java.net.URI;

public class AdvancedFile extends File {

    public AdvancedFile(String pathname) {
        super(pathname);
    }

    public AdvancedFile(String parent, String child) {
        super(parent, child);
    }

    public AdvancedFile(File parent, String child) {
        super(parent, child);
    }

    public AdvancedFile(URI uri) {
        super(uri);
    }

    public static AdvancedFile ofFile(File file) {
        return new AdvancedFile(file.getPath());
    }

    /**
     * Copy the content of the {@link InputStream InputStreams}.
     * @param fileInputStreams the {@link InputStream InputStreams} to be copied.
     * @throws IOException read / write exception.
     */
    public void copyContent(InputStream... fileInputStreams) throws IOException {
        for(InputStream fileInputStream : fileInputStreams) {
            InputStreamReader reader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            FileWriter fileWriter = new FileWriter(this);

            while (bufferedReader.ready())
                fileWriter.append(bufferedReader.readLine()).append("\n");

            reader.close();
            bufferedReader.close();
            fileWriter.close();
        }
    }

    /**
     * Copy the content of the {@link File files}.
     * @param files the {@link File files} to be copied.
     * @throws IOException read / write exception.
     */
    public void copyContent(File... files) throws IOException {
        for(File file : files)
            copyContent(new FileInputStream(file));
    }
}

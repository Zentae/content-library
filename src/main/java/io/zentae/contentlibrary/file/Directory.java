package io.zentae.contentlibrary.file;

import java.io.*;
import java.net.URI;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Stream;

public class Directory extends File {

    public Directory(String pathname) {
        super(pathname);
    }

    public Directory(String parent, String child) {
        super(parent, child);
    }

    public Directory(File parent, String child) {
        super(parent, child);
    }

    public Directory(URI uri) {
        super(uri);
    }

    /**
     * @return the {@link File files} inside this {@link Directory}.
     * @throws IOException read exception.
     */
    public Collection<File> getFiles() throws IOException {
        try(Stream<Path> paths = Files.walk(Paths.get(getPath()), FileVisitOption.FOLLOW_LINKS)) {
            return paths.filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .toList();
        }
    }

    /**
     *
     * @param name {@link File}'s name.
     * @return a specific {@link File} inside this {@link Directory}.
     * @throws IOException read exception
     */
    public File getFile(String name) throws IOException {
        return getFiles().stream().filter(file -> file.getName().equals(name))
                .findFirst().get();
    }

    /**
     * @return the {@link Directory directories} inside this {@link Directory}.
     * @throws IOException read exception.
     */
    public Collection<Directory> getDirectories() throws IOException {
        try(Stream<Path> paths = Files.walk(Paths.get(getPath()), FileVisitOption.FOLLOW_LINKS)) {
            return paths.filter(Files::isDirectory)
                    .map(Path::toFile)
                    .map(file -> new Directory(file.getPath()))
                    .toList();
        }
    }

    public Directory getDirectory(String name) throws IOException {
        return getDirectories().stream().filter(directory -> directory.getName().equals(name))
                .findFirst().get();
    }

    /**
     * Copy {@link File files} into this {@link Directory}.
     * @param files the {@link File files} to copy.
     * @throws IOException read exception.
     */
    public void copyFiles(File... files) throws IOException {
        for(File file : files) {
            File destination = new File(getPath() + "/" + file.getName());

            if(!destination.exists()) {
                try {
                    destination.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                InputStream inputStream = new FileInputStream(file);
                InputStreamReader reader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(reader);
                FileWriter fileWriter = new FileWriter(destination);

                while (bufferedReader.ready())
                    fileWriter.append(bufferedReader.readLine()).append("\n");

                reader.close();
                bufferedReader.close();
                fileWriter.close();
            }
        }
    }

}

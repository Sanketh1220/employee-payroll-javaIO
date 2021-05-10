/****************************************************
 * Purpose : Program to created a to watch over a file for activities performed on it
 *
 * @author Sanketh Chigurupalli
 * @version 1.0
 * @since 26-04-2021
 *
 ****************************************************/
package com.javaio;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.StandardWatchEventKinds.*;

public class Java8WatchServiceExample {
    private final WatchService watcher;

    // Created a Map passing watchkey and path
    private final Map<WatchKey, Path> dirWatchers;

    /**
     * @param dir
     * @throws IOException
     */
    Java8WatchServiceExample(Path dir) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        this.dirWatchers = new HashMap<>();
        scanAndRegisterDirectories(dir);
    }

    /**
     * Registering the activities that need to be kept tracked
     * @param dir
     * @throws IOException
     */
    private void registerDirectories(Path dir) throws IOException {
        WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        dirWatchers.put(key, dir);
    }

    /**
     * @param start
     * @throws IOException
     */
    private void scanAndRegisterDirectories(final Path start) throws IOException {
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                registerDirectories(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    @SuppressWarnings({"rawTypes", "unchecked"})
    void processEvents() {
        while (true) {
            WatchKey key = null;
            try {
                key = watcher.take();
            }catch (InterruptedException x) {
                x.printStackTrace();
            }
            Path dir = dirWatchers.get(key);
            if (dir == null) continue;
            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();
                Path name = ((WatchEvent<Path>) event).context();
                Path child = dir.resolve(name);
                System.out.format("%s: %s\n", event.kind().name(), child);

                if (kind == ENTRY_CREATE) {
                    try {
                        if (Files.isDirectory(child)) scanAndRegisterDirectories(child);
                    } catch (IOException x) {}
                } else if (kind.equals(ENTRY_DELETE)) {
                    if (Files.isDirectory(child)) dirWatchers.remove(key);
                }
            }

            boolean valid = key.reset();
            if (!valid) {
                dirWatchers.remove(key);
                if (dirWatchers.isEmpty()) break;
            }
        }
    }
}

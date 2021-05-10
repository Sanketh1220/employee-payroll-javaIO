/****************************************************
 * Purpose : Program to created to handle a file
 *
 * @author Sanketh Chigurupalli
 * @version 1.0
 * @since 26-04-2021
 *
 ****************************************************/
package com.javaio;

import java.io.File;

public class FileUtils {

    /**
     * @param contentsToDelete
     * @return
     */
    public static boolean deleteFiles(File contentsToDelete) {
        File[] allContents = contentsToDelete.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteFiles(file);
            }
        }
        return contentsToDelete.delete();
    }
}

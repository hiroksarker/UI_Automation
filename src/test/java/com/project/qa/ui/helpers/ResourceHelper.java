package com.project.qa.ui.helpers;

public class ResourceHelper {
    /**
     * This method generate resource path for given file
     *
     * @param path
     * @return absolute path
     */
    public static String getResourcePath(String path) {
        String basePath = System.getProperty("user.dir");
        return basePath + "/" + path;
    }
}

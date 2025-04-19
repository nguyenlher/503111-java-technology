package com.mvm;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.validator.routines.UrlValidator;

public class App {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please specify an URL to a file");
            return;
        }

        String urlString = args[0];
        String[] schemes = {"http", "https"};
        UrlValidator urlValidator = new UrlValidator(schemes);
        
        if (!urlValidator.isValid(urlString)) {
            System.out.println("This is not a valid URL");
            return;
        }

        try {
            String fileName = urlString.substring(urlString.lastIndexOf('/') + 1);
            FileUtils.copyURLToFile(new URL(urlString), new File(fileName));
            System.out.println("File downloaded successfully as " + fileName);
        } catch (IOException e) {
            System.out.println("Error downloading file: " + e.getMessage());
        }
    }
}
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// PUT YOUR IMPORTS HERE
import com.drew.metadata.Metadata;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Tag;

/**
 *
 * @author Trevor Hartman
 * @author Jason Carr
 *
 * @since Version 1.0
 *
 */
public class HiddenSecrets {
    public static void getHiddenSecrets(File file) {
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(
                    new FileInputStream(file)
            );
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    System.out.format("[%s] - %s = %s%n",
                            directory.getName(), tag.getTagName(), tag.getDescription());
                }
                if (directory.hasErrors()) {
                    for (String error : directory.getErrors()) {
                        System.err.format("ERROR: %s%n", error);
                    }
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("That file does not exist.");
        } catch (IOException ioe) {
            System.out.println("Problem reading from file stream.");
        } catch (ImageProcessingException ipe) {
            System.out.println("Failed to process the image meta-data");
        }
    }

    public static void main(String[] args) {
        /**
         *
         * @author Jason Carr
         *
         * Old code that worked but required the exact path and file name to work.
         *
         * @since Version 1.0
         *
         */
        //System.out.print("Type directory and filename for image to be analyzed: ");
        //Scanner directory = new Scanner(System.in);
        //String inputString = (directory.nextLine());
        //File file = new File(inputString);
        //getHiddenSecrets(file);

        //System.out.println(directory);
        //System.out.println(inputString);
        //System.out.println(file);

        /** New code works great */
        // Put your code to request a file path,
        System.out.print("Type directory that has images in it: /");
        // read in a string from System.in,
        Scanner path = new Scanner(System.in);
        // convert that string into A Path type using Paths class,
        String inputString = (path.nextLine());
        /**
         *
         * @author chatGPT
         * Propmt: How can I get Java to find all files in a directory
         * @modifiedBy Jason Carr
         *
         * @since Version 1.0
         *
         */
        File directory = new File(inputString);
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                System.out.println(file.getName());
            }
            // and call the getHiddenSecrets method to get the file's meta-data
            // HERE
            /** My code here too */
            getHiddenSecrets(file);
            // print Path
            System.out.println(inputString);
            System.out.println(file);
            System.out.println("=========================================================================================");



        }

    }
}
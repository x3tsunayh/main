package seedu.address.model.person;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.File;
import java.nio.file.Files;
import javax.imageio.ImageIO;

/**
 * Represents a Picture in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Picture {

    public static final String DEFAULT_PATH = "images/default.png";
    public static final String MESSAGE_PICTURE_CONSTRAINTS =
            "Filepath must be valid, point to an image file, and is less than 10MB in size";
    public static final String PICTURE_VALIDATION_REGEX_EXT = "([a-zA-Z]:)?(\\\\[a-zA-Z0-9._-]+)+\\\\?";
    public static final String PICTURE_VALIDATION_REGEX_INT = "[^\\s].*";
    public static final String APPDATA_DIR = defaultDirectory();
    public static final String FOLDER = APPDATA_DIR + "/AddressBook";
    private static final String URL_PREFIX = "file:/";

    private static final int FIVE_MB_IN_BYTES = 5242880;

    private String path;

    /**
     * Default initializer, uses default picture
     */
    public Picture() {

        this.path = DEFAULT_PATH;
    }

    /**
     * initializer if path pointing to pic is specified. For now, only called by XmlAdaptedPerson class
     *
     * @param path
     */
    public Picture(String path) {

        requireNonNull(path);
        String path1 = path;
        //if the file:/ prefix exists, drop it
        if (path.substring(0, 6).equals(URL_PREFIX)) {
            path1 = path.substring(6);
            System.out.println(path);
        }
        checkArgument(isValidPath(path1), MESSAGE_PICTURE_CONSTRAINTS);
        this.path = path;
    }

    /**
     * initializer if path, and image name of new picture is specified
     *
     * @param path
     * @param newPictureName
     */
    public Picture(String path, String newPictureName) {

        requireNonNull(path);
        checkArgument(isValidPath(path), MESSAGE_PICTURE_CONSTRAINTS);
        String extension = "";

        int i = path.lastIndexOf('.');
        if (i > 0) {
            extension = path.substring(i + 1);
        }
        this.path = FOLDER + "//"  + newPictureName + "." + extension;

        createNewPicture(path, newPictureName + "." + extension);

    }

    /**
     * Check if path is valid
     *
     * @param path
     * @return
     */
    public static boolean isValidPath(String path) {

        if (path.equals(DEFAULT_PATH)) {
            return true;
        }

        boolean isValidExternalPath = path.matches(PICTURE_VALIDATION_REGEX_EXT);
        boolean isValidInternalPath = path.matches(PICTURE_VALIDATION_REGEX_INT);

        if (isValidExternalPath || isValidInternalPath) {
            return isValidPicture(path);
        }
        return false;
    }

    /**
     * Checks if the image pointed to by the path is indeed a valid image
     *
     * @param path
     * @return
     */
    public static boolean isValidPicture(String path) {

        File f = new File(path);

        try {
            if (ImageIO.read(f) == null) {
                return false;
            }
        } catch (Exception e) { //cannot read image properly
            return false;
        }

        return isValidSize(path);
    }
    /**
     * Check if image is greater than allowed size
     *
     * @param path
     * @return
     */
    public static boolean isValidSize(String path) {

        File f = new File(path);

        return (f.length() <= FIVE_MB_IN_BYTES);
    }

    /**
     * copies the file from (@code source) to the profile pic folder
     *
     * @param source
     * @param dstFilename
     */
    public void createNewPicture(String source, String dstFilename) {

        File folder = new File(FOLDER);

        if (!folder.exists()) {

            if (!folder.mkdir()) {
                System.out.println("FAILED TO MAKE FOLDER");
            }
        }

        File src = null;

        try {
            src = new File(source);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        File dest = new File(FOLDER + "//" + dstFilename);

        if (!dest.exists()) {
            try {
                dest.createNewFile();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        try {
            Files.copy(src.toPath(), dest.toPath() , REPLACE_EXISTING);
            this.path = URL_PREFIX + dest.toPath().toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getPath() {
        return this.path;
    }

    /**
     * Determines the User OS and output the appropriate folder to store profile pic
     *
     * @return
     */
    private static String defaultDirectory() {
        String os = System.getProperty("os.name").toUpperCase();
        if (os.contains("WIN")) {
            return System.getenv("APPDATA");
        } else if (os.contains("MAC")) {
            return System.getProperty("user.home") + "/Library/Application "
                    + "Support";
        } else if (os.contains("NUX")) {
            return System.getProperty("user.home");
        }
        return System.getProperty("user.dir");
    }


}

package seedu.address.model.person;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.File;
import java.nio.file.Files;
import javax.activation.MimetypesFileTypeMap;

/**
 * Represents a Picture in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Picture {

    public static final String DEFAULT_PATH = "images/default.png";
    public static final String  MESSAGE_PICTURE_CONSTRAINTS =
            "Filepath must be valid, and point to an image file"; //size
    public static final String PICTURE_VALIDATION_REGEX = "[^\\s].*";
    public static final String PICTURE_DIRECTORY  = "ProfilePic/";
    public static final String MESSAGE_USAGE = "dummy";

    private String path;

    /**
     * Default initializer, uses default picture
     */
    public Picture() {
        this.path = DEFAULT_PATH;
    }

    /**
     * initializer if path pointing to pic is specified
     * @param path
     */
    public Picture(String path) {
        this.path = path;
    }

    /**
     * initializer if path, and image name of new picture is specified
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
        this.path = "ProfilePicture/" + newPictureName + "." + extension;

        createNewPicture(path, newPictureName + "." + extension);

    }

    /**
     * Check if path is valid
     * @param path
     * @return
     */
    public boolean isValidPath(String path) {

        if (!path.matches(PICTURE_VALIDATION_REGEX)) {
            return false;
        }
        return isValidPicture(path);
    }

    /**
     * Checks if the image pointed to by the path is indeed a valid image
     * @param path
     * @return
     */
    public static boolean isValidPicture(String path) {

        File f = new File(path);
        String mimetype = new MimetypesFileTypeMap().getContentType(f);
        String type = mimetype.split("/")[0];
        return type.equals("image");
    }

    /**
     * copies the file from (@code source) to the profile pic folder
     * @param source
     * @param dstFilename
     */
    public void createNewPicture(String source, String dstFilename) {

        File src = new File(source);
        System.out.println("aa");
        File dest = new File("ProfilePic/" + dstFilename);

        System.out.println("bb");
        try {
            Files.copy(src.toPath(), dest.toPath(), REPLACE_EXISTING);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getPath() {
        return this.path;
    }
}

package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;

import seedu.address.commons.exceptions.IllegalValueException;

public class Picture {

    public static final String DEFAULT_PATH = "images/default.png";
    public static final String  MESSAGE_PICTURE_CONSTRAINTS =
            "Filepath must be valid, and point to an image file"; //size
    public static final String PICTURE_VALIDATION_REGEX = "[^\\s].*";
    public final String PICTURE_DIRECTORY  = "ProfilePic/";
    public final String MESSAGE_USAGE = "dummy";
    private String path;

    public Picture() {
        this.path = DEFAULT_PATH;
    }

    public Picture(String path) {
        this.path = path;
    }
    public Picture(String path, String newPictureName) {

        requireNonNull(path);
        checkArgument(isValidPath(path), MESSAGE_PICTURE_CONSTRAINTS);
        String extension = "";

        int i = path.lastIndexOf('.');
        if (i > 0) {
            extension = path.substring(i+1);
        }
        this.path = "ProfilePicture/" + newPictureName + "." + extension;

        createNewPicture(path, newPictureName + "." + extension);

    }

    public boolean isValidPath(String path) {

        if (!path.matches(PICTURE_VALIDATION_REGEX)) {
            return false;
        }
        return isValidPicture(path);
    }

    public static boolean isValidPicture(String path) {

        File f = new File(path);
        String mimetype= new MimetypesFileTypeMap().getContentType(f);
        String type = mimetype.split("/")[0];
        return type.equals("image");
    }

    public void createNewPicture(String source, String dstFilename) {

        File src = new File(source);
        System.out.println("aa");
        File dest = new File("ProfilePic/"+dstFilename);

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

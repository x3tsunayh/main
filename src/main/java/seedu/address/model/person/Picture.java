package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;

import seedu.address.commons.exceptions.IllegalValueException;

public class Picture {

    public static final String DEFAULT_PATH = "ProfilePic/nophoto.png";
    public static final String  MESSAGE_PICTURE_CONSTRAINTS =
            "Filepath must be valid, and point to an image file"; //size
    public static final String PICTURE_VALIDATION_REGEX = "[^\\s].*";

    String path;

    public Picture() {
        this.path = DEFAULT_PATH;
    }

    public Picture(String path, String newPictureName) {

        requireNonNull(path);
        checkArgument(isValidPath(path), MESSAGE_PICTURE_CONSTRAINTS);
        this.path = createNewPicture(path, newPictureName);

    }

    public boolean isValidPath(String path) {

        if (!path.matches(PICTURE_VALIDATION_REGEX)) {
            return false;
        }
        return isValidPicture(path);
    }

    public boolean isValidPicture(String path) {

        File f = new File(path);
        String mimetype= new MimetypesFileTypeMap().getContentType(f);
        String type = mimetype.split("/")[0];
        return type.equals("image");
    }

    public String createNewPicture (String path, String newPictureName) {
        int width = 12;
        int height = 12;
        BufferedImage bf = null;
        File f = null;
        String dst;
        try{
            f = new File(path);
            bf = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            bf = ImageIO.read(f);

            dst = "resources/ProfilePic/"+newPictureName+".jpg";
            f = new File(dst);

            ImageIO.write(bf, "jpg", f);
        } catch (Exception e) {
            dst = DEFAULT_PATH;
        }

        return dst;
    }
}

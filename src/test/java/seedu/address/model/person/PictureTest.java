package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;



public class PictureTest {

    public static final String VALID_LOCAL_IMAGE_PNG = "src/main/resources/images/help_icon.png";
    public static final String VALID_LOCAL_IMAGE_JPG = "src/test/resources/PictureTest/yonghe.jpg";
    public static final String VALID_LOCAL_IMAGE_5MB = "src/test/resources/PictureTest/5mbTestJpg.jpg";
    public static final String VALID_LOCAL_IMAGE_BIGGER_THAN_5MB =
            "src/test/resources/PictureTest/5.6mbTestJpg.jpg";

    public static final String INVALID_LOCAL_FILE_NONIMAGE =
            "src/test/resources/PictureTest/nonImageFile.txt";
    public static final String INVALID_LOCAL_FILE_NONIMAGE_WITH_IMAGE_FILETYPE =
            "src/test/resources/PictureTest/nonImageFileWithJpgPrefix.jpg";

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Picture(null));
    }

    @Test
    public void constructor_invalidPath_throwsIllegalArgumentException() {
        String invalidName =
                "RandomRandomThisIsTooLongToBeValidButToBeSureLetsMakeThisLonger_MakePicturesGreatAgain!?>>:?"
                        + "SurelyThisCantBeValidRight?WellLifeIsUnpredictableSoToBeSafeIWillMakeThisEvenLonger";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Picture(invalidName));
    }

    @Test
    public void isValidPath() {
        // null name
        Assert.assertThrows(NullPointerException.class, () -> Picture.isValidPath(null));

        // invalid name
        assertFalse(Picture.isValidPath("")); // empty string
        assertFalse(Picture.isValidPath(" ")); // spaces only
        // points to non image file with image filetype (jpg)
        assertFalse(Picture.isValidPath(INVALID_LOCAL_FILE_NONIMAGE_WITH_IMAGE_FILETYPE));
        assertFalse(Picture.isValidPath(INVALID_LOCAL_FILE_NONIMAGE)); // points to non image file
        assertFalse(Picture.isValidPath(VALID_LOCAL_IMAGE_BIGGER_THAN_5MB)); // points to image file bigger than 5mb

        // valid name
        assertTrue(Picture.isValidPath(VALID_LOCAL_IMAGE_PNG)); // valid png file
        assertTrue(Picture.isValidPath(VALID_LOCAL_IMAGE_JPG)); // valid png file
        assertTrue(Picture.isValidPath(VALID_LOCAL_IMAGE_5MB)); // valid jpg around 5mb (test size restriction)
    }

}

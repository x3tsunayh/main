package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.person.Picture;


/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";
    private static final String BROKEN_IMAGE_URL = "images/imageFail.png";
    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private ImageView imageView;

    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        person.getTags().forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        initProfilePic(person);
    }


    public void initProfilePic(Person person) {

        //String filePath = person.getPicture().getPath();
       // if (filePath == null) {
        /*System.out.println((getClass().getClassLoader().getResource("images/default.png")).toExternalForm());
        Image x = new Image((getClass().getClassLoader().getResource("images/default.png")).toExternalForm());
        if (x==null) System.out.println("NULLLLLLLLLL");
            imageView.setImage(x);*/
       // }
      //  imageView.setImage(new Image(filePath, 12, 12, true, false));
        //imageView = new ImageView((getClass().getClassLoader().getResource("images/default.png")).toExternalForm());
        //System.out.println(imageView);
        String url = person.getPicture().getPath();
        try {
            imageView.setImage(new Image(url, 128, 128, true, false));
        } catch (Exception e) {
            imageView.setImage(new Image(BROKEN_IMAGE_URL, 128, 128, true, false));
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}



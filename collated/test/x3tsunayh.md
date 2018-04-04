# x3tsunayh
###### \java\seedu\address\logic\commands\AddEventCommandTest.java
``` java

public class AddEventCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddEventCommand(null);
    }

    @Test
    public void execute_addEventSuccessful() throws Exception {
        ModelStubAcceptingEventAdded modelStub = new ModelStubAcceptingEventAdded();
        Event validEvent = new EventBuilder().build();

        CommandResult commandResult = getAddEventCommandForEvent(validEvent, modelStub).execute();

        assertEquals(String.format(AddEventCommand.MESSAGE_SUCCESS, validEvent), commandResult.feedbackToUser);
        assertEquals(Arrays.asList(validEvent), modelStub.eventsAdded);
    }

    @Test
    public void equals() {
        Event test1 = new EventBuilder().withTitle("Test 1").build();
        Event test2 = new EventBuilder().withTitle("Test 2").build();
        AddEventCommand addCommandTest1 = new AddEventCommand(test1);
        AddEventCommand addCommandTest2 = new AddEventCommand(test2);

        // same object -> returns true
        assertTrue(addCommandTest1.equals(addCommandTest1));
        assertTrue(addCommandTest2.equals(addCommandTest2));

        // different types -> returns false
        assertFalse(addCommandTest1.equals(1));

        // null -> returns false
        assertFalse(addCommandTest1.equals(null));

        // different event -> returns false
        assertFalse(addCommandTest1.equals(addCommandTest2));
    }

    /**
     * Generates a new AddEventCommand with the details of the given event.
     */
    private AddEventCommand getAddEventCommandForEvent(Event event, Model model) {
        AddEventCommand command = new AddEventCommand(event);
        command.setData(model, new CommandHistory(), new UndoRedoStack());
        return command;
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void addPerson(Person person) {
            fail("This method should not be called.");
        }

        @Override
        public void resetData(ReadOnlyAddressBook newData) {
            fail("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            fail("This method should not be called.");
            return null;
        }

        @Override
        public ReadOnlyEventBook getEventBook() {
            return null;
        }

        @Override
        public void deletePerson(Person target) {
            fail("This method should not be called.");
        }

        @Override
        public void updatePerson(Person target, Person editedPerson) {
            fail("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            fail("This method should not be called.");
            return null;
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            fail("This method should not be called.");
        }

        @Override
        public void sortPersons() {
            fail("This method should not be called.");
        }

        @Override
        public void addTask(Task task) {
            fail("This method should not be called.");
        }

        @Override
        public void deleteTask(Task target) {
            fail("This method should not be called.");
        }

        @Override
        public void updateTask(Task target, Task editedTask) {
            fail("This method should not be called.");
        }

        @Override
        public void sortTasksByPriority() {
            fail("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            fail("This method should not be called.");
            return null;
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            fail("This method should not be called.");
        }

        @Override
        public void addEvent(ReadOnlyEvent toAdd) {
            fail("This method should not be called.");
        }

        @Override
        public void deleteEvent(ReadOnlyEvent eventToDelete) {
            fail("This method should not be called.");
        }

        @Override
        public ObservableList<ReadOnlyEvent> getFilteredEventList() {
            return null;
        }

        @Override
        public void updateFilteredEventList(Predicate<ReadOnlyEvent> predicate) {
            fail("This method should not be called.");
        }
    }

    /**
     * A Model stub that always accept the event being added.
     */
    private class ModelStubAcceptingEventAdded extends ModelStub {
        final ArrayList<ReadOnlyEvent> eventsAdded = new ArrayList<>();

        @Override
        public void addEvent(ReadOnlyEvent toAdd) {
            requireNonNull(toAdd);
            eventsAdded.add(toAdd);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
```
###### \java\seedu\address\logic\commands\CommandTestUtil.java
``` java
    public static final String VALID_XML_FILEPATH = "validXmlFile.xml";
    public static final String VALID_CSV_FILEPATH = "validCsvFile.csv";
    public static final String INVALID_XML_FILEPATH = "invalidXmlFile.xmll";
    public static final String INVALID_CSV_FILEPATH = "invalidCsvFile.csvv";
    public static final String EXISTING_XML_FILEPATH = "existingXmlFile.xml";
    public static final String EXISTING_CSV_FILEPATH = "existingCsvFile.csv";
    public static final String VALID_TITLE_CNY = "CNY Celebration 2018";
    public static final String VALID_TITLE_CHRISTMAS = "Christmas Celebration 2018";
    public static final String VALID_DESCRIPTION_CNY = "CNY Celebration at FOS";
    public static final String VALID_DESCRIPTION_CHRISTMAS = "Christmas Party at SOC";
    public static final String VALID_LOCATION_CNY = "NUS S16 Level 3";
    public static final String VALID_LOCATION_CHRISTMAS = "NUS COM1";
    public static final String VALID_DATETIME_CNY = "15-02-2018 1000";
    public static final String VALID_DATETIME_CHRISTMAS = "24-12-2018 1830";

    public static final String TITLE_DESC_CNY = " " + PREFIX_EVENT_TITLE + VALID_TITLE_CNY;
    public static final String TITLE_DESC_CHRISTMAS = " " + PREFIX_EVENT_TITLE + VALID_TITLE_CHRISTMAS;
    public static final String DESCRIPTION_DESC_CNY = " " + PREFIX_EVENT_DESCRIPTION + VALID_DESCRIPTION_CNY;
    public static final String DESCRIPTION_DESC_CHRISTMAS = " "
            + PREFIX_EVENT_DESCRIPTION + VALID_DESCRIPTION_CHRISTMAS;
    public static final String LOCATION_DESC_CNY = " " + PREFIX_EVENT_LOCATION + VALID_LOCATION_CNY;
    public static final String LOCATION_DESC_CHRISTMAS = " " + PREFIX_EVENT_LOCATION + VALID_LOCATION_CHRISTMAS;
    public static final String DATETIME_DESC_CNY = " " + PREFIX_EVENT_DATETIME + VALID_DATETIME_CNY;
    public static final String DATETIME_DESC_CHRISTMAS = " " + PREFIX_EVENT_DATETIME + VALID_DATETIME_CHRISTMAS;

    // '~' not allowed at the start of field input
    public static final String INVALID_DATETIME_DESC = " "
            + PREFIX_EVENT_DATETIME + "32-12-2018 2359"; //There is no 32 in the date

```
###### \java\seedu\address\logic\commands\DeleteEventCommandTest.java
``` java

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code DeleteEventCommand}.
 */
public class DeleteEventCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() throws Exception {
        ReadOnlyEvent eventToDelete = model.getFilteredEventList().get(Index.fromOneBased(1).getZeroBased());
        DeleteEventCommand deleteEventCommand = prepareCommand(Index.fromOneBased(1));

        String expectedMessage = String.format(DeleteEventCommand.MESSAGE_DELETE_EVENT_SUCCESS, eventToDelete);

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());
        ReadOnlyEvent expectedEventToDelete = expectedModel.getFilteredEventList()
                .get(Index.fromOneBased(1).getZeroBased());
        expectedModel.deleteEvent(expectedEventToDelete);

        assertCommandSuccess(deleteEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteEventCommand deleteEventCommand = prepareCommand(outOfBoundIndex);

        assertCommandFailure(deleteEventCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() throws Exception {
        ReadOnlyEvent event = model.getEventBook().getEventList().get(0);
        model.updateFilteredEventList(new TitleContainsKeywordsPredicate(Arrays.asList(event.getTitle())));
        // checks if filtered event list only has one event here
        assert model.getFilteredEventList().size() == 1;

        ReadOnlyEvent eventToDelete = model.getFilteredEventList().get(Index.fromOneBased(1).getZeroBased());
        DeleteEventCommand deleteEventCommand = prepareCommand(Index.fromOneBased(1));

        String expectedMessage = String.format(DeleteEventCommand.MESSAGE_DELETE_EVENT_SUCCESS, eventToDelete);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());
        ReadOnlyEvent expectedEventToDelete = expectedModel.getFilteredEventList()
                .get(Index.fromOneBased(1).getZeroBased());
        expectedModel.deleteEvent(expectedEventToDelete);
        showNoEvent(expectedModel);

        assertCommandSuccess(deleteEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_failure() {
        ReadOnlyEvent event = model.getEventBook().getEventList().get(0);
        model.updateFilteredEventList(new TitleContainsKeywordsPredicate(Arrays.asList(event.getTitle())));
        // checks if filtered event list only has one event here
        assert model.getFilteredEventList().size() == 1;

        Index outOfBoundIndex = Index.fromOneBased(2);
        // ensures outOfBoundIndex is still within size of Event Book
        assertTrue(outOfBoundIndex.getZeroBased() < model.getEventBook().getEventList().size());

        DeleteEventCommand deleteEventCommand = prepareCommand(outOfBoundIndex);

        assertCommandFailure(deleteEventCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteEventCommand deleteFirstEventCommand = new DeleteEventCommand(Index.fromOneBased(1));
        DeleteEventCommand deleteSecondEventCommand = new DeleteEventCommand(Index.fromOneBased(2));

        // same object -> returns true
        assertTrue(deleteFirstEventCommand.equals(deleteFirstEventCommand));

        // same values -> returns true
        DeleteEventCommand deleteFirstEventCommandCopy = new DeleteEventCommand(Index.fromOneBased(1));
        assertTrue(deleteFirstEventCommand.equals(deleteFirstEventCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstEventCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstEventCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstEventCommand.equals(deleteSecondEventCommand));
    }

    /**
     * Returns a {@code DeleteCommand} with the parameter {@code index}.
     */
    private DeleteEventCommand prepareCommand(Index index) {
        DeleteEventCommand deleteEventCommand = new DeleteEventCommand(index);
        deleteEventCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        return deleteEventCommand;
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoEvent(Model model) {
        model.updateFilteredEventList(p -> false);

        assert model.getFilteredEventList().isEmpty();
    }
}
```
###### \java\seedu\address\logic\commands\ExportCommandTest.java
``` java

public class ExportCommandTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    private Storage storage;
    private Model model;

    @Before
    public void setUp() {
        AddressBookStorage addressBookStorage = new XmlAddressBookStorage(getFilePath("addressbook.xml"));
        XmlEventBookStorage eventBookStorage = new XmlEventBookStorage(getFilePath("eb.xml"));
        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getFilePath("preferences.json"));

        storage = new StorageManager(addressBookStorage, eventBookStorage, userPrefsStorage);
        model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());
    }

    @Test
    public void execute_validXmlFilePath_success() {
        String filePath = getFilePath(VALID_XML_FILEPATH);
        ExportCommand command = prepareCommand(filePath);
        String expectedMessage = String.format(ExportCommand.MESSAGE_EXPORT_SUCCESS, filePath);

        assertCommandSuccess(command, expectedMessage, filePath);
    }

    @Test
    public void execute_validCsvFilePath_success() {
        String filePath = getFilePath(VALID_CSV_FILEPATH);
        ExportCommand command = prepareCommand(filePath);
        String expectedMessage = String.format(ExportCommand.MESSAGE_EXPORT_SUCCESS, filePath);

        assertCommandSuccess(command, expectedMessage, filePath);
    }

    @Test
    public void execute_invalidXmlFileExtension_throwsCommandException() {
        String filePath = getFilePath(INVALID_XML_FILEPATH);
        ExportCommand command = prepareCommand(filePath);
        String expectedMessage = String.format(ExportCommand.MESSAGE_NOT_XML_CSV_FILE);

        assertCommandFailure(command, expectedMessage, filePath);
    }

    @Test
    public void execute_invalidCsvFileExtension_throwsCommandException() {
        String filePath = getFilePath(INVALID_CSV_FILEPATH);
        ExportCommand command = prepareCommand(filePath);
        String expectedMessage = String.format(ExportCommand.MESSAGE_NOT_XML_CSV_FILE);

        assertCommandFailure(command, expectedMessage, filePath);
    }

    @Test
    public void execute_existingXmlName_throwsCommandException() {
        String filePath = getFilePath(EXISTING_XML_FILEPATH);
        ExportCommand command = prepareCommand(filePath);
        try {
            storage.exportAddressBook(model.getAddressBook(), EXISTING_XML_FILEPATH);
        } catch (ExistingFileException e) {
            //do nothing if correct exception is thrown
        } catch (IOException | InvalidFileException e) {
            throw new AssertionError("The expected CommandException was not thrown.", e);
        }
    }

    @Test
    public void execute_existingCsvName_throwsCommandException() {
        String filePath = getFilePath(EXISTING_CSV_FILEPATH);
        ExportCommand command = prepareCommand(filePath);
        try {
            storage.exportAddressBook(model.getAddressBook(), EXISTING_CSV_FILEPATH);
        } catch (ExistingFileException e) {
            //do nothing if correct exception is thrown
        } catch (IOException | InvalidFileException e) {
            throw new AssertionError("The expected CommandException was not thrown.", e);
        }

    }


    private String getFilePath(String fileName) {

        return testFolder.getRoot().getPath() + fileName;
    }

    /**
     * Returns an {@code ExportCommand} with parameter {@String filePath}
     */
    private ExportCommand prepareCommand(String filePath) {
        ExportCommand exportCommand = new ExportCommand(filePath);
        exportCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        exportCommand.setStorage(storage);
        return exportCommand;
    }

    /**
     * Asserts that {@code command} is successfully executed, and<br>
     *     - the command feedback is equal to {@code expectedMessage}<br>
     *     - the {@code FilteredList<Person>} is equal to {@code expectedList}<br>
     *     - the {@code AddressBook} in model remains the same after executing the {@code command}
     */
    private void assertCommandSuccess(ExportCommand command, String expectedMessage, String filePath) {
        try {
            CommandResult result = command.execute();
            assertEquals(expectedMessage, result.feedbackToUser);
            // Storage does not support CSV readability for addressbook data.
            if (!FileUtil.isValidCsvFile(filePath)) {
                assertEquals(model.getAddressBook(), new AddressBook(storage.readAddressBook(filePath).get()));
            }
        } catch (CommandException | DataConversionException | IOException | JAXBException e) {
            throw new AssertionError("Export Command is not working as expected.", e);
        }
    }

    /**
     * Asserts that {@code command} is executed, but<br>
     *     - the correct CommandException is thrown<br>
     */
    public void assertCommandFailure(ExportCommand command, String expectedMessage, String filePath) {
        try {
            command.execute();
            fail("The expected CommandException was not thrown.");
        } catch (CommandException e) {
            assertEquals(expectedMessage, e.getMessage());
            assertFalse((new File(filePath)).exists());
        }
    }

}
```
###### \java\seedu\address\logic\commands\FindEventCommandTest.java
``` java

/**
 * Contains integration tests (interaction with the Model) for {@code FindEventCommand}.
 */
public class FindEventCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());

    @Test
    public void equals() {
        TitleContainsKeywordsPredicate firstPredicate =
                new TitleContainsKeywordsPredicate(Collections.singletonList("Event One"));
        TitleContainsKeywordsPredicate secondPredicate =
                new TitleContainsKeywordsPredicate(Collections.singletonList("Event Two"));

        FindEventCommand findFirstCommand = new FindEventCommand(firstPredicate);
        FindEventCommand findSecondCommand = new FindEventCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindEventCommand findFirstCommandCopy = new FindEventCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noEventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0);
        FindEventCommand command = prepareCommand(" ");
        assertCommandSuccess(command, expectedMessage, Collections.emptyList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 3);
        FindEventCommand command = prepareCommand("CNY Christmas Movie");
        assertCommandSuccess(command, expectedMessage, Arrays.asList(CNY, CHRISTMAS, MOVIE));
    }

    /**
     * Parses {@code userInput} into a {@code FindEventCommand}.
     */
    private FindEventCommand prepareCommand(String userInput) {
        FindEventCommand command =
                new FindEventCommand(new TitleContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+"))));
        command.setData(model, new CommandHistory(), new UndoRedoStack());
        return command;
    }

    /**
     * Asserts that {@code command} is successfully executed, and<br>
     *     - the command feedback is equal to {@code expectedMessage}<br>
     *     - the {@code FilteredList<Event>} is equal to {@code expectedList}<br>
     *     - the {@code EventBook} in model remains the same after executing the {@code command}
     */
    private void assertCommandSuccess(FindEventCommand command, String expectedMessage,
                                      List<ReadOnlyEvent> expectedList) {
        EventBook expectedEventBook = new EventBook(model.getEventBook());
        CommandResult commandResult = command.execute();

        assertEquals(expectedMessage, commandResult.feedbackToUser);
        assertEquals(expectedList, model.getFilteredEventList());
        assertEquals(expectedEventBook.toString(), model.getEventBook().toString());
    }
}
```
###### \java\seedu\address\logic\commands\ListAllEventsCommandTest.java
``` java

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListAllEventsCommand.
 */
public class ListAllEventsCommandTest {

    private Model model;
    private Model expectedModel;
    private ListAllEventsCommand listEventCommand;

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());

        listEventCommand = new ListAllEventsCommand();
        listEventCommand.setData(model, new CommandHistory(), new UndoRedoStack());
    }

    @Test
    public void execute_listNotFiltered_showsSameList() {
        String expectedMessage = String.format("All "
                + getMessageForEventListShownSummary(expectedModel.getFilteredEventList().size()));
        assertCommandSuccess(listEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        ReadOnlyEvent event = model.getEventBook().getEventList().get(0);
        model.updateFilteredEventList(new TitleContainsKeywordsPredicate(Arrays.asList(event.getTitle())));
        String expectedMessage = String.format("All "
                + getMessageForEventListShownSummary(expectedModel.getFilteredEventList().size()));

        // ensures only one event in the filtered event list
        assert model.getFilteredEventList().size() == 1;

        assertCommandSuccess(listEventCommand, model, expectedMessage, expectedModel);
    }
}
```
###### \java\seedu\address\logic\parser\DeleteEventCommandParserTest.java
``` java

public class DeleteEventCommandParserTest {

    private DeleteEventCommandParser parser = new DeleteEventCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteEventCommand() {
        assertParseSuccess(parser, "1", new DeleteEventCommand(Index.fromOneBased(1)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "one", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteEventCommand.MESSAGE_USAGE));
    }
}
```
###### \java\seedu\address\logic\parser\FindEventCommandParserTest.java
``` java

public class FindEventCommandParserTest {

    private FindEventCommandParser parser = new FindEventCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        // empty input not allowed
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindEventCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        FindEventCommand expectedFindCommand =
                new FindEventCommand(new TitleContainsKeywordsPredicate(Arrays.asList("CNY", "Christmas")));
        // multiple keywords allowed
        assertParseSuccess(parser, "et/CNY Christmas", expectedFindCommand);

        // multiple whitespaces allowed
        assertParseSuccess(parser, "et/ \n CNY \n \t Christmas  \t", expectedFindCommand);
    }
}
```
###### \java\seedu\address\model\event\UniqueEventListTest.java
``` java

public class UniqueEventListTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        UniqueEventList uniqueEventList = new UniqueEventList();
        thrown.expect(UnsupportedOperationException.class);
        uniqueEventList.asObservableList().remove(0);
    }
}
```
###### \java\seedu\address\model\EventBookTest.java
``` java

public class EventBookTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final EventBook eventBook = new EventBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), eventBook.getEventList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        eventBook.resetData(null);
    }

    @Test
    public void resetData_withValidReadOnlyEventBook_replacesData() {
        EventBook newData = getTypicalEventBook();
        eventBook.resetData(newData);
        assertEquals(newData, eventBook);
    }

    @Test
    public void resetData_withDuplicateEvents_throwsAssertionError() {
        // Repeated events should throw AssertionError
        List<Event> newEvents = Arrays.asList(new Event(CNY), new Event(CNY));
        EventBookStub newData = new EventBookStub(newEvents);

        thrown.expect(AssertionError.class);
        eventBook.resetData(newData);
    }

    @Test
    public void getEventList_modifyList_throwsUnsupportedOperationException() {
        // Event List should not be altered unknowingly by unsupported operations
        thrown.expect(UnsupportedOperationException.class);
        eventBook.getEventList().remove(0);
    }

    /**
     * A stub ReadOnlyEventBook whose events lists can violate interface constraints.
     */
    private static class EventBookStub implements ReadOnlyEventBook {
        private final ObservableList<ReadOnlyEvent> events = FXCollections.observableArrayList();

        EventBookStub(Collection<? extends ReadOnlyEvent> events) {
            this.events.setAll(events);
        }

        @Override
        public ObjectProperty<String> titleProperty() {
            return null;
        }

        @Override
        public String getTitle() {
            return null;
        }

        @Override
        public ObjectProperty<String> descriptionProperty() {
            return null;
        }

        @Override
        public String getDescription() {
            return null;
        }

        @Override
        public ObjectProperty<String> locationProperty() {
            return null;
        }

        @Override
        public String getLocation() {
            return null;
        }

        @Override
        public ObjectProperty<String> datetimeProperty() {
            return null;
        }

        @Override
        public String getDatetime() {
            return null;
        }

        @Override
        public ObservableList<ReadOnlyEvent> getEventList() {
            return events;
        }
    }
}
```
###### \java\seedu\address\testutil\TypicalEvents.java
``` java

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {

    public static final ReadOnlyEvent CNY = new EventBuilder().withTitle("CNY")
            .withDescription("CNY Celebration at FOS").withLocation("NUS S16 Level 3")
            .withDatetime("15-02-2018 1000").build();
    public static final ReadOnlyEvent CHRISTMAS = new EventBuilder().withTitle("Christmas")
            .withDescription("Christmas Party at SOC").withLocation("NUS COM1")
            .withDatetime("24-12-2018 1830").build();
    public static final ReadOnlyEvent MOVIE = new EventBuilder().withTitle("Movie Outing")
            .withDescription("Black Panther Movie").withLocation("Suntec GV")
            .withDatetime("21-04-2018 1500").build();
    public static final ReadOnlyEvent REUNION = new EventBuilder().withTitle("Class Reunion")
            .withDescription("With Secondary School Classmates").withLocation("Samantha's House")
            .withDatetime("06-05-2018 1730").build();

    private TypicalEvents() {
        // prevents instantiation
    }

    /**
     * Returns an {@code EventBook} with all the typical events.
     */
    public static EventBook getTypicalEventBook() {
        EventBook eb = new EventBook();
        for (ReadOnlyEvent event : getTypicalEvents()) {
            try {
                eb.addEvent(event);
            } catch (CommandException e) {
                assert false : "Invalid Command";
            }
        }
        return eb;
    }

    public static List<ReadOnlyEvent> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(CNY, CHRISTMAS, MOVIE, REUNION));
    }
}
```

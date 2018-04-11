# jill858
###### \data\XmlSerializableAddressBookTest\typicalAddressBook.xml
``` xml
    <persons>
        <name>Harry Styles</name>
        <phone>84821222</phone>
        <email>harry@example.com</email>
        <address>chinatown street</address>
        <tagged>colleagues</tagged>
        <tagged>family</tagged>
    </persons>
    <persons>
        <name>Ian Kurz</name>
        <phone>94839221</phone>
        <email>ian@example.com</email>
        <address>cross road 10</address>
        <tagged>classmates</tagged>
        <tagged>owesMoney</tagged>
    </persons>
    <persons>
        <name>Keith Loh</name>
        <phone>84123922</phone>
        <email>keith@example.com</email>
        <address>5th avenue</address>
        <tagged>classmates</tagged>
    </persons>
    <tags>friends</tags>
    <tags>owesMoney</tags>
    <tasks>
        <taskName>TaskOne</taskName>
        <taskPriority>medium</taskPriority>
        <taskDescription>Tasks to be done for task 1</taskDescription>
        <taskDueDate>2018-06-15</taskDueDate>
        <taskStatus>undone</taskStatus>
        <categorised>work</categorised>
    </tasks>
    <tasks>
        <taskName>TaskTwo</taskName>
        <taskPriority>high</taskPriority>
        <taskDescription>Agenda for task 2</taskDescription>
        <taskDueDate>2018-03-28</taskDueDate>
        <taskStatus>undone</taskStatus>
        <categorised>personal</categorised>
    </tasks>
    <tasks>
        <taskName>TaskThree</taskName>
        <taskPriority>low</taskPriority>
        <taskDescription>Purchase office supplies</taskDescription>
        <taskDueDate>2018-04-10</taskDueDate>
        <taskStatus>done</taskStatus>
        <categorised>work</categorised>
    </tasks>
    <taskCategories>work</taskCategories>
    <taskCategories>personal</taskCategories>
</addressbook>
```
###### \java\seedu\address\logic\commands\ConvertCommandTest.java
``` java
public class ConvertCommandTest {
    @Test
    public void equals() {
        ConvertCommand convertFirstCommand = new ConvertCommand("SGD", "USD", 0.76);
        ConvertCommand convertSecondCommand = new ConvertCommand("USD", "SGD", 1.31);

        // same object -> returns true
        assertTrue(convertFirstCommand.equals(convertFirstCommand));

        // same values -> returns true
        ConvertCommand convertFirstCommandCopy = new ConvertCommand("SGD", "USD", 0.76);
        assertTrue(convertFirstCommand.equals(convertFirstCommandCopy));

        // different types -> returns false
        assertFalse(convertFirstCommand.equals(1));

        // null -> returns false
        assertFalse(convertFirstCommand.equals(null));

        // different value -> returns false
        assertFalse(convertFirstCommand.equals(convertSecondCommand));
    }

    @Test
    public void execute_convertCurrency_validOutput() {
        double baseRate = 1.00;

        //convert 1 SGD to USD
        String expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "SGD", baseRate, "USD", 0.76);
        ConvertCommand command = new ConvertCommand("SGD", "USD", 1.00);
        assertCommandResult(command, expectedMessage);

        //convert 1 SGD to AUD
        expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "SGD", baseRate, "AUD", 0.991);
        command = new ConvertCommand("SGD", "AUD", 1.00);
        assertCommandResult(command, expectedMessage);

        //convert 1 SGD to CAD
        expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "SGD", baseRate, "CAD", 0.981);
        command = new ConvertCommand("SGD", "CAD", 1.00);
        assertCommandResult(command, expectedMessage);

        //convert 1 SGD to CHF
        expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "SGD", baseRate, "CHF", 0.724);
        command = new ConvertCommand("SGD", "CHF", 1.00);
        assertCommandResult(command, expectedMessage);

        //convert 1 SGD to CNY
        expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "SGD", baseRate, "CNY", 4.801);
        command = new ConvertCommand("SGD", "CNY", 1.00);
        assertCommandResult(command, expectedMessage);

        //convert 1 SGD to GBP
        expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "SGD", baseRate, "GBP", 0.539);
        command = new ConvertCommand("SGD", "GBP", 1.00);
        assertCommandResult(command, expectedMessage);

        //convert 1 SGD to HKG
        expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "SGD", baseRate, "HKD", 5.998);
        command = new ConvertCommand("SGD", "HKD", 1.00);
        assertCommandResult(command, expectedMessage);

        //convert 1 SGD to ILS
        expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "SGD", baseRate, "ILS", 2.662);
        command = new ConvertCommand("SGD", "ILS", 1.00);
        assertCommandResult(command, expectedMessage);

        //convert 1 SGD to INR
        expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "SGD", baseRate, "INR", 49.587);
        command = new ConvertCommand("SGD", "INR", 1.00);
        assertCommandResult(command, expectedMessage);

        //convert 1 SGD to JPY
        expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "SGD", baseRate, "JPY", 80.847);
        command = new ConvertCommand("SGD", "JPY", 1.00);
        assertCommandResult(command, expectedMessage);

        //convert 1 SGD to MYR
        expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "SGD", baseRate, "MYR", 2.962);
        command = new ConvertCommand("SGD", "MYR", 1.00);
        assertCommandResult(command, expectedMessage);

        //convert 1 SGD to NZD
        expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "SGD", baseRate, "NZD", 1.049);
        command = new ConvertCommand("SGD", "NZD", 1.00);
        assertCommandResult(command, expectedMessage);

        //convert 1 SGD to PHP
        expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "SGD", baseRate, "PHP", 40.063);
        command = new ConvertCommand("SGD", "PHP", 1.00);
        assertCommandResult(command, expectedMessage);

        //convert 1 SGD to SEK
        expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "SGD", baseRate, "SEK", 6.279);
        command = new ConvertCommand("SGD", "SEK", 1.00);
        assertCommandResult(command, expectedMessage);

        //convert 1 SGD to THB
        expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "SGD", baseRate, "THB", 23.840);
        command = new ConvertCommand("SGD", "THB", 1.00);
        assertCommandResult(command, expectedMessage);

        //convert 1 SGD to TWD
        expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "SGD", baseRate, "TWD", 22.285);
        command = new ConvertCommand("SGD", "TWD", 1.00);
        assertCommandResult(command, expectedMessage);

        //convert 10,000 JPY to USD
        expectedMessage = String.format(ConvertCommand.MESSAGE_COMPLETE, "JPY", 10000.00, "USD", 94.5);
        command = new ConvertCommand("JPY", "USD", 10000.00);
        assertCommandResult(command, expectedMessage);
    }

    /**
     * Asserts that the result message from the execution of {@code ConvertCommand} equals to {@code expectedMessage}
     */
    private void assertCommandResult(ConvertCommand convertCommand, String expectedMessage) {
        assertEquals(expectedMessage, convertCommand.execute().feedbackToUser);
    }
}
```
###### \java\seedu\address\logic\commands\FindCommandTest.java
``` java
    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }
```
###### \java\seedu\address\logic\commands\FindCommandTest.java
``` java
    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        FindCommand command = prepareNameCommand(" ");
        assertCommandSuccess(command, expectedMessage, Collections.emptyList());
    }
```
###### \java\seedu\address\logic\commands\FindCommandTest.java
``` java
    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        // multiple name keywords
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 4);
        FindCommand command = prepareNameCommand("Kurz Elle Kunz");
        assertCommandSuccess(command, expectedMessage, Arrays.asList(CARL, ELLE, FIONA, IAN));

        // multiple partial name keywords
        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 5);
        command = prepareNameCommand("Ku El");
        assertCommandSuccess(command, expectedMessage, Arrays.asList(CARL, DANIEL, ELLE, FIONA, IAN));

        // multiple mixed-case name keywords
        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 4);
        command = prepareNameCommand("KuRz ElLe KuNz");
        assertCommandSuccess(command, expectedMessage, Arrays.asList(CARL, ELLE, FIONA, IAN));

        // multiple mixed-case partial name keywords
        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        command = prepareNameCommand("KuN ElL");
        assertCommandSuccess(command, expectedMessage, Arrays.asList(ELLE, FIONA));

        // multiple tag keywords
        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        command = prepareTagCommand("colleagues classmates");
        assertCommandSuccess(command, expectedMessage, Arrays.asList(HARRY, IAN, KEITH));

        // multiple partial tag keywords
        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 4);
        command = prepareTagCommand("c owesM");
        assertCommandSuccess(command, expectedMessage, Arrays.asList(BENSON, HARRY, IAN, KEITH));

        // multiple mixed-case tag keywords
        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        command = prepareTagCommand("colLeagUes clasSMates");
        assertCommandSuccess(command, expectedMessage, Arrays.asList(HARRY, IAN, KEITH));

        // multiple mixed-case partial tag keywords
        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        command = prepareTagCommand("colL owESM");
        assertCommandSuccess(command, expectedMessage, Arrays.asList(BENSON, HARRY, IAN));
    }
```
###### \java\seedu\address\logic\commands\FindCommandTest.java
``` java
    @Test
    public void execute_singleKeywords_multiplePersonsFound() {
        // name keywords
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        FindCommand command = prepareNameCommand("Kurz");
        assertCommandSuccess(command, expectedMessage, Arrays.asList(CARL, IAN));

        //  partial name keywords
        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        command = prepareNameCommand("Ku");
        assertCommandSuccess(command, expectedMessage, Arrays.asList(CARL, FIONA, IAN));

        // mixed-case name keywords
        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        command = prepareNameCommand("KuRz");
        assertCommandSuccess(command, expectedMessage, Arrays.asList(CARL, IAN));

        //  mixed-case partial name keywords
        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        command = prepareNameCommand("kU");
        assertCommandSuccess(command, expectedMessage, Arrays.asList(CARL, FIONA, IAN));

        // tag keywords
        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        command = prepareTagCommand("friends");
        assertCommandSuccess(command, expectedMessage,
                Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));

        //  partial tag keywords
        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        command = prepareTagCommand("class");
        assertCommandSuccess(command, expectedMessage, Arrays.asList(IAN, KEITH));

        //  mixed-case tag keywords
        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        command = prepareTagCommand("oweSMoney");
        assertCommandSuccess(command, expectedMessage, Arrays.asList(BENSON, IAN));

        //  mixed-case partial tag keywords
        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        command = prepareTagCommand("owesM");
        assertCommandSuccess(command, expectedMessage, Arrays.asList(BENSON, IAN));
    }
```
###### \java\seedu\address\logic\commands\FindCommandTest.java
``` java
    /**
     * Parses {@code userInput} into a {@code FindCommand} for a name search.
     */
    private FindCommand prepareNameCommand(String userInput) {
        FindCommand command =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+"))));
        command.setData(model, new CommandHistory(), new UndoRedoStack());
        return command;
    }
```
###### \java\seedu\address\logic\commands\FindCommandTest.java
``` java
    /**
     * Parses {@code userInput} into a {@code FindCommand} for a tag search.
     */
    private FindCommand prepareTagCommand(String userInput) {
        FindCommand command =
                new FindCommand(new TagContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+"))));
        command.setData(model, new CommandHistory(), new UndoRedoStack());
        return command;
    }

    /**
     * Asserts that {@code command} is successfully executed, and<br>
     *     - the command feedback is equal to {@code expectedMessage}<br>
     *     - the {@code FilteredList<Person>} is equal to {@code expectedList}<br>
     *     - the {@code AddressBook} in model remains the same after executing the {@code command}
     */
    private void assertCommandSuccess(FindCommand command, String expectedMessage, List<Person> expectedList) {
        AddressBook expectedAddressBook = new AddressBook(model.getAddressBook());
        CommandResult commandResult = command.execute();

        assertEquals(expectedMessage, commandResult.feedbackToUser);
        assertEquals(expectedList, model.getFilteredPersonList());
        assertEquals(expectedAddressBook, model.getAddressBook());
    }
}
```
###### \java\seedu\address\logic\parser\ConvertCommandParserTest.java
``` java
public class ConvertCommandParserTest {

    private ConvertCommandParser parser = new ConvertCommandParser();

    @Test
    public void parse_validArgs_returnsConvertCommand() {
        //standard arguement
        assertParseSuccess(parser, "1 SGD USD", new ConvertCommand("SGD", "USD", 1));

        //retrieving base rate
        assertParseSuccess(parser, "SGD USD", new ConvertCommand("SGD", "USD", 1));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "  ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ConvertCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidCurrencyCode_throwsParseException() {
        //invalid currency code
        assertParseFailure(parser, "SGD US",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ConvertCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "SG USD",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ConvertCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "SG US",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ConvertCommand.MESSAGE_USAGE));

        //invalid number of parameter
        assertParseFailure(parser, "SGD",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ConvertCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "5",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ConvertCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "5 SGD",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ConvertCommand.MESSAGE_USAGE));

        //invalid value
        assertParseFailure(parser, "SGD SGD USD",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ConvertCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "SGD 5 USD",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ConvertCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "SGD SGD 5",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ConvertCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "5 5 5",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ConvertCommand.MESSAGE_USAGE));
    }
}
```
###### \java\seedu\address\logic\parser\FindCommandParserTest.java
``` java
    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindNameCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "n/Alice Bob", expectedFindNameCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "n/Alice \n \t Bob  \t", expectedFindNameCommand);

        FindCommand expectedFindTagCommand =
                new FindCommand(new TagContainsKeywordsPredicate(Arrays.asList("owesMoney", "friends")));

        assertParseSuccess(parser, "t/owesMoney friends", expectedFindTagCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "t/owesMoney\t friends  \t", expectedFindTagCommand);

        FindCommand expectedFindAddressCommand =
                new FindCommand(new AddressContainsKeywordsPredicate(Arrays.asList("clementi", "avenue")));

        assertParseSuccess(parser, "a/clementi avenue", expectedFindAddressCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "a/clementi\t avenue  \t", expectedFindAddressCommand);

        FindCommand expectedFindPhoneCommand =
                new FindCommand(new PhoneContainsKeywordsPredicate(Arrays.asList("94945000")));

        assertParseSuccess(parser, "p/94945000", expectedFindPhoneCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "p/94945000 \t", expectedFindPhoneCommand);
    }

}
```
###### \java\seedu\address\model\person\AddressContainsKeywordsPredicateTest.java
``` java
public class AddressContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("Clementi Avenue");
        List<String> secondPredicateKeywordList = Arrays.asList("Clementi Avenue", "Dover Road");

        AddressContainsKeywordsPredicate firstPredicate =
                new AddressContainsKeywordsPredicate(firstPredicateKeywordList);
        AddressContainsKeywordsPredicate secondPredicate =
                new AddressContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AddressContainsKeywordsPredicate firstPredicateCopy =
                new AddressContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_addressContainsKeywords_returnsTrue() {
        // One keyword
        AddressContainsKeywordsPredicate predicate =
                new AddressContainsKeywordsPredicate(Collections.singletonList("clementi"));
        assertTrue(predicate.test(new PersonBuilder().withAddress("clementi avenue").build()));

        // Multiple keywords
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("clementi", "avenue"));
        assertTrue(predicate.test(new PersonBuilder().withAddress("clementi avenue").build()));

        // Only one matching keyword
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("clementi", "road"));
        assertTrue(predicate.test(new PersonBuilder().withAddress("clementi avenue").build()));

        // One mixed-case keyword
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("ClEMenti"));
        assertTrue(predicate.test(new PersonBuilder().withAddress("clementi avenue").build()));

        // Multiple mixed-case keywords
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("ClEMenti", "AvEnUe"));
        assertTrue(predicate.test(new PersonBuilder().withAddress("clementi avenue").build()));

        // One partial keyword
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("clem"));
        assertTrue(predicate.test(new PersonBuilder().withAddress("clementi avenue").build()));

        // Multiple partial keywords
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("clem", "ave"));
        assertTrue(predicate.test(new PersonBuilder().withAddress("clementi avenue").build()));

        // One partial mixed-case keyword
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("ClEMe"));
        assertTrue(predicate.test(new PersonBuilder().withAddress("clementi avenue").build()));

        // Multiple partial mixed-case keywords
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("ClEM", "AvE"));
        assertTrue(predicate.test(new PersonBuilder().withAddress("clementi avenue").build()));
    }

    @Test
    public void test_addressDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        AddressContainsKeywordsPredicate predicate = new AddressContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withAddress("clementi avenue").build()));

        // Non-matching keyword
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("clementi", "avenue"));
        assertFalse(predicate.test(new PersonBuilder().withAddress("dover road").build()));


        // address not match
        predicate = new AddressContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Alice", "Loop", "Avenue", "classmates"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withTags("friends").build()));
    }
}
```
###### \java\seedu\address\model\person\PhoneContainsKeywordsPredicateTest.java
``` java
public class PhoneContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("94945555");
        List<String> secondPredicateKeywordList = Arrays.asList("94945555", "84845555");

        PhoneContainsKeywordsPredicate firstPredicate = new PhoneContainsKeywordsPredicate(firstPredicateKeywordList);
        PhoneContainsKeywordsPredicate secondPredicate = new PhoneContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PhoneContainsKeywordsPredicate firstPredicateCopy =
                new PhoneContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_phoneContainsKeywords_returnsTrue() {
        // One keyword
        PhoneContainsKeywordsPredicate predicate =
                new PhoneContainsKeywordsPredicate(Collections.singletonList("94945555"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("94945555").build()));

        // Multiple keywords but only one matched
        predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("94945555", "87879000"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("94945555").build()));

        // Partial keyword
        predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("9494"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("94945555").build()));
    }

    @Test
    public void test_phoneDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PhoneContainsKeywordsPredicate predicate = new PhoneContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withPhone("94945555").build()));

        // Non-matching keyword
        predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("94945555"));
        assertFalse(predicate.test(new PersonBuilder().withPhone("98985555").build()));

        // phone not match
        predicate = new PhoneContainsKeywordsPredicate(
                Arrays.asList("94945555", "alice@email.com", "Alice", "Main", "Street", "classmates"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withTags("friends").build()));
    }


}
```
###### \java\seedu\address\model\tag\TagContainsKeywordsPredicateTest.java
``` java
public class TagContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TagContainsKeywordsPredicate firstPredicate = new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        TagContainsKeywordsPredicate secondPredicate = new TagContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagContainsKeywordsPredicate firstPredicateCopy = new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // One keyword
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Collections.singletonList("friends"));
        assertTrue(predicate.test(new PersonBuilder().withTags("friends", "colleagues").build()));

        // Multiple keywords
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("friends", "colleagues"));
        assertTrue(predicate.test(new PersonBuilder().withTags("friends", "colleagues").build()));

        // Only one matching keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("friends", "classmates"));
        assertTrue(predicate.test(new PersonBuilder().withTags("friends", "neighbours").build()));

        // Mixed-case keywords
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("fFiEndS", "coLLeagues"));
        assertTrue(predicate.test(new PersonBuilder().withTags("friends", "colleagues").build()));

        // One partial keyword with multiple tags return
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("fri"));
        assertTrue(predicate.test(new PersonBuilder().withTags("friends", "colleagues").build()));

        // Partial keywords
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("fri", "col"));
        assertTrue(predicate.test(new PersonBuilder().withTags("friends", "colleagues").build()));

    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withTags("friends").build()));

        // Non-matching keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("friends"));
        assertFalse(predicate.test(new PersonBuilder().withTags("colleagues", "family").build()));

        // tag not found
        predicate = new TagContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Alice", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withTags("friends").build()));

        // tag not match
        predicate = new TagContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Alice", "Main", "Street", "classmates"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withTags("friends").build()));
    }
}
```
###### \java\seedu\address\testutil\TypicalPersons.java
``` java
    public static final Person HARRY = new PersonBuilder().withName("Harry Styles").withPhone("84821222")
            .withEmail("harry@example.com").withAddress("chinatown street").withTags("colleagues", "family").build();
    public static final Person IAN = new PersonBuilder().withName("Ian Kurz").withPhone("94839221")
            .withEmail("ian@example.com").withAddress("cross road 10").withTags("classmates", "owesMoney").build();
    public static final Person KEITH = new PersonBuilder().withName("Keith Loh").withPhone("84123922")
            .withEmail("keith@example.com").withAddress("5th avenue").withTags("classmates").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE, HARRY, IAN, KEITH));
    }
}
```

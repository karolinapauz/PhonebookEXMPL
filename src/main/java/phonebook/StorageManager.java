package phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;

public class StorageManager {

    private final String contactFileName = "src/main/resources/contacts.csv";

    public StorageManager() {
        try {
            this.createContactsFile();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void createContactsFile() throws IOException {
        File contactsFile = new File(contactFileName);
        contactsFile.createNewFile();
    }

    public void addContact(Contact contact) throws IOException {
        this.addContactsToFile(contact.toString(), true);
    }

    public ArrayList<Contact> getContacts() throws FileNotFoundException {
        File contactsFile = new File(this.contactFileName);
        Scanner fileReader = new Scanner(contactsFile);

        ArrayList<Contact> contacts = new ArrayList<>();

        while (fileReader.hasNextLine()) {
            String contactRowString = fileReader.nextLine();
            String[] contactDetailsAsArray = contactRowString.split(",");

            if (contactDetailsAsArray.length > 4) continue;
            Contact contact = new Contact(
                    UUID.fromString(contactDetailsAsArray[0]),
                    contactDetailsAsArray[1],
                    contactDetailsAsArray[2],
                    contactDetailsAsArray[3]
            );
            contacts.add(contact);
        }
        return contacts;
    }

    public void overwriteContacts(ArrayList<Contact> contacts) throws IOException {
        String allContactsString = contacts
                .stream()
                .map(Contact::toString)
                .collect(Collectors.joining(""));

        this.addContactsToFile(allContactsString, false);

        /*
        Code above is the same as writing below

        allContactsString = "";
        for(Contact contact : contacts) {
            allContactsString = allContactString + contact.toString();
        }
         */
    }

    private void addContactsToFile(String allContactsString, boolean append) throws IOException {
        FileWriter contactsFile = new FileWriter(this.contactFileName, append);
        contactsFile.write(allContactsString);
        contactsFile.close();
    }
}

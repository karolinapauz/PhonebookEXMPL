package phonebook;

import java.util.ArrayList;
import java.util.Scanner;

public class PhonebookController {

    private final Scanner scanner = new Scanner(System.in);
    private final StorageManager storageManager;
    private ArrayList<Contact> contacts = new ArrayList<>();

    public PhonebookController() {
        this.storageManager = new StorageManager();
        this.syncContacts();
    }

    public void collectAndSaveContact() {
        try {
            this.storageManager.addContact(this.collectContactInfo());
            this.syncContacts();
            System.out.println("Contact Added successfully");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private Contact collectContactInfo() {
        Contact contact = new Contact();

        System.out.println("Enter First Name: ");
        contact.setFirstName(scanner.nextLine());
        System.out.println("Enter Last Name: ");
        contact.setLastName(scanner.nextLine());
        System.out.println("Enter Phone Number: ");
        contact.setPhoneNumber(scanner.nextLine());
        return contact;
    }

    public void showContact(ArrayList<Contact> contacts) {
        contacts = contacts == null ? this.contacts : contacts;

        System.out.println("SN \tName \t Number");
        if (contacts.isEmpty()) {
            System.out.println("No contacts to display");
            return;
        }

        int SN = 1;

        for (Contact contact : contacts) {
            System.out.println(
                    SN + " \t" +
                            contact.getFirstName() + " " +
                            contact.getLastName() + " \t" +
                            contact.getPhoneNumber() + " \t"
            );
            SN++;
        }
    }

    public void displayMenu() {
        System.out.println("Welcome to Phonebook!" +
                "\n1. Add Contact" +
                "\n2. Remove Contact" +
                "\n3. Update Contact" +
                "\n4. Find Contact" +
                "\n5. View All Contacts" +
                "\n6. Exit");
    }

    public void syncContacts() {
        try {
            this.contacts = this.storageManager.getContacts();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeContact() {
        this.showContact(null);
        System.out.println("Enter the contact SN to remove: ");
        try {
            int contactIndex = Integer.parseInt(scanner.nextLine());
            this.contacts.remove(contactIndex - 1);
            this.storageManager.overwriteContacts(this.contacts);
            System.out.println("Contact deleted successfully!");
        } catch (Exception exception) {
            System.out.println("Problem deleting contact " + exception.getClass());
        }
    }

    public void updateContact() {
        try {
            this.showContact(null);
            System.out.println("Enter the contact SN to update: ");
            int contactIndex = Integer.parseInt(scanner.nextLine());
            Contact updatedContactInfo = this.collectContactInfo();
            this.contacts.set(contactIndex - 1, updatedContactInfo);
            this.storageManager.overwriteContacts(this.contacts);
            this.syncContacts();
            System.out.println("Contact updated successfully");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void findContact() {
        System.out.println("Enter the number or name to find: ");
        String contactToFind = scanner.nextLine();

        ArrayList<Contact> foundContacts = new ArrayList<>();

        for (Contact contact : this.contacts) {
            if (contact.toString().toLowerCase().trim().contains(
                    contactToFind.toLowerCase().trim()
            )) ;
            foundContacts.add(contact);
        }
        System.out.println(foundContacts.size() + " contacts found");
        this.showContact(foundContacts);
    }
}
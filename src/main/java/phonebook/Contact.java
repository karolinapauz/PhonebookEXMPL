package phonebook;

import java.util.UUID;

public class Contact {

    private final UUID id;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Contact() {
        this.id = UUID.randomUUID();
    }

    public Contact(UUID id, String firstName, String lastName, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return id + "," + firstName + "," + lastName + "," + phoneNumber + "\n";
    }
}

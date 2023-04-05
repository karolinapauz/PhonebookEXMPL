import phonebook.PhonebookController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        PhonebookController phoneBookController = new PhonebookController();

        Scanner scanner = new Scanner(System.in);

        String userInput = "";

        while (!userInput.equals("6")) {
            phoneBookController.displayMenu();
            userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    phoneBookController.collectAndSaveContact();
                    break;
                case "2":
                    phoneBookController.removeContact();
                    break;
                case "3":
                    phoneBookController.updateContact();
                    break;
                case "4":
                    phoneBookController.findContact();
                    break;
                case "5":
                    phoneBookController.showContact(null);
                    break;

                default:
                    System.out.println("Please enter a menu option which is supported");
            }
        }

        System.out.println("Good buy!");
        System.exit(0);

    }
}

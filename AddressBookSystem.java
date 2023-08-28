import java.io.*;
import java.util.*;

class Contact implements Serializable {
    private String name;
    private String phoneNumber;
    private String emailAddress;

    public Contact(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nPhone: " + phoneNumber + "\nEmail: " + emailAddress;
    }
}

class AddressBook implements Serializable {
    private List<Contact> contacts;

    public AddressBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
    }

    public Contact searchContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Contact contact : contacts) {
            result.append(contact).append("\n-------------------------\n");
        }
        return result.toString();
    }
}

class AddressBookSystem {
    private static final String DATA_FILE = "contacts.dat";

    public static void main(String[] args) {
        AddressBook addressBook = loadAddressBook();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Address Book System");
            System.out.println("1. Add Contact");
            System.out.println("2. Search Contact");
            System.out.println("3. Display All Contacts");
            System.out.println("4. Edit Contact");
            System.out.println("5. Remove Contact");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    addContact(scanner, addressBook);
                    break;
                case 2:
                    searchContact(scanner, addressBook);
                    break;
                case 3:
                    displayContacts(addressBook);
                    break;
                case 4:
                    editContact(scanner, addressBook);
                    break;
                case 5:
                    removeContact(scanner, addressBook);
                    break;
                case 6:
                    saveAddressBook(addressBook);
                    System.out.println("Exiting the application.");
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private static AddressBook loadAddressBook() {
        AddressBook addressBook = new AddressBook();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            addressBook = (AddressBook) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            
        }
        return addressBook;
    }

    private static void saveAddressBook(AddressBook addressBook) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            outputStream.writeObject(addressBook);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addContact(Scanner scanner, AddressBook addressBook) {
        System.out.print("Enter contact name: ");
        String name = scanner.nextLine();

        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter email address: ");
        String emailAddress = scanner.nextLine();

        Contact newContact = new Contact(name, phoneNumber, emailAddress);
        addressBook.addContact(newContact);
        System.out.println("Contact added successfully!");
    }

    private static void searchContact(Scanner scanner, AddressBook addressBook) {
        System.out.print("Enter contact name to search: ");
        String name = scanner.nextLine();

        Contact foundContact = addressBook.searchContact(name);
        if (foundContact != null) {
            System.out.println("Contact found:");
            System.out.println(foundContact);
        } else {
            System.out.println("Contact not found.");
        }
    }

    private static void displayContacts(AddressBook addressBook) {
        List<Contact> contacts = addressBook.getAllContacts();
        if (contacts.isEmpty()) {
            System.out.println("No contacts to display.");
        } else {
            System.out.println("All Contacts:");
            System.out.println(addressBook);
        }
    }

    private static void editContact(Scanner scanner, AddressBook addressBook) {
        System.out.print("Enter contact name to edit: ");
        String name = scanner.nextLine();

        Contact foundContact = addressBook.searchContact(name);
        if (foundContact != null) {
            System.out.println("Contact found:");
            System.out.println(foundContact);

            System.out.println("Enter new contact information:");

            System.out.print("Enter new name (leave empty to keep current): ");
            String newName = scanner.nextLine();
            newName = newName.isEmpty() ? foundContact.getName() : newName;

            System.out.print("Enter new phone number (leave empty to keep current): ");
            String newPhoneNumber = scanner.nextLine();
            newPhoneNumber = newPhoneNumber.isEmpty() ? foundContact.getPhoneNumber() : newPhoneNumber;

            System.out.print("Enter new email address (leave empty to keep current): ");
            String newEmailAddress = scanner.nextLine();
            newEmailAddress = newEmailAddress.isEmpty() ? foundContact.getEmailAddress() : newEmailAddress;

            addressBook.removeContact(foundContact);
            Contact editedContact = new Contact(newName, newPhoneNumber, newEmailAddress);
            addressBook.addContact(editedContact);
            System.out.println("Contact edited successfully!");
        } else {
            System.out.println("Contact not found.");
        }
    }

    private static void removeContact(Scanner scanner, AddressBook addressBook) {
        System.out.print("Enter contact name to remove: ");
        String name = scanner.nextLine();

        Contact foundContact = addressBook.searchContact(name);
        if (foundContact != null) {
            addressBook.removeContact(foundContact);
            System.out.println("Contact removed successfully!");
        } else {
            System.out.println("Contact not found.");
        }
    }
}

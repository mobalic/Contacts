package contacts;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Organization extends Records implements Serializable {
    static Scanner scanner = new Scanner(System.in);
    private String name;
    private String phoneNumber;
    private String address;

    private LocalDateTime timeCreated;
    private LocalDateTime timeLastEdit;

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public LocalDateTime getTimeLastEdit() {
        return timeLastEdit;
    }

    public void setTimeLastEdit(LocalDateTime timeLastEdit) {
        this.timeLastEdit = timeLastEdit;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public Records create() {
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the address: ");
        String address = scanner.nextLine();

        System.out.print("Enter the number: ");
        String phoneNumber = scanner.nextLine();
        String regex = "[+]?([(]?[a-zA-Z0-9]+[)]?)([\\s-]?[a-zA-Z0-9]{2,})*$|[+]?([a-z-A-z0-9]+)([\\s-][(]?[a-zA-z0-9]{2,}[)]?)*$";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (!matcher.matches()) {
            System.out.println("Wrong number format!");
            phoneNumber = null;
        }

        if (phoneNumber == null) {
            phoneNumber = "[no number]";
        }

        if (address == null) {
            address = "[no data]";
        }

        var record = new Organization();
        record.setName(name);
        record.setPhoneNumber(phoneNumber);
        record.setAddress(address);

        record.setTimeCreated(LocalDateTime.now().withNano(0));
        record.setTimeLastEdit(LocalDateTime.now().withNano(0));
        return record;
    }

    @Override
    public void edit() {

        System.out.print("Select a field (name,address ,number): ");
        String fieldSelected = scanner.nextLine();
        switch (fieldSelected) {
            case "name":
                System.out.print("Enter name: ");
                String newName = scanner.nextLine();
                setName(newName);
                break;

            case "number":
                System.out.print("Enter number:");
                String newNumber = scanner.nextLine();
                String regex = "[+]?([(]?[a-zA-Z0-9]+[)]?)([\\s-]?[a-zA-Z0-9]{2,})*$|[+]?([a-z-A-z0-9]+)([\\s-][(]?[a-zA-z0-9]{2,}[)]?)*$";
                Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
                Matcher matcher = pattern.matcher(newNumber);
                if (!matcher.matches()) {
                    System.out.println("Wrong number format!");
                    newNumber = null;
                }
                if (newNumber == null) {
                    newNumber = "[no number]";
                }

                setPhoneNumber(newNumber);
                break;

            case "address":
                System.out.print("Enter the address :");
                String address = scanner.nextLine();
                if (address == null) {
                    address = "[no data]";
                }
                setAddress(address);
            default:
                System.out.println("Invalid choice ");
                break;
        }
        setTimeLastEdit(LocalDateTime.now().withNano(0));
        System.out.println("Saved");
    }


    @Override
    public String toString() {
        return "Organization name: " + getName() + "\n" +
                "Address: " + getAddress() + "\n" +
                "Number: " + getPhoneNumber() + "\n" +
                "Time created: " + getTimeCreated() + "\n" +
                "Time last edit: " + getTimeLastEdit() + "\n";


    }

    @Override
    public String getNameList() {
        return name;
    }
}





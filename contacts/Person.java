package contacts;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person extends Records implements Serializable {

    private static final Scanner scanner = new Scanner(System.in);
    private String name;
    private String surName;
    private String birth;
    private String phoneNumber;
    private String gender;
    private LocalDateTime timeCreated;
    private LocalDateTime timeLastEdit;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


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

    @Override
    public Records create() {
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the surname: ");
        String surName = scanner.nextLine();

        System.out.print("Enter the birth date: ");
        String birth = scanner.nextLine();
        try {
            LocalDate.parse(birth);
        } catch (Exception e) {
            System.out.println("Bad birth date!");
            birth = "[no data]";
        }

        System.out.print("Enter the gender (M, F): ");
        String gender = scanner.nextLine();
        if (!"M".equals(gender) && !"F".equals(gender)) {
            System.out.println("Bad gender!");
            gender = "[no data]";
        }

        System.out.print("Enter the number:");
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

        var record = new Person();
        record.setName(name);
        record.setSurName(surName);
        record.setPhoneNumber(phoneNumber);
        record.setBirth(birth);
        record.setGender(gender);

        record.setTimeCreated(LocalDateTime.now().withNano(0));
        record.setTimeLastEdit(LocalDateTime.now().withNano(0));

        return record;
    }

    @Override
    public void edit() {
        var person = new Person();
        System.out.print("Select a field (name, surname, birth, gender, number): ");
        String fieldSelected = scanner.nextLine();
        switch (fieldSelected) {
            case "name":
                System.out.print("Enter name: ");
                String newName = scanner.nextLine();
                setName(newName);
                break;
            case "surname":
                System.out.print("Enter surname:");
                String newSurname = scanner.nextLine();
                setSurName(newSurname);
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
            case "birth":
                System.out.println("Enter the birth date:");
                String birth = scanner.nextLine();
                try {
                    LocalDate.parse(birth);
                } catch (Exception e) {
                    System.out.println("Bad birth date!");
                    birth = "[no data]";

                }
                setBirth(birth);

                break;
            case "gender":
                System.out.println("Enter the gender (M, F):");
                String gender = scanner.nextLine();
                if (!"M".equals(gender) && !"F".equals(gender)) {
                    System.out.println("Bad gender!");
                    gender = "[no data]";
                }
                setGender(gender);
                break;


            default:
                System.out.println("Invalid choice ");
                break;


        }
        person.setTimeLastEdit(LocalDateTime.now().withNano(0));
        System.out.println("Saved");

    }


    @Override
    public String toString() {
        return "Name: " + getName() + "\n" +
                "Surname: " + getSurName() + "\n" +
                "Birth date: " + getBirth() + "\n" +
                "Gender: " + getGender() + "\n" +
                "Number: " + getPhoneNumber() + "\n" +
                "Time created: " + getTimeCreated() + "\n" +
                "Time last edit: " + getTimeLastEdit() + "\n";
    }

    @Override
    public String getNameList() {
        return name + " " + surName;
    }


}

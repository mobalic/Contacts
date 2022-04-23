package contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecordsState implements Serializable{
    static Scanner scanner = new Scanner(System.in);
    Records currentRecord;
    static   ArrayList<Records> list = new ArrayList<>();

    public Records getCurrentRecord() {
        return currentRecord;
    }

    public void setCurrentRecord(Records currentRecord) {
        this.currentRecord = currentRecord;
    }


    public void add() {
        System.out.print("Enter the type (person, organization): ");
        String choice = scanner.nextLine();
        if ("person".equals(choice)) {
            currentRecord = new Person();
            list.add(currentRecord.create());
            System.out.println("The record added.");



        } else if ("organization".equals(choice)) {
            currentRecord = new Organization();
            list.add(currentRecord.create());
            System.out.println("The record added.");
        } else {
            System.out.println("invalid choice");
        }


    }


    public void info() {
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s \n", i + 1, list.get(i).getNameList());
        }
    }


    public void List() {
        if (list.isEmpty()) {
            System.out.println("No records !");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("%d. %s \n", i + 1, list.get(i).getNameList());
            }
        }
    }


    @Override
    public String toString() {
        return currentRecord.toString();
    }


    public ArrayList Search() {
        int count = 0;
        System.out.print("Enter search query: ");
        ArrayList<Records> newlist = new ArrayList<>();
        String query = scanner.nextLine();
        for (int i = 0; i < list.size(); i++) {
            final Pattern pattern = Pattern.compile(query,Pattern.CASE_INSENSITIVE);
            final Matcher matcher = pattern.matcher(list.get(i).getNameList());

            while (matcher.find()) {
                newlist.add(list.get(i));
            }

        }

        for (int i = 0; i < list.size(); i++) {
            final Pattern pattern = Pattern.compile(query,Pattern.CASE_INSENSITIVE);
            final Matcher matcher = pattern.matcher(list.get(i).getPhoneNumber());

            while (matcher.find()) {
                newlist.add(list.get(i));
            }

        }




        System.out.printf("Found %d results:\n", newlist.size());
        for (int i = 0; i < newlist.size(); i++) {
            System.out.printf("%d. %s \n", i + 1, newlist.get(i).getNameList());
        }

        return newlist;

    }


    void count() {
        System.out.printf("The Phone Book has %d records. ", list.size());
        System.out.println();
    }

}

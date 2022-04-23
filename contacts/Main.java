package contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static RecordsState state = new RecordsState();
    static String filename = "Records.txt";

    public static void main(String[] args) throws Exception {
        File file = new File(filename);
        if (!empty(file)) {
            RecordsState.list = load(filename);
        }
        menu();
    }

    public static void menu() throws IOException {
        while (true) {
            System.out.print("[menu] Enter action (add, list, search, count, exit): ");
            String selection = scanner.nextLine();
            switch (selection) {
                case "add":
                    state.add();
                    System.out.println();
                    break;
                case "list":
                    state.List();
                    System.out.println();
                    System.out.print("[list] Enter action ([number], back): ");
                    String actionList = scanner.nextLine();
                    if (actionList.equals("back")) {
                        System.out.println();
                        menu();
                    } else if (isNumeric(actionList)) {
                        int choice = Integer.parseInt(actionList);
                        Records obj = null;

                        for (int i = 0; i < RecordsState.list.size(); i++) {
                            if (choice - 1 == i) {
                                obj = RecordsState.list.get(i);

                            }
                        }
                        assert obj != null;
                        System.out.println(obj);
                        menuRecord(obj);
                    }
                    System.out.println();
                    break;
                case "search":
                    mainSearch();

                    System.out.println();
                    break;
                case "count":
                    state.count();
                    System.out.println();
                    break;
                case "exit":
                    save(RecordsState.list, "D:\\tasks\\Records2020.txt");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong choice try again");
                    System.out.println();
            }
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static void menuSearch(ArrayList<Records> newlist) throws IOException {
        System.out.print("[search] Enter action ([number], back, again): ");
        String selection = scanner.nextLine();
        if (selection.equals("back")) {
            System.out.println();
            menu();
        } else if (selection.equals("again")) {
            mainSearch();
        } else if (isNumeric(selection)) {
            int choice = Integer.parseInt(selection);
            Records SearchObj = null;
            Records obj;
            for (int i = 0; i < newlist.size(); i++) {
                if (choice - 1 == i) {
                    SearchObj = newlist.get(i);
                }
            }

            obj = RecordsState.list.get(RecordsState.list.indexOf(SearchObj));
            assert obj != null;
            System.out.println(obj);
            menuRecord(obj);
        }
    }


    public static void mainSearch() throws IOException {
        var list = state.Search();
        System.out.println();
        if (!list.isEmpty()) {
            menuSearch(list);
        }

    }


    public static Object LoadFile(String filename) throws IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream(filename);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object obj = ois.readObject();
        ois.close();
        return obj;
    }


    public static void menuRecord(Records obj) throws IOException {
        while (true) {
            System.out.print("[record] Enter action (edit, delete, menu): ");
            String select = scanner.nextLine();
            switch (select) {
                case "edit":
                    obj.edit();
                    System.out.println(obj);
                    break;
                case "delete":
                    RecordsState.list.remove(obj);
                    System.out.println("The record removed!");
                    break;
                case "menu":
                    System.out.println();
                    menu();
                    break;
                default:
                    System.out.println("invalid choice");
                    break;
            }
        }
    }

    public static void save(ArrayList<Records> list, String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(list);
        oos.close();
    }

    public static ArrayList<Records> load(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        ArrayList<Records> newList = (ArrayList<Records>) ois.readObject();
        ois.close();
        return newList;
    }
    public static boolean empty(File file) {
        return !file.exists() || file.length() == 0;
    }
}






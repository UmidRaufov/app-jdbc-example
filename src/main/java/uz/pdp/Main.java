package uz.pdp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataBaseService dataBaseService = new DataBaseService();

        int i = -1;
        while (i != 0) {
            System.out.println("0=>Exit, 1=>Create User, 2=>Read User, 3=>Update User, 4=>Delete User");
            i = scanner.nextInt();
            scanner = new Scanner(System.in);
            switch (i) {
                case 1:
                    System.out.println("Create User Process");

                    System.out.println("Input firstName");
                    String firstName = scanner.nextLine();
                    System.out.println("Input lastName");
                    String lastName = scanner.nextLine();
                    System.out.println("Input userName");
                    String userName = scanner.nextLine();
                    System.out.println("Input password");
                    String password = scanner.nextLine();
                    User user = new User(firstName, lastName, userName, password);
                    dataBaseService.saveUser(user);
                    break;
                case 2:
                    System.out.println("Read User Process");
                    dataBaseService.getUsers();
                    break;
                case 3:
                    System.out.println("Update User Process");

                    System.out.println("Input user's ID:");
                    int id = scanner.nextInt();
                    scanner = new Scanner(System.in);
                    System.out.println("Enter editing firstName");
                    firstName = scanner.nextLine();
                    System.out.println("Enter editing lastName");
                    lastName = scanner.nextLine();
                    System.out.println("Enter editing userName");
                    userName = scanner.nextLine();
                    System.out.println("Enter editing password");
                    password = scanner.nextLine();
                    user = new User(id, firstName, lastName, userName, password);
                    dataBaseService.updateUser(user);
                    break;
                case 4:
                    System.out.println("Delete User Process");
                    System.out.println("Input user's ID:");
                    id = scanner.nextInt();
                    dataBaseService.deleteUsers(id);
                    break;
            }
        }
    }
}

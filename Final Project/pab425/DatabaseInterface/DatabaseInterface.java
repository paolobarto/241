package DatabaseInterface;

import java.io.*;
import java.sql.*;
import java.util.IllegalFormatWidthException;
import java.util.Scanner;
import java.util.function.Function;

import javax.swing.JTable.PrintMode;

public class DatabaseInterface {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        // try {
        // boolean correctPasswordEntered = false;
        // Connection con = null;
        // while (!correctPasswordEntered) {

        // System.out.println("Please input your Oracle username on Edgar1:");
        // String user_name = in.nextLine();

        // System.out.println("Please input your Oracle password on Edgar1:");
        // // designed for inputting password without displaying the password:
        // Console console = System.console();
        // char[] pwd = console.readPassword();
        // try {
        // con =
        // DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241",
        // user_name,
        // new String(pwd));
        // correctPasswordEntered = true;
        // } catch (Exception badLogin) {
        // System.out.println("Incorrect login, try again.");
        // continue;
        // }
        // }
        // } catch (Exception e) {
        // // TODO: handle exception
        // }
        boolean inInterface = true;
        while (inInterface) {

            PrintMenu();
            String interfaceChoice = RegexChecker(in, "[1-4]", "Please Enter a number from 1-4");
            switch (interfaceChoice) {
                case "1":
                    ManagementMenu(in);
                    break;
                case "2":

                    break;
                case "3":

                    break;
                case "4":
                    inInterface = false;
                    break;
            }
        }
    }

    public static void PrintMenu() {
        System.out.println("Enter an Interface Option[1-4]:");
        System.out.println(" [1]: Management Interface");
        System.out.println(" [2]: Employee Interface");
        System.out.println(" [3]: Customer Interface");
        System.out.println(" [4]: Quit");
    }

    public static String RegexChecker(Scanner in, String regex, String returnStatement) {
        boolean enteredCorrectly = false;
        while (!enteredCorrectly) {
            String line = in.nextLine();
            if (line.matches(regex)) {
                return line;
            } else {
                System.out.println(returnStatement);
            }
        }
        return "";
    }

    public static void PrintManagementMenu() {
        System.out.println("\nEnter a Management Option[1-5]:");
        System.out.println(" [1]: Move Car To Different Location");
        System.out.println(" [2]: Create New Discount Group");
        System.out.println(" [3]: Add new Charge");
        System.out.println(" [4]: Add Car to Location");
        System.out.println(" [5]: Quit");
    }

    public static void ManagementMenu(Scanner in) {
        boolean inManagementMenu = true;
        while (inManagementMenu) {
            PrintManagementMenu();
            String line = RegexChecker(in, "[1-5]", "Please enter a number between 1-5");
            switch (line) {
                case "1":
                    System.out.println("Printing list of locations...");
                    // Print List of locations
                    // With location chosen show list of cars at location
                    // select next location
                    // End with saying name of car moved to next location
                    break;
                case "2":
                    System.out.println("Please enter the name of the new Discount Group");
                    // Name of Group to create discount for
                    // Discount amount
                    // End with saying name and discount amount
                    break;
                case "3":
                    System.out.println("Printing list of charges...");
                    // Print list of charge types
                    break;
                case "4":
                    System.out.println("Printing list of locations...");
                    // Print locations
                    // With locations ask for each part of car object
                    // End with saying car added to list
                    break;
                case "5":
                    inManagementMenu = false;
                    break;
            }
        }
    }

    public static void EmployeeMenu(Scanner in) {
        boolean inEmployeeMenu = true;
        while (inEmployeeMenu) {
            PrintManagementMenu();
            String line = RegexChecker(in, "[1-5]", "Please enter a number between 1-5");
            switch (line) {
                case "1":
                    System.out.println("Printing list of locations...");
                    // Print List of locations
                    // With location chosen show list of cars at location
                    // select next location
                    // End with saying name of car moved to next location
                    break;
                case "2":
                    System.out.println("Please enter the name of the new Discount Group");
                    // Name of Group to create discount for
                    // Discount amount
                    // End with saying name and discount amount
                    break;
                case "3":
                    System.out.println("Printing list of charges...");
                    // Print list of charge type
                    break;
                case "4":
                    System.out.println("Printing list of locations...");
                    // Print locations
                    // With locations ask for each part of car object
                    // End with saying car added to list
                    break;
                case "5":
                    inEmployeeMenu = false;
                    break;
            }
        }
    }
}

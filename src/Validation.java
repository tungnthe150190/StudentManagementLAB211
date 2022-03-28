
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author @author tungnthe150190
 */
public class Validation {

    public Scanner scanner = new Scanner(System.in);

    //allow user to input an integer in range [min..max]
    public int inputInt(String mess, int min, int max) {
        System.out.print(mess);
        int number = 0;
        do {
            try {
                number = Integer.parseInt(scanner.nextLine());
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.err.print("Out of range! Please enter in range " + min + " to " + max);
                }
            } catch (NumberFormatException e) {
                System.err.print("Invalid! Please enter an integer number: ");
            }
        } while (true);
    }
    //allow user to input a not null String
    public String inputString(String mess) {
        String string;
        do {
            System.out.print(mess);
            string = scanner.nextLine().trim();
            if (!string.equals("")) {
                return string;
            } else {
                System.err.println("This is required field. Please enter not empty!");
            }
        } while (true);
    }
    //allow user to input a function code (a specify character(Y/N - U/D))
    public String inputFunctionCode(String mess, String s1, String s2) {
        String option;
        do {
            System.out.print(mess);
            option = scanner.nextLine().trim();
            //yes - break to continued; no - exit the function
            if (option.equalsIgnoreCase(s1)) {
                return s1;
            } else if (option.equalsIgnoreCase(s2)) {
                return s2;
            } else {
                System.err.println("Please enter " + s1 + " or " + s2);
            }
        } while (true);
    }
    //allow user to input a valid course
    public String inputCourse(String mess) {
        String course;
        do {
            System.out.print(mess);
            course = scanner.nextLine();
            if (course.equalsIgnoreCase("java")
                    || course.equalsIgnoreCase(".net")
                    || course.equalsIgnoreCase("c/c++")) {
                return course;
            } else {
                System.err.println("There are only 3 course: Java, C/C++ and .NET. Please choose one!");
            }
        } while (true);
    }
    //to check duplicate record of a list student
    public boolean isDuplicateStudent(ArrayList<Student> students, String ID, String course, String semester) {
        for (Student student : students) {
            if (ID.equalsIgnoreCase(student.getID())
                    && course.equalsIgnoreCase(student.getCourse())
                    && semester.equalsIgnoreCase(student.getSemester())) {
                return true;
            }
        }
        return false;
    }
    //to check duplicate record of a list report
    public boolean isDuplicateReport(ArrayList<Report> reports, String ID, String course) {
        for (Report report : reports) {
            if (ID.equalsIgnoreCase(report.getID())
                    && course.equalsIgnoreCase(report.getCourse())) {
                return true;
            }
        }
        return false;
    }
}

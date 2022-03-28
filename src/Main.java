
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author tungnthe150190 J1.L.P0021
 */
public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static Validation validation = new Validation();

    public static String getNameByID(ArrayList<Student> database, String ID) {
        for (Student student : database) {
            if (student.getID().equalsIgnoreCase(ID)) {
                return student.getName();
            }
        }
        return null;
    }

    public static ArrayList getStudentsByID(ArrayList<Student> database, String ID) {
        ArrayList<Student> found = new ArrayList<>();
        for (Student student : database) {
            if (student.getID().equalsIgnoreCase(ID)) {
                found.add(student);
            }
        }
        return found;
    }

    public static ArrayList getStudentsByName(ArrayList<Student> database, String keyWord) {
        ArrayList<Student> found = new ArrayList<>();
        for (Student student : database) {
            if (student.getName().contains(keyWord)) {
                found.add(student);
            }
        }
        return found;
    }

    //allow user to create a list of students
    public static void create(ArrayList<Student> database) {
        do {
            System.out.println("-------------Add Student----------------");
            String id = validation.inputString("Enter id: ").toUpperCase();
            String name = getNameByID(database, id);
            if (name != null) {
                System.out.println("Name: " + name);
            } else {
                name = validation.inputString("Enter name: ").toUpperCase();
            }
            String semester = validation.inputString("Enter semester: ");
            String course = validation.inputCourse("Enter course: ");
            //check duplicate record
            if (!validation.isDuplicateStudent(database, id, course, semester)) {
                database.add(new Student(id, name, semester, course));
                System.out.println("Added student successfully.");
            } else {
                System.err.println("Duplicate record. Please enter a student with different informations");
            }
            //if no. student entered > 10, ask user to continue enter or quit
            if (database.size() >= 10) {
                String option = validation.inputFunctionCode("Do you want to continue? (Y/N): ", "y", "n");
                if (option.equals("n")) {
                    return;
                }
            }
        } while (true);
    }

    //allow user to find and sort by name (accending)
    public static void findNSort(ArrayList<Student> database) {
        //if database is empty, exit the function
        if (database.isEmpty()) {
            System.out.println("Empty database! Please create first!");
            return;
        }

        System.out.println("Enter name to search: ");
        String keyName = scanner.nextLine().toUpperCase();
        //display all the list if enter blank
        if (keyName.isEmpty()) {
            displayStudents(database);
        } else {
            ArrayList<Student> result = getStudentsByName(database, keyName);
            if (result.isEmpty()) {
                System.out.println("There is no student has this name");
            } else {
                //sort by name
                Collections.sort(result);
                displayStudents(result);
            }
        }
    }

    //get a single record in the list of students which found by ID
    public static void displayStudents(ArrayList<Student> foundByID) {
        System.out.println("List record found: ");
        int count = 1;
        System.out.printf("%-10s%-15s%-15s%-15s\n", "Number", "Student name", "semester", "Course Name");
        //display list student found
        for (Student student : foundByID) {
            System.out.printf("%-10d%-15s%-15s%-15s\n", count, student.getName(), student.getSemester(), student.getCourse());
            count++;
        }
    }

    //allow user to find a record by ID and Update/Delete
    public static void updateNDelete(ArrayList<Student> database) {
        //check empty database
        if (database.isEmpty()) {
            System.out.println("The database is empty! Please create first");
            return;
        }
        String idSearch = validation.inputString("Enter ID to find: ").toUpperCase();
        ArrayList<Student> foundByID = getStudentsByID(database, idSearch);
        //check empty of list student found 
        //if not - exit the function
        if (foundByID.isEmpty()) {
            System.err.println("The ID " + idSearch + " is not exist!");
            return;
        }
        //else
        displayStudents(foundByID);
        int choice = validation.inputInt("Enter record which you want to update/delete: ", 1, foundByID.size());
        Student s = foundByID.get(choice - 1);
        Student selected = null;
        for (Student student : database) {
            if(validation.isDuplicateStudent(database, s.getID(), s.getName(), s.getCourse())){
                selected = student;
            }
        }
        if(selected == null){
            System.out.println("An error occur. Please contact the developer!");
            return;
        }

        String name, semester, course; // inf variables
        String option; //select variables
        do {
            option = validation.inputFunctionCode("Do you want Update (U) or Delete (D): ", "u", "d");
            //update
            if (option.equals("u")) {

                name = validation.inputString("Enter name: ").toUpperCase();
                //update name will update all the records of this student
                if (!name.equals(s.getName())) {
                    option = validation.inputFunctionCode("Update name will update all the records "
                            + "of this student. Do you want to continue? (Y/N): ", "y", "n");
                    if (option.equals("y")) {
                        //update all record has the id of s
                        for (Student student : database) {
                            if(student.getID().equals(selected.getID()))
                                student.setName(name);
                        }
                    }
                }

                semester = validation.inputString("Enter semester: ");
                course = validation.inputCourse("Enter course: ");
                //check record exist or not
                if (!validation.isDuplicateStudent(database, s.getID(), course, semester)) {
                    selected.setSemester(semester);
                    selected.setCourse(course);
                
                    System.out.println("Updated successfully.");
                    return;
                } else {
                    System.err.println("Update failed! This student has taken this course in this semester!");
                    option = validation.inputFunctionCode("Do you want to try another? (y/n): ", "y", "n");
                    if (option.equals("n")) {
                        return;
                    }
                }
            } //delete
            else {
                database.remove(selected);
                System.out.println("Deleted successfully!");
                return;
            }
        } while (true);

    }

    //allow user to see the report of the database
    public static void report(ArrayList<Student> database) {
        //check empty database
        if (database.isEmpty()) {
            System.out.println("The database is empty! Please create first");
            return;
        }
        ArrayList<Report> reports = new ArrayList<>();
        int total;
        for (Student student : database) {
            //if student has the same ID and course with another in report list, ignore it
            if (validation.isDuplicateReport(reports, student.getID(), student.getCourse())){
                continue;
            }
            //count total of course
            total = 1;
            for (Student student1 : database) {
                //if two object is the same
                if (student == student1) {
                    continue;
                }
                //else if ID and Name is the same but courses are not
                if (student.getID().equalsIgnoreCase(student1.getID())
                        && student.getCourse().equalsIgnoreCase(student1.getCourse())) {
                    total++;
                }
            }
            reports.add(new Report(total, student.getID(), student.getName(), student.getCourse()));
        }
        //print report
        for (int i = 0; i < reports.size(); i++) {
            System.out.printf("%-15s|%-10s|%-5d\n", reports.get(i).getName(),
                    reports.get(i).getCourse(), reports.get(i).getTotalCourse());
        }
    }

    public static void Menu() {
        System.out.println("WELCOME TO STUDENT MANAGEMENT");
        System.out.println("1. Create");
        System.out.println("2. Find and Sort");
        System.out.println("3. Update/Delete");
        System.out.println("4. Report");
        System.out.println("5. Exit");
        System.out.println("(Please choose 1 to Create, 2 to Find and Sort, 3 to Update/Delete, 4 to Report and 5 to Exit program)");
    }

    public static void main(String[] args) {

        ArrayList<Student> database = new ArrayList<>();
        database.add(new Student("HE150190", "NGUYEN THANH TUNG", "SU21", "Java"));
        database.add(new Student("HE150190", "NGUYEN THANH TUNG", "SU21", ".Net"));
        database.add(new Student("HE150190", "NGUYEN THANH TUNG", "SP21", "Java"));
        database.add(new Student("HE153503", "PHAM VAN BINH", "SU21", "Java"));
        database.add(new Student("HE153503", "PHAM VAN BINH", "SP21", "Java"));

        do {
            Menu();
            int selection = validation.inputInt("Your selection: ", 1, 5);
            switch (selection) {
                case 1:
                    create(database);
                    break;
                case 2:
                    findNSort(database);
                    break;
                case 3:
                    updateNDelete(database);
                    break;
                case 4:
                    report(database);
                    break;
                case 5:
                    return;
            }
        } while (true);
    }
}

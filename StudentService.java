
import java.io.*;
import java.util.*;

public class StudentService {
    private static final String FILE_NAME = "student_data.txt";

    public void addStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Department: ");
        String dept = sc.nextLine();
        System.out.print("Enter Marks: ");
        int marks = Integer.parseInt(sc.nextLine());

        Student student = new Student(id, name, dept, marks);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(student.toString());
            writer.newLine();
            System.out.println("Student added successfully!");
        } catch (IOException e) {
            System.out.println("Error saving student.");
        }
    }

    public void viewStudents() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\n--- All Students ---");
            while ((line = reader.readLine()) != null) {
                Student s = Student.fromString(line);
                System.out.println("ID: " + s.getId() + " | Name: " + s.getName() +
                                   " | Dept: " + s.getDepartment() + " | Marks: " + s.getMarks());
            }
        } catch (IOException e) {
            System.out.println("No students found.");
        }
    }

    public void searchStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Search by ID or Dept: ");
        String key = sc.nextLine().toLowerCase();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                Student s = Student.fromString(line);
                if (s.getId().equalsIgnoreCase(key) || s.getDepartment().equalsIgnoreCase(key)) {
                    System.out.println("ID: " + s.getId() + " | Name: " + s.getName() +
                                       " | Dept: " + s.getDepartment() + " | Marks: " + s.getMarks());
                    found = true;
                }
            }
            if (!found) System.out.println("Student not found.");
        } catch (IOException e) {
            System.out.println("Error searching student.");
        }
    }

    public void updateStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ID to update: ");
        String id = sc.nextLine();

        List<Student> students = loadStudents();
        boolean updated = false;

        for (Student s : students) {
            if (s.getId().equalsIgnoreCase(id)) {
                System.out.print("Enter new Name: ");
                s.setName(sc.nextLine());
                System.out.print("Enter new Department: ");
                s.setDepartment(sc.nextLine());
                System.out.print("Enter new Marks: ");
                s.setMarks(Integer.parseInt(sc.nextLine()));
                updated = true;
                break;
            }
        }

        if (updated) {
            saveStudents(students);
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student ID not found.");
        }
    }

    public void deleteStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ID to delete: ");
        String id = sc.nextLine();

        List<Student> students = loadStudents();
        boolean removed = students.removeIf(s -> s.getId().equalsIgnoreCase(id));

        if (removed) {
            saveStudents(students);
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student ID not found.");
        }
    }

    private List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                students.add(Student.fromString(line));
            }
        } catch (IOException ignored) {}
        return students;
    }

    private void saveStudents(List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                writer.write(s.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving students.");
        }
    }
}

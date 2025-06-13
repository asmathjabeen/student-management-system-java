
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentService service = new StudentService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    service.addStudent();
                    break;
                case "2":
                    service.viewStudents();
                    break;
                case "3":
                    service.searchStudent();
                    break;
                case "4":
                    service.updateStudent();
                    break;
                case "5":
                    service.deleteStudent();
                    break;
                case "6":
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

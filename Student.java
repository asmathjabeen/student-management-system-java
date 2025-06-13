
public class Student {
    private String id;
    private String name;
    private String department;
    private int marks;

    public Student(String id, String name, String department, int marks) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.marks = marks;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public int getMarks() {
        return marks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + department + "," + marks;
    }

    public static Student fromString(String line) {
        String[] parts = line.split(",");
        return new Student(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
    }
}

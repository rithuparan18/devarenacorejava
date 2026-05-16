import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private ArrayList<Student> students;

    public StudentManager() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Student searchStudent(String query) {
        for (Student s : students) {
            if (s.getStudentId().equalsIgnoreCase(query) || s.getName().equalsIgnoreCase(query)) {
                return s;
            }
        }
        return null;
    }

    public boolean updateStudent(String studentId, String name, int age, double grade, String contact) {
        Student s = searchStudent(studentId);
        if (s != null) {
            s.setName(name);
            s.setAge(age);
            s.setGrade(grade);
            s.setContact(contact);
            return true;
        }
        return false;
    }

    public boolean deleteStudent(String studentId) {
        Student s = searchStudent(studentId);
        if (s != null) {
            students.remove(s);
            return true;
        }
        return false;
    }
}
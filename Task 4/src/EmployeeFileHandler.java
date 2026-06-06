import java.io.*;
import java.util.ArrayList;

public class EmployeeFileHandler {
    private static final String FILE_NAME = "data/employees.dat";

    public static void saveToFile(ArrayList<Employee> employees) {
        // Using try-with-resources to ensure streams close automatically
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(employees);
            System.out.println("💾 Employee data successfully saved to system.");
        } catch (IOException e) {
            System.out.println("❌ Critical Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Employee> loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            // Ensure the data directory exists
            file.getParentFile().mkdirs();
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Employee>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("❌ Error loading data. Starting fresh. Error: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
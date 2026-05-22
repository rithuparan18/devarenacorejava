public class ValidationUtils {
    
    public static boolean isValidAge(int age) {
        return age > 0;
    }
    
    public static boolean isValidGrade(double grade) {
        return grade >= 0.0 && grade <= 100.0;
    }
    
    public static boolean isNotEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }
}
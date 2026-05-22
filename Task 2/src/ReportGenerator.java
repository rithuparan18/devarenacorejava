import java.util.ArrayList;

public class ReportGenerator {
    
    public static void printClassSummary(ArrayList<StudentGrade> students, String[] subjects) {
        if (students.isEmpty()) {
            System.out.println("No data available for report.");
            return;
        }

        System.out.println("\n=== PERFORMANCE REPORT ===");
        System.out.println("Total Students: " + students.size());

        System.out.println("\n📊 SUBJECT AVERAGES:");
        for (int i = 0; i < subjects.length; i++) {
            double sum = 0;
            for (StudentGrade student : students) {
                sum += student.getMarks()[i];
            }
            System.out.printf("• %s: %.2f\n", subjects[i], (sum / students.size()));
        }

        System.out.println("\n🏆 TOP PERFORMERS (By Average):");
        students.sort((s1, s2) -> Double.compare(
            GradeCalculator.calculateAverage(s2.getMarks()), 
            GradeCalculator.calculateAverage(s1.getMarks())
        ));
        
        for (int i = 0; i < Math.min(3, students.size()); i++) {
            StudentGrade s = students.get(i);
            double avg = GradeCalculator.calculateAverage(s.getMarks());
            System.out.printf("%d. %s - Average: %.2f (%s)\n", 
                (i + 1), s.getName(), avg, GradeCalculator.getGradeCategory(avg));
        }

        System.out.println("\n📈 GRADE DISTRIBUTION:");
        int[] gradeCounts = new int[5]; // A, B, C, D, F
        for (StudentGrade s : students) {
            String grade = GradeCalculator.getGradeCategory(GradeCalculator.calculateAverage(s.getMarks()));
            switch (grade) {
                case "A": gradeCounts[0]++; break;
                case "B": gradeCounts[1]++; break;
                case "C": gradeCounts[2]++; break;
                case "D": gradeCounts[3]++; break;
                case "F": gradeCounts[4]++; break;
            }
        }
        System.out.println("• A Grade: " + gradeCounts[0] + " students");
        System.out.println("• B Grade: " + gradeCounts[1] + " students");
        System.out.println("• C Grade: " + gradeCounts[2] + " students");
        System.out.println("• D Grade: " + gradeCounts[3] + " students");
        System.out.println("• F Grade: " + gradeCounts[4] + " students");
    }
}
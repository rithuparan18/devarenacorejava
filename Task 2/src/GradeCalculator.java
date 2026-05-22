public class GradeCalculator {
    
    public static double calculateAverage(double[] marks) {
        if (marks == null || marks.length == 0) return 0.0;
        double sum = 0;
        for (double mark : marks) {
            sum += mark;
        }
        return sum / marks.length;
    }

    public static double getHighestMark(double[] marks) {
        double max = marks[0];
        for (double mark : marks) {
            if (mark > max) max = mark;
        }
        return max;
    }

    public static double getLowestMark(double[] marks) {
        double min = marks[0];
        for (double mark : marks) {
            if (mark < min) min = mark;
        }
        return min;
    }

    public static String getGradeCategory(double average) {
        if (average >= 90) return "A";
        else if (average >= 80) return "B";
        else if (average >= 70) return "C";
        else if (average >= 60) return "D";
        else return "F";
    }
}
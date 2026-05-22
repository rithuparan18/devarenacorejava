public class StudentGrade {
    private String name;
    private double[] marks;

    public StudentGrade(String name, int subjectCount) {
        this.name = name;
        this.marks = new double[subjectCount];
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double[] getMarks() { return marks; }
    
    public void setMark(int index, double mark) {
        if (index >= 0 && index < marks.length) {
            this.marks[index] = mark;
        }
    }
}

/**
 *
 * @author @author tungnthe150190
 */
public class Student implements Comparable<Student> {

    private String ID;
    private String name;
    private String semester;
    private String course;

    public Student() {
    }

    public Student(String ID, String name, String semester, String course) {
        this.ID = ID;
        this.name = name;
        this.semester = semester;
        this.course = course;
    }

    public Student(String ID, String name, String course) {
        this.ID = ID;
        this.name = name;
        this.course = course;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public int compareTo(Student s) {
        return this.name.compareTo(s.name);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tungnthe150190
 */
public class Report extends Student {

    private int totalCourse;

    public Report() {
    }

    public Report(int totalCourse, String ID, String name, String course) {
        super(ID, name, course);
        this.totalCourse = totalCourse;
    }

    public int getTotalCourse() {
        return totalCourse;
    }

    public void setTotalCourse(int totalCourse) {
        this.totalCourse = totalCourse;
    }

}

package ua.nure.khmelik.SummaryTask4.entity.data;

import java.util.Date;

import ua.nure.khmelik.SummaryTask4.entity.dbentities.CourseTheme;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Teacher;

public class CourseData {

    private int idCourse;
    private String name;
    private Date start;
    private Date end;
    private CourseTheme theme;
    private Teacher teacher;

    public int getIdCourse() {
	return idCourse;
    }

    public void setIdCourse(int idCourse) {
	this.idCourse = idCourse;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Date getStart() {
	return start;
    }

    public void setStart(Date start) {
	this.start = start;
    }

    public Date getEnd() {
	return end;
    }

    public void setEnd(Date end) {
	this.end = end;
    }

    public CourseTheme getTheme() {
	return theme;
    }

    public void setTheme(CourseTheme theme) {
	this.theme = theme;
    }

    public Teacher getTeacher() {
	return teacher;
    }

    public void setTeacher(Teacher teacher) {
	this.teacher = teacher;
    }

    @Override
    public String toString() {
	return "CourseData [theme=" + theme
		+ ", teacher=" + teacher + "]";
    }
    
    

}

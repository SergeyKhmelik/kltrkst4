package ua.nure.khmelik.SummaryTask4.service;

import java.sql.SQLException;

import ua.nure.khmelik.SummaryTask4.entity.data.JournalData;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.CourseControlPoint;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Mark;

public interface JournalService {

    JournalData getJournal(int idCourse) throws SQLException;

    int addCourseControlPoint(CourseControlPoint point) throws SQLException;

    int deleteCourseControlPoint(int idPoint) throws SQLException;

    int setAnotherDate(CourseControlPoint point) throws SQLException;

    int updateMark(Mark mark) throws SQLException;
    
}

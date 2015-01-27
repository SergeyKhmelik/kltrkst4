package ua.nure.khmelik.SummaryTask4.service;

import ua.nure.khmelik.SummaryTask4.entity.Journal;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.CourseControlPoint;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Mark;

public interface JournalService {

    Journal getJournal(int idCourse);

    int addCourseControlPoint(CourseControlPoint point);

    int deleteCourseControlPoint(int idPoint);

    int setAnotherDate(CourseControlPoint point);

    int updateMark(Mark mark);

}

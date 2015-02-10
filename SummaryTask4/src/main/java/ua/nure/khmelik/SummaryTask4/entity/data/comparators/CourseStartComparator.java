package ua.nure.khmelik.SummaryTask4.entity.data.comparators;

import java.util.Comparator;

import ua.nure.khmelik.SummaryTask4.entity.data.CourseData;

public class CourseStartComparator implements Comparator<CourseData> {

    @Override
    public int compare(CourseData arg0, CourseData arg1) {
	return arg0.getStart().compareTo(arg1.getStart());
    }

}

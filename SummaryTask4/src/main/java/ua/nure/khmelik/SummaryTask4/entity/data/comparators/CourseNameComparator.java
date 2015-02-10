package ua.nure.khmelik.SummaryTask4.entity.data.comparators;

import java.util.Comparator;

import ua.nure.khmelik.SummaryTask4.entity.data.CourseData;

public class CourseNameComparator implements Comparator<CourseData> {

    @Override
    public int compare(CourseData arg0, CourseData arg1) {
	return arg0.getName().compareTo(arg1.getName());
    }

}

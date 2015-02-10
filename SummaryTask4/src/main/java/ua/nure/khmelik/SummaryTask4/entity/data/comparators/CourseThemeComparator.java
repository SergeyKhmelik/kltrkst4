package ua.nure.khmelik.SummaryTask4.entity.data.comparators;

import java.util.Comparator;

import ua.nure.khmelik.SummaryTask4.entity.data.CourseData;

public class CourseThemeComparator implements Comparator<CourseData> {

    @Override
    public int compare(CourseData arg0, CourseData arg1) {
	return arg0.getTheme().getName().compareTo(arg1.getTheme().getName());
    }

}

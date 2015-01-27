package ua.nure.khmelik.SummaryTask4.service;

import java.util.ArrayList;

import ua.nure.khmelik.SummaryTask4.entity.dbentities.Student;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Teacher;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.User;

public interface UserService {

	ArrayList<Teacher> readTeachers();
	
	ArrayList<Student> readStudents();
	
	int addStudent(Student student);
	
	int addTeacher(Teacher teacher);
	
	int updateUser(User user);
	
	int deleteUser(int idUser);
	
	int blockStudent(int idStudent, boolean block);	
	
}

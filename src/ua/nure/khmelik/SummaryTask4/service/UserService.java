package ua.nure.khmelik.SummaryTask4.service;

import java.util.ArrayList;

import ua.nure.khmelik.SummaryTask4.entity.dbentities.Student;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Teacher;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.User;

public interface UserService {

	ArrayList<Teacher> readTeachers();
	
	ArrayList<Student> readStudents();
	
	void addStudent(Student student);
	
	void addTeacher(Teacher teacher);
	
	void updateUser(User user);
	
	void deleteUser(int idUser);
	
	void blockStudent(boolean toBlock, int studentId);	
	
}

package ua.nure.khmelik.SummaryTask4.service;

import java.sql.SQLException;
import java.util.ArrayList;

import ua.nure.khmelik.SummaryTask4.entity.dbentities.Student;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Teacher;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.User;

public interface UserService {

	ArrayList<Teacher> readTeachers() throws SQLException;
	
	ArrayList<Student> readStudents() throws SQLException;
	
	int addStudent(Student student) throws SQLException;
	
	int addTeacher(Teacher teacher) throws SQLException;
	
	int updateUser(User user) throws SQLException;
	
	int deleteUser(int idUser) throws SQLException;
	
	int blockStudent(int idStudent, boolean block) throws SQLException;	
	
}

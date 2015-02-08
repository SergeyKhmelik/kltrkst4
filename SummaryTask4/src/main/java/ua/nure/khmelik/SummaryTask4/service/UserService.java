package ua.nure.khmelik.SummaryTask4.service;

import java.sql.SQLException;
import java.util.ArrayList;

import ua.nure.khmelik.SummaryTask4.entity.data.RoleData;
import ua.nure.khmelik.SummaryTask4.entity.data.StudentData;
import ua.nure.khmelik.SummaryTask4.entity.data.TeacherData;
import ua.nure.khmelik.SummaryTask4.exceptions.NoSuchRoleException;

public interface UserService {

	ArrayList<TeacherData> readTeachers() throws SQLException;
	
	ArrayList<StudentData> readStudents() throws SQLException;
	
	int addStudent(StudentData student) throws SQLException;
	
	int addTeacher(TeacherData teacher) throws SQLException;
	
	int updateStudent(StudentData student) throws SQLException;
	
	int updateTeacher(TeacherData teacher) throws SQLException;
	
	int deleteUser(int idUser) throws SQLException;
	
	int blockStudent(int idStudent, boolean block) throws SQLException;	
	
	RoleData readRole(String roleName) throws SQLException, NoSuchRoleException;
	
	RoleData readRole(int roleId) throws SQLException, NoSuchRoleException;

	boolean validateUserLoginOnDuplicate(String login, int idUser) throws SQLException;
	
	boolean validateUserEmailOnDuplicate(String email, int idUser) throws SQLException;
	
}

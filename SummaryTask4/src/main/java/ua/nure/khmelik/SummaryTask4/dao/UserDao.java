package ua.nure.khmelik.SummaryTask4.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import ua.nure.khmelik.SummaryTask4.entity.dbentities.Student;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Teacher;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.User;

public interface UserDao {

    ArrayList<Student> readStudents(Connection conn) throws SQLException;

    ArrayList<Student> readStudents(Connection conn, int idCourse)
	    throws SQLException;

    ArrayList<Teacher> readTeachers(Connection conn) throws SQLException;

    int createStudent(Connection conn, Student student) throws SQLException;

    int createTeacher(Connection conn, Teacher teacher) throws SQLException;

    int createUser(Connection conn, User user) throws SQLException;

    int updateUser(Connection conn, User user) throws SQLException;

    int deleteUser(Connection conn, int idUser) throws SQLException;

    int blockStudent(Connection conn, int idStudent) throws SQLException;

    int unblockStudent(Connection conn, int idStudent) throws SQLException;
    
    Teacher readTeacher(Connection conn, int idTeacher) throws SQLException;
    
    Student readStudent(Connection conn, int idStudent) throws SQLException;
 
    int updateTeacher(Connection conn, Teacher teacher) throws SQLException;
    
    int updateStudent(Connection conn, Student student) throws SQLException;    
    
}
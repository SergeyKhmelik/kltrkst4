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

    int createStudent(Connection conn, Student st) throws SQLException;

    int createTeacher(Connection conn, Teacher te) throws SQLException;

    int createUser(Connection conn, User u) throws SQLException;

    int updateUser(Connection conn, User u) throws SQLException;

    int deleteUser(Connection conn, int userId) throws SQLException;

    int blockStudent(Connection conn, int studentId) throws SQLException;

    int unblockStudent(Connection conn, int studentId) throws SQLException;
    
}
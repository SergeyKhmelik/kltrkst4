package ua.nure.khmelik.SummaryTask4.service.mysql;

import java.util.ArrayList;

import ua.nure.khmelik.SummaryTask4.dao.UserDao;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Student;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Teacher;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.User;
import ua.nure.khmelik.SummaryTask4.service.UserService;
import ua.nure.khmelik.SummaryTask4.util.TransactionManager;

public class MysqlUserService implements UserService {

    private TransactionManager transactionManager;
    private UserDao userDao;
    
    public MysqlUserService(TransactionManager transactionManager,
	    UserDao userDao) {
	super();
	this.transactionManager = transactionManager;
	this.userDao = userDao;
    }

    @Override
    public ArrayList<Teacher> readTeachers() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ArrayList<Student> readStudents() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void addStudent(Student student) {
	// TODO Auto-generated method stub

    }

    @Override
    public void addTeacher(Teacher teacher) {
	// TODO Auto-generated method stub

    }

    @Override
    public void updateUser(User user) {
	// TODO Auto-generated method stub

    }

    @Override
    public void deleteUser(int idUser) {
	// TODO Auto-generated method stub

    }

    @Override
    public void blockStudent(int idStudent, boolean block) {
	// TODO Auto-generated method stub

    }

}

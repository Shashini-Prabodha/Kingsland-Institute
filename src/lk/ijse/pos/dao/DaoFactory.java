package lk.ijse.pos.dao;

import lk.ijse.pos.bo.custom.impl.CourseBoImpl;
import lk.ijse.pos.bo.custom.impl.RegistrationBoImpl;
import lk.ijse.pos.dao.custom.impl.CourseDAOImpl;
import lk.ijse.pos.dao.custom.impl.QueryDAOImpl;
import lk.ijse.pos.dao.custom.impl.RegistrationDAOImpl;
import lk.ijse.pos.dao.custom.impl.StudentDAOImpl;

public class DaoFactory {
    private static DaoFactory daoFactory;
    private  DaoFactory(){}
    public static DaoFactory getInstance(){
        return (daoFactory==null)? (daoFactory=new DaoFactory()) : daoFactory;}

    public enum DAOType{
            STUDENT,COURSE,REGISTRATION,QUERY
    }

    public <T> T getDao(DAOType type){
        switch (type){
            case COURSE:
                return (T) new CourseDAOImpl();
            case STUDENT:
                return (T) new StudentDAOImpl();
            case REGISTRATION:
                return (T) new RegistrationDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            default:
                return null;
        }
    }
}


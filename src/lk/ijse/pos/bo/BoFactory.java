package lk.ijse.pos.bo;

import lk.ijse.pos.bo.custom.impl.CourseBoImpl;
import lk.ijse.pos.bo.custom.impl.RegistrationBoImpl;
import lk.ijse.pos.bo.custom.impl.StudentBoImpl;
import lk.ijse.pos.dao.DaoFactory;
import lk.ijse.pos.dao.custom.impl.StudentDAOImpl;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory(){}

    public static BoFactory getInstance(){
        return (boFactory==null)? (boFactory=new BoFactory()) : boFactory;
    }
    public enum BOType{
        STUDENT,COURSE,REGISTRATION
    }

    public <T> T getBo(BoFactory.BOType type){
        switch (type){
            case COURSE:
                return (T) new CourseBoImpl();
            case STUDENT:
                return (T) new StudentBoImpl();
            case REGISTRATION:
                return (T) new RegistrationBoImpl();
            default:
                return null;
        }
    }
}


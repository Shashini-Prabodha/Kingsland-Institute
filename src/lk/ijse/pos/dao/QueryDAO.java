package lk.ijse.pos.dao;

import lk.ijse.pos.dto.StudentDTO;
import lk.ijse.pos.entity.Course;
import lk.ijse.pos.entity.Student;

import java.util.List;

public interface QueryDAO extends SuperDAO{
    public int getRegId() throws Exception;
    public List<Student> getCourWiseStu(String code) throws Exception;

}

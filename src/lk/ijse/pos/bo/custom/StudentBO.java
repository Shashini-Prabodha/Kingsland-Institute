package lk.ijse.pos.bo.custom;

import lk.ijse.pos.dto.CourseDTO;
import lk.ijse.pos.dto.StudentDTO;

import java.util.ArrayList;

public interface StudentBO {
    public boolean saveStudent(StudentDTO dto) throws Exception;
    public boolean updateStudent(StudentDTO dto) throws Exception;
    public boolean deleteStudent(String id) throws Exception;
    public StudentDTO getStudent(String id) throws Exception;
    public ArrayList<StudentDTO> getAllStudent() throws Exception;
//    public ArrayList<CourseDTO> getCourseDetail() throws Exception;

}

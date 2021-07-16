package lk.ijse.pos.bo.custom;

import lk.ijse.pos.dto.CourseDTO;
import lk.ijse.pos.dto.StudentDTO;
import lk.ijse.pos.entity.Student;

import java.util.ArrayList;
import java.util.List;

public interface CourseBO {
    public boolean saveCourse(CourseDTO dto) throws Exception;
    public boolean updateCourse(CourseDTO dto) throws Exception;
    public boolean deleteCourse(String id) throws Exception;
    public CourseDTO getCourse(String id) throws Exception;
    public ArrayList<CourseDTO> getAllCourse() throws Exception;
    public List<StudentDTO> getCourWiseStu(String code) throws Exception;
}

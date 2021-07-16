package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.CourseBO;
import lk.ijse.pos.dao.DaoFactory;
import lk.ijse.pos.dao.QueryDAO;
import lk.ijse.pos.dao.custom.CourseDAO;
import lk.ijse.pos.dto.CourseDTO;
import lk.ijse.pos.dto.RegistrationDTO;
import lk.ijse.pos.dto.StudentDTO;
import lk.ijse.pos.entity.Course;
import lk.ijse.pos.entity.Registration;
import lk.ijse.pos.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class CourseBoImpl implements CourseBO {
    private CourseDAO dao= DaoFactory.getInstance().getDao(DaoFactory.DAOType.COURSE);
    private QueryDAO qDao= DaoFactory.getInstance().getDao(DaoFactory.DAOType.QUERY);

    @Override
    public boolean saveCourse(CourseDTO dto) throws Exception {
        return dao.save(new Course(dto.getCode(),dto.getCourseName(),dto.getCourseType(),dto.getDuration()));
    }

    @Override
    public boolean updateCourse(CourseDTO dto) throws Exception {
        return dao.update(new Course(dto.getCode(),dto.getCourseName(),dto.getCourseType(),dto.getDuration()));
    }

    @Override
    public boolean deleteCourse(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public CourseDTO getCourse(String id) throws Exception {
        Course course=dao.get(id);
        return new CourseDTO(course.getCode(),course.getCourseName(),course.getCourseType(),course.getDuration());
    }

    @Override
    public ArrayList<CourseDTO> getAllCourse() throws Exception {
        List<Course> list=dao.getAll();
        ArrayList<CourseDTO> dtoList=new ArrayList<>();
        for (Course course: list) {
            dtoList.add(new CourseDTO(course.getCode(),course.getCourseName(),course.getCourseType(),course.getDuration()));
        }
        return dtoList;
    }

    @Override
    public List<StudentDTO> getCourWiseStu(String code) throws Exception {
        List<Student> list=qDao.getCourWiseStu(code);
        ArrayList<StudentDTO> dtoList=new ArrayList<>();
        for (Student student: list) {
            dtoList.add(new StudentDTO(student.getId(),student.getStudentName(),student.getAddress(),student.getContact(),student.getDob(),student.getGender()));
        }
        return dtoList;
    }
}

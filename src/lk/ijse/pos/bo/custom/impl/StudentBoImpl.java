package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.StudentBO;
import lk.ijse.pos.dao.DaoFactory;
import lk.ijse.pos.dao.custom.StudentDAO;
import lk.ijse.pos.dto.CourseDTO;
import lk.ijse.pos.dto.StudentDTO;
import lk.ijse.pos.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBoImpl implements StudentBO {
    StudentDAO dao= DaoFactory.getInstance().getDao(DaoFactory.DAOType.STUDENT);
    @Override
    public boolean saveStudent(StudentDTO dto) throws Exception {
        return dao.save(new Student(dto.getId(),dto.getStudentName(),dto.getAddress(),dto.getContact(),dto.getDob(),dto.getGender()));
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws Exception {
        return dao.update(new Student(dto.getId(),dto.getStudentName(),dto.getAddress(),dto.getContact(),dto.getDob(),dto.getGender()));
    }

    @Override
    public boolean deleteStudent(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public StudentDTO getStudent(String id) throws Exception {
        Student student=dao.get(id);
        return new StudentDTO(student.getId(),student.getStudentName(),student.getAddress(),student.getContact(),student.getDob(),student.getGender());
    }

    @Override
    public ArrayList<StudentDTO> getAllStudent() throws Exception {
        List<Student> list=dao.getAll();
        ArrayList<StudentDTO> dtoList=new ArrayList<>();
        for (Student student: list) {
            dtoList.add(new StudentDTO(student.getId(),student.getStudentName(),student.getAddress(),student.getContact(),student.getDob(),student.getGender()));
        }
        return dtoList;
    }

}

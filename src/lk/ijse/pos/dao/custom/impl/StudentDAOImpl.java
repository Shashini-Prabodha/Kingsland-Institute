package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.CrudUtil;
import lk.ijse.pos.dao.custom.StudentDAO;
import lk.ijse.pos.entity.Student;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean save(Student student) throws Exception {
        return CrudUtil.execute("INSERT INTO Student VALUES (?,?,?,?,?,?)",student.getId(),student.getStudentName(),
                student.getAddress(),student.getContact(),student.getDob(),student.getGender());
    }

    @Override
    public boolean update(Student student) throws Exception {
        return CrudUtil.execute("UPDATE Student SET studentName=?, Address=?, Contact=?, dob=?, gender=? WHERE Id=?",student.getStudentName(),student.getAddress(),student.getContact(),student.getDob(),student.getGender(),student.getId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.execute("DELETE FROM Student WHERE id=?",id);
    }

    @Override
    public Student get(String id) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Student WHERE id=?",id);
        if(rst.next()){
            return new Student(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getString(5),
                    rst.getString(6));
        }else{
            return null;
        }
    }

    @Override
    public List<Student> getAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Student");
        List<Student> studentList = new ArrayList<>();
        while (rst.next()) {
            studentList.add(
                    new Student(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getString(5),
                    rst.getString(6))
            );
        }
            return studentList;
    }
}

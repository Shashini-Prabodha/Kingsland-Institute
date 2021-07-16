package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.CrudUtil;
import lk.ijse.pos.dao.custom.CourseDAO;
import lk.ijse.pos.entity.Course;
import lk.ijse.pos.entity.Student;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {
    @Override
    public boolean save(Course course) throws Exception {
        return CrudUtil.execute("INSERT INTO Course VALUES(?,?,?,?)",course.getCode(),course.getCourseName(),course.getCourseType(),course.getDuration());
    }

    @Override
    public boolean update(Course course) throws Exception {
        return CrudUtil.execute("UPDATE Course SET courseName=?, courseType=?, duration=? WHERE code=?",course.getCourseName(),course.getCourseType(),course.getDuration(),course.getCode());

    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.execute("DELETE FROM Course WHERE code=?",id);

    }

    @Override
    public Course get(String id) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Course WHERE code=?",id);
        if(rst.next()){
            return new Course(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4));
        }else{
            return null;
        }
    }

    @Override
    public List<Course> getAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Course");
        List<Course> courseList = new ArrayList<>();
        while (rst.next()) {
            courseList.add(
                    new Course(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getString(3),
                            rst.getString(4))
            );
        }
        return courseList;

    }
}

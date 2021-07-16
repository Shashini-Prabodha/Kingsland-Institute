package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.CrudUtil;
import lk.ijse.pos.dao.QueryDAO;
import lk.ijse.pos.dto.StudentDTO;
import lk.ijse.pos.entity.Course;
import lk.ijse.pos.entity.Student;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public int getRegId() throws Exception {
        ResultSet rst=CrudUtil.execute("SELECT regNo FROM Registration ORDER BY code DESC LIMIT 1");
        int id=0;
        if(rst.next()){
            id=Integer.parseInt(rst.getString(1));
        }return id;
    }

    @Override
    public List<Student> getCourWiseStu(String code) throws Exception {
        ResultSet rst=CrudUtil.execute("SELECT s.Id,s.studentName,s.Address,s.Contact,s.dob,s.gender FROM Student s,Course c, Registration r WHERE (c.code=r.code && s.Id=r.Id) and c.code=?",code);
        ArrayList<Student> list=new ArrayList<>();
        while (rst.next()){
            list.add(new Student(rst.getString("Id"),rst.getString("studentName"),
                    rst.getString("Address"),rst.getInt("Contact"),
                    rst.getString("dob"),rst.getString("gender")));
        }
        return list;
    }
}

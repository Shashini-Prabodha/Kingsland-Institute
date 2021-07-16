package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.CrudUtil;
import lk.ijse.pos.dao.custom.CourseDAO;
import lk.ijse.pos.dao.custom.RegistrationDAO;
import lk.ijse.pos.entity.Course;
import lk.ijse.pos.entity.Registration;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDAOImpl implements RegistrationDAO {

    @Override
    public boolean save(Registration registration) throws Exception {
        return CrudUtil.execute("INSERT INTO Registration VALUES(?,?,?,?,?)",registration.getRegNo(),registration.getRegDate(),registration.getRegFee(),registration.getId(),registration.getCode());
    }

    @Override
    public boolean update(Registration registration) throws Exception {
        return CrudUtil.execute("UPDATE Registration SET regDate=?, regFee=?, Id=?, code=? WHERE regNo=?",registration.getRegDate(),registration.getRegFee(),registration.getId(),registration.getCode(),registration.getRegNo());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.execute("DELETE FROM Registration WHERE id=?",id);
    }

    @Override
    public Registration get(String id) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Registration WHERE id=?",id);
        if(rst.next()){
            return new Registration(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getString(4),
                    rst.getString(3));
        }else{
            return null;
        }
    }

    @Override
    public List<Registration> getAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Registration");
        List<Registration> registrationList = new ArrayList<>();
        while (rst.next()) {
            registrationList.add(
                    new Registration(
                            rst.getInt(1),
                            rst.getString(2),
                            rst.getDouble(3),
                            rst.getString(4),
                            rst.getString(3))
            );
        }
        return null;
    }
}

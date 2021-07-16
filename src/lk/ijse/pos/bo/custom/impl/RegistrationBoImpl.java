package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.RegistrationBO;
import lk.ijse.pos.dao.DaoFactory;
import lk.ijse.pos.dao.QueryDAO;
import lk.ijse.pos.dao.custom.RegistrationDAO;
import lk.ijse.pos.dto.RegistrationDTO;
import lk.ijse.pos.dto.StudentDTO;
import lk.ijse.pos.entity.Registration;
import lk.ijse.pos.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class RegistrationBoImpl implements RegistrationBO {

    private RegistrationDAO dao= DaoFactory.getInstance().getDao(DaoFactory.DAOType.REGISTRATION);
    private QueryDAO qDao= DaoFactory.getInstance().getDao(DaoFactory.DAOType.QUERY);

    @Override
    public boolean saveRegistration(RegistrationDTO dto) throws Exception {
        return dao.save(new Registration(dto.getRegNo(),dto.getRegDate(),dto.getRegFee(),dto.getId(),dto.getCode()));
    }

    @Override
    public boolean updateRegistration(RegistrationDTO dto) throws Exception {
        return dao.update(new Registration(dto.getRegNo(),dto.getRegDate(),dto.getRegFee(),dto.getId(),dto.getCode()));
    }

    @Override
    public boolean deleteRegistration(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public RegistrationDTO getRegistration(String id) throws Exception {
        Registration registration=dao.get(id);
        return new RegistrationDTO(registration.getRegNo(),registration.getRegDate(),registration.getRegFee(),registration.getId(),registration.getCode());
    }

    @Override
    public ArrayList<RegistrationDTO> getAllRegistration() throws Exception {
        List<Registration> list=dao.getAll();
        ArrayList<RegistrationDTO> dtoList=new ArrayList<>();
        for (Registration registration: list) {
            dtoList.add(new RegistrationDTO(registration.getRegNo(),registration.getRegDate(),registration.getRegFee(),registration.getId(),registration.getCode()));
        }
        return dtoList;
    }

    @Override
    public int getRegId() throws Exception {
        return qDao.getRegId();
    }

}

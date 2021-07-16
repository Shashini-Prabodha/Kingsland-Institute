package lk.ijse.pos.bo.custom;

import lk.ijse.pos.dto.RegistrationDTO;
import lk.ijse.pos.dto.StudentDTO;

import java.util.ArrayList;

public interface RegistrationBO {
    public boolean saveRegistration(RegistrationDTO dto) throws Exception;
    public boolean updateRegistration(RegistrationDTO dto) throws Exception;
    public boolean deleteRegistration(String id) throws Exception;
    public RegistrationDTO getRegistration(String id) throws Exception;
    public ArrayList<RegistrationDTO> getAllRegistration() throws Exception;
    public int getRegId() throws Exception;
}

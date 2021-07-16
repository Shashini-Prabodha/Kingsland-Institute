package lk.ijse.pos.view.TM;

import com.jfoenix.controls.JFXButton;

public class StudentTM {
    private String Id;
    private String studentName;
    private String address;
    private int contact;
    private String dob;
    private String gender;
    private JFXButton btn;

    public StudentTM(String id, String studentName, String address, int contact, String dob, String gender, JFXButton btn) {
        Id = id;
        this.studentName = studentName;
        this.address = address;
        this.contact = contact;
        this.dob = dob;
        this.gender = gender;
        this.btn = btn;
    }

    public StudentTM() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public JFXButton getBtn() {
        return btn;
    }

    public void setBtn(JFXButton btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "StudentTM{" +
                "Id='" + Id + '\'' +
                ", studentName='" + studentName + '\'' +
                ", address='" + address + '\'' +
                ", contact=" + contact +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", btn=" + btn +
                '}';
    }
}

package lk.ijse.pos.dto;

public class RegistrationDTO {
    private int regNo;
    private String regDate;
    private double regFee;
    private String Id;
    private String code;



    public RegistrationDTO(int regNo, String regDate, double regFee, String id, String code) {
        this.regNo = regNo;
        this.regDate = regDate;
        this.regFee = regFee;
        Id = id;
        this.code = code;
    }

    public int getRegNo() {
        return regNo;
    }

    public void setRegNo(int regNo) {
        this.regNo = regNo;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public double getRegFee() {
        return regFee;
    }

    public void setRegFee(double regFee) {
        this.regFee = regFee;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "regNo='" + regNo + '\'' +
                ", regDate='" + regDate + '\'' +
                ", regFee=" + regFee +
                ", Id='" + Id + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}

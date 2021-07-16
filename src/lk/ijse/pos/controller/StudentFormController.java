package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.pos.bo.BoFactory;
import lk.ijse.pos.bo.custom.CourseBO;
import lk.ijse.pos.bo.custom.RegistrationBO;
import lk.ijse.pos.bo.custom.StudentBO;
import lk.ijse.pos.dao.DaoFactory;
import lk.ijse.pos.dao.QueryDAO;
import lk.ijse.pos.dto.CourseDTO;
import lk.ijse.pos.dto.RegistrationDTO;
import lk.ijse.pos.dto.StudentDTO;
import lk.ijse.pos.entity.Course;
import lk.ijse.pos.view.TM.StudentTM;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class StudentFormController {
    public JFXTextField txtID;
    public JFXTextField txtStudentName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtDOB;
    public RadioButton rbtnMale;
    public RadioButton rbtnFemale;
    public JFXButton btnUpdate;
    public JFXButton btnRegister;
    public TableView<StudentTM> tblStudent;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colDOB;
    public TableColumn colGender;
    public TableColumn colDelete;
    public ToggleGroup group;
    public JFXComboBox cmbCourse;
    public JFXButton btnClear;
    public JFXTextField txtDuration;
    public JFXTextField txtCourseType;
    public Label lblDate;
    public JFXTextField txtCourseName;
    public JFXTextField txtRegFee;
    String gender;

    StudentBO bo= BoFactory.getInstance().getBo(BoFactory.BOType.STUDENT);

    public void initialize() throws Exception {
        setNull();
        genarateOrderDate();
        loadAllCourse();


        colID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadAllStudent();
        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setData(newValue);
        });
    }
    public void genarateOrderDate() {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String orderDate = sdf.format(date);
        lblDate.setText(orderDate);

    }
    private void setData(StudentTM tm) {
        txtID.setText(tm.getId());
        txtContact.setText(tm.getContact()+"");
        txtStudentName.setText(tm.getStudentName());
        txtAddress.setText(tm.getAddress());
        txtDOB.setText(tm.getDob());
        if(tm.getGender().equals("Female")){
            rbtnFemale.setSelected(true);
        }else{
            rbtnMale.setSelected(true);
        }
    }

    private void loadAllStudent() throws Exception {
        ObservableList<StudentTM> tmlist= FXCollections.observableArrayList();
        List<StudentDTO> dtoArrayList= bo.getAllStudent();

        for (StudentDTO dto : dtoArrayList) {
            JFXButton btn = new JFXButton("Delete");
            StudentTM tm = new StudentTM(dto.getId(), dto.getStudentName(), dto.getAddress(), dto.getContact(), dto.getDob(), dto.getGender(), btn);
            tmlist.add(tm);
            btn.setOnAction((e)->{
                try {
                    ButtonType ok= new ButtonType("OK",
                            ButtonBar.ButtonData.OK_DONE);
                    ButtonType no= new ButtonType("NO",
                            ButtonBar.ButtonData.CANCEL_CLOSE);

                    Alert alert= new Alert(Alert.AlertType.CONFIRMATION,
                            "Are You Sure ?",ok,no);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.orElse(no)==ok){
                        if (bo.deleteStudent(tm.getId())) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Deleted", ButtonType.OK).show();
                            loadAllStudent();
                            return;
                        }
                        new Alert(Alert.AlertType.WARNING,
                                "Try Again",ButtonType.OK).show();
                    }else{
                        //no
                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            });
        }
            tblStudent.setItems(tmlist);

    }

    public void txtIDOnAction(ActionEvent actionEvent) throws Exception {
        StudentDTO student = bo.getStudent(txtID.getText());
        if(null!=student){
            txtStudentName.setText(student.getId());
            txtAddress.setText(student.getAddress());
            txtContact.setText(student.getContact()+"");
            txtDOB.setText(student.getDob());
            if(student.getGender().equals("Female")){
                rbtnFemale.setSelected(true);
            }else{
                rbtnMale.setSelected(true);
            }
        }else{
            System.out.println("no");
            new Alert(Alert.AlertType.WARNING,"Student Not Registerd! ").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws Exception {
        try {
            gender = getGender();
            boolean isUpdated = bo.updateStudent(
                    new StudentDTO(txtID.getText(), txtStudentName.getText(), txtAddress.getText(), Integer.parseInt(txtContact.getText()), txtDOB.getText(),
                            gender));
            if (isUpdated) {
                loadAllStudent();
                new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfully",ButtonType.OK).show();
//                loadAllStudent();
            }else{
                new Alert(Alert.AlertType.CONFIRMATION, "Updated Fail !",ButtonType.OK).show();
            }
        }catch ( NullPointerException ex){

        }
    }
    RegistrationBO rbo = BoFactory.getInstance().getBo(BoFactory.BOType.REGISTRATION);

    public void btnRegisterOnAction(ActionEvent actionEvent) throws Exception {
            try {String c = (String) cmbCourse.getValue();
                gender=getGender();
                if (txtAddress.getText() != null && txtID.getText() != null && txtContact.getText()!=null && txtDOB.getText() != null && gender!= null && txtStudentName.getText() != null && c!=null && txtRegFee.getText()!=null) {
                    boolean isSaved = bo.saveStudent(
                            new StudentDTO(txtID.getText(),
                                    txtStudentName.getText(),
                                    txtAddress.getText(),
                                    Integer.parseInt(txtContact.getText()),
                                    txtDOB.getText(),
                                    gender));
                    int rNo=rbo.getRegId();
                    boolean isRegistered=rbo.saveRegistration(new RegistrationDTO((++rNo),lblDate.getText(),Double.parseDouble(txtRegFee.getText()),txtID.getText(),c));
                    if(isSaved && isRegistered) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Registered Successfully").show();
                        loadAllStudent();
                    }
               }else {
                    new Alert(Alert.AlertType.WARNING, "Please Complete All Fields").show();
                }

            }catch (NumberFormatException | NullPointerException ex){
                new Alert(Alert.AlertType.WARNING, "Please Complete All Fields").show();
            }catch (SQLIntegrityConstraintViolationException exception){
                new Alert(Alert.AlertType.WARNING, "Student Already Registered !").show();
            }

    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        setNull();
    }

    public void setNull(){
        txtID.setText(null);
        txtStudentName.setText(null);
        txtDOB.setText(null);
        txtAddress.setText(null);
        txtContact.setText(null);
        txtCourseName.setText(null);
        txtCourseType.setText(null);
        txtDuration.setText(null);
        txtRegFee.setText(null);
    }
    public String getGender(){
        if(rbtnMale.isSelected()){
            return "Male";
        }
        else if(rbtnFemale.isSelected()){
            return "Female";
        }else{
            return null;
        }
    }
    CourseBO cbo=BoFactory.getInstance().getBo(BoFactory.BOType.COURSE);
    //load all course to combo box
    public void loadAllCourse() throws Exception {

        ArrayList<CourseDTO> item = cbo.getAllCourse();
        for (CourseDTO i : item) {
            cmbCourse.getItems().addAll(i.getCode());
        }

    }

    public void setCourseItem(ActionEvent actionEvent) {
        try {
            String c = (String) cmbCourse.getValue();
            CourseDTO d=cbo.getCourse(c);
            System.out.println(d.getCourseName());
            txtCourseType.setText(d.getCourseType());
            txtDuration.setText(d.getDuration());
            txtCourseName.setText(d.getCourseName());

        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

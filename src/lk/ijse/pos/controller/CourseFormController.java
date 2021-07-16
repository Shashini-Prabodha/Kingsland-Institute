package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import lk.ijse.pos.bo.BoFactory;
import lk.ijse.pos.bo.custom.CourseBO;
import lk.ijse.pos.dao.DaoFactory;
import lk.ijse.pos.dto.CourseDTO;
import lk.ijse.pos.dto.StudentDTO;
import lk.ijse.pos.entity.Student;
import lk.ijse.pos.view.TM.CourseTM;
import lk.ijse.pos.view.TM.CourseWiseStudentTM;
import lk.ijse.pos.view.TM.StudentTM;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class CourseFormController {
    public JFXTextField txtCourseCode;
    public JFXTextField txtCourseName;
    public JFXTextField txtDuration;

    public RadioButton rbtnPartTime;
    public RadioButton rbtnFullTime;
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public TableView<CourseTM> tblCourse;
    public TableColumn colCourseCode;
    public TableColumn colCourseName;
    public TableColumn colCourseType;
    public TableColumn colDuration;
    public TableColumn colDelete;
    public TableView<CourseWiseStudentTM> tblCStudent;
    public TableColumn colId;
    public TableColumn colSName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colDOB;
    public TableColumn colGender;
    public JFXComboBox cmbCourseCode;
    public JFXButton btnClear;
    public ToggleGroup group;
    public Label lblDate;
    String type;

    CourseBO bo= BoFactory.getInstance().getBo(BoFactory.BOType.COURSE);

    public void initialize() throws Exception {
        setNull();
        genarateOrderDate();
        loadAllCourseCmb();

        colCourseCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colCourseType.setCellValueFactory(new PropertyValueFactory<>("courseType"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btn"));

        colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colSName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        loadAllCourse();

        tblCourse.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setData(newValue);
        });
    }

    public void genarateOrderDate() {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String orderDate = sdf.format(date);
        lblDate.setText(orderDate);

    }

    private void loadAllCourse() throws Exception {
        ObservableList<CourseTM> tmlist= FXCollections.observableArrayList();
        List<CourseDTO> dtoArrayList= bo.getAllCourse();
//        tmlist.clear();

        for (CourseDTO dto : dtoArrayList) {
            JFXButton btn = new JFXButton("Delete");
            CourseTM tm = new CourseTM(dto.getCode(),dto.getCourseName(),dto.getCourseType(),dto.getDuration(),btn);
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
                        if (bo.deleteCourse(tm.getCode())) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Deleted", ButtonType.OK).show();
                            loadAllCourse();
                            loadAllCourseCmb();
                            return;
                        }
                        new Alert(Alert.AlertType.WARNING,
                                "Try Again",ButtonType.OK).show();
                    }else{}

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            });
        }
        tblCourse.setItems(tmlist);
    }

    private void setData(CourseTM tm) {
        try{
        txtCourseCode.setText(tm.getCode());
        txtCourseName.setText(tm.getCourseName());
        txtDuration.setText(tm.getDuration());
        if(tm.getCourseType().equalsIgnoreCase("Part Time")){
            rbtnPartTime.setSelected(true);
        }else{
            rbtnFullTime.setSelected(true);
        }}catch (NullPointerException e){}
    }
    public void txtCourseCodeOnAction(ActionEvent actionEvent) throws Exception, InvocationTargetException {
        CourseDTO course = bo.getCourse(txtCourseCode.getText());
        if(null!=course){
            txtCourseName.setText(course.getCourseName());
            txtDuration.setText(course.getDuration());
            if(course.getCourseType().equalsIgnoreCase("Part Time")){
                rbtnPartTime.setSelected(true);
            }else{
                rbtnFullTime.setSelected(true);
            }
        }else{
            System.out.println("no");
            new Alert(Alert.AlertType.WARNING,"Course Not Registerd! ").show();
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        try {
            type=getType();
            if (txtCourseCode.getText() != null && txtCourseName.getText() != null && type!= null && txtDuration.getText() != null) {
                boolean isSaved = bo.saveCourse(
                        new CourseDTO(txtCourseCode.getText(),
                                txtCourseName.getText(),
                                type,
                                txtDuration.getText()));
                if(isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Added Successfully").show();
                    loadAllCourse();
                    loadAllCourseCmb();
                    setNull();
                }
            }else {
                new Alert(Alert.AlertType.WARNING, "Please Complete All Fields").show();
            }

        }catch (NumberFormatException | NullPointerException ex){
            new Alert(Alert.AlertType.WARNING, "Please Complete All Fields").show();
        }catch (SQLIntegrityConstraintViolationException exception){
            new Alert(Alert.AlertType.WARNING, "Course Already Registered !").show();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            type = getType();
            boolean isUpdated = bo.updateCourse(
                    new CourseDTO(txtCourseCode.getText(),
                            txtCourseName.getText(),
                            type,
                            txtDuration.getText()));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfully").show();
                loadAllCourse();
            }else{
                new Alert(Alert.AlertType.CONFIRMATION, "Updated Fail !").show();
            }
        }catch (NumberFormatException | NullPointerException ex){

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {setNull();
    }

    public String getType(){
        if(rbtnPartTime.isSelected()){
            return "Part Time";
        }
        else if(rbtnFullTime.isSelected()){
            return "Full Time";
        }else{
            return null;
        }
    }
    public void setNull(){
        rbtnFullTime.setSelected(false);
        rbtnPartTime.setSelected(false);
        txtCourseCode.setText("");
        txtCourseName.setText(null);
        txtDuration.setText(null);
    }

    //load all course to combo box
    public void loadAllCourseCmb() throws Exception {
        cmbCourseCode.getItems().clear();
        ArrayList<CourseDTO> item =bo.getAllCourse();
        for (CourseDTO i : item) {
            cmbCourseCode.getItems().addAll(i.getCode());
        }

    }
    private void loadAllStudents() throws Exception {
        ObservableList<CourseWiseStudentTM> tmlist= FXCollections.observableArrayList();
        List<StudentDTO> dtoArrayList= bo.getCourWiseStu((String) cmbCourseCode.getValue());
        tmlist.clear();
        for (StudentDTO dto : dtoArrayList) {

            CourseWiseStudentTM tm = new CourseWiseStudentTM(dto.getId(), dto.getStudentName(), dto.getAddress(), dto.getContact(), dto.getDob(), dto.getGender());
            tmlist.add(tm);

        }
        tblCStudent.setItems(tmlist);
    }

    public void loadStudentsOnAction(ActionEvent actionEvent) throws Exception {
        loadAllStudents();
    }
}

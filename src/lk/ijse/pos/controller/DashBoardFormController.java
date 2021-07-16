package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardFormController {

    public JFXButton btnStudent;
    public JFXButton btnCourse;
    public AnchorPane root;
    public JFXButton btnExit;
    public JFXTextField txtf;


    public void btnStudentOnAction(ActionEvent actionEvent) throws IOException {
        initUi("StudentForm.fxml");
    }

    public void btnCourseOnAction(ActionEvent actionEvent) throws IOException {
        initUi("CourseForm.fxml");
    }

    public void initialize() throws IOException {
//        initUi("DashBoardForm.fxml");
    }

    private void initUi(String location) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren()
                .add(FXMLLoader.
                        load(this.getClass().getResource("/lk/ijse/pos/view/" +
                                location)));

    }

    public void btnExitOnAction(ActionEvent mouseEvent) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
}

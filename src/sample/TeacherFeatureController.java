package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.event.ActionEvent;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherFeatureController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML private AnchorPane class_button;
    @FXML private Label class_label;

    @FXML private AnchorPane student_button;
    @FXML private Label student_label;

    @FXML private AnchorPane course_button;
    @FXML private Label course_label;

    @FXML private AnchorPane semester_button;
    @FXML private Label semester_label;

    @FXML private AnchorPane acc_button;
    @FXML private Label acc_label;

    @FXML private AnchorPane subject_button;
    @FXML private Label subject_label;

    @FXML private Button account_info;
    @FXML private Button log_out;
    @FXML
    private Label alert;

    public void click_accButton(MouseEvent event) throws IOException {
        alert.setVisible(false);
        FXMLLoader loader= new FXMLLoader(getClass().getResource("TeacherUserTable.fxml"));
        root=loader.load();
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void click_subjectButton(MouseEvent event) throws  IOException{
        alert.setVisible(false);
        FXMLLoader loader= new FXMLLoader(getClass().getResource("subjectTable.fxml"));
        root=loader.load();
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void click_semesterButton(MouseEvent event) throws IOException{
        alert.setVisible(false);
        FXMLLoader loader= new FXMLLoader(getClass().getResource("SemesterTable.fxml"));
        root=loader.load();
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void click_classButton(MouseEvent event) throws IOException
    {
        alert.setVisible(false);
        FXMLLoader loader= new FXMLLoader(getClass().getResource("classTable.fxml"));
        root=loader.load();
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void click_SessionButton(MouseEvent event) throws IOException
    {
        if(Main.currentSemester==null)
        {
            alert.setVisible(true);
            return;
        }
        alert.setVisible(false);
        FXMLLoader loader= new FXMLLoader(getClass().getResource("CourseRegistrationSessionTable.fxml"));
        root=loader.load();
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void click_CourseButton(MouseEvent event) throws IOException
    {
        if(Main.currentSemester==null)
        {
            alert.setVisible(true);
            return;
        }
        alert.setVisible(false);
        FXMLLoader loader= new FXMLLoader(getClass().getResource("CourseTable.fxml"));
        root=loader.load();
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void mouseEnter(MouseEvent e) throws IOException{
        if(e.getSource()==class_button) {
            class_button.setStyle("-fx-background-color:#5F6062");
            class_label.setTextFill(Paint.valueOf("white"));
        }
        else if(e.getSource()==student_button)
        {
            student_button.setStyle("-fx-background-color:#5F6062");
            student_label.setTextFill(Paint.valueOf("white"));
        }
        else if(e.getSource()==course_button)
        {
            course_button.setStyle("-fx-background-color:#5F6062");
            course_label.setTextFill(Paint.valueOf("white"));
        }
        else if(e.getSource()==semester_button)
        {
            semester_button.setStyle("-fx-background-color:#5F6062");
            semester_label.setTextFill(Paint.valueOf("white"));
        }
        else if(e.getSource()==acc_button)
        {
            acc_button.setStyle("-fx-background-color:#5F6062");
            acc_label.setTextFill(Paint.valueOf("white"));
        }
        else
        {
            subject_button.setStyle("-fx-background-color:#5F6062");
            subject_label.setTextFill(Paint.valueOf("white"));
        }

    }
    public void mouseExit(MouseEvent e) throws  IOException{
        if(e.getSource()==class_button) {
            class_button.setStyle("-fx-background-color:none; -fx-border-color:#c0c0c0; -fx-border-width: 10px 5px 5px 5px  ");
            class_label.setTextFill(Paint.valueOf("black"));
        }
        else if(e.getSource()==student_button )
        {
            student_button.setStyle("-fx-background-color:white; -fx-border-color:#c0c0c0; -fx-border-width: 10px 5px 5px 5px  ");
            student_label.setTextFill(Paint.valueOf("black"));
        }
        else if(e.getSource()==course_button)
        {
            course_button.setStyle("-fx-background-color:none; -fx-border-color:#c0c0c0; -fx-border-width: 10px 5px 5px 5px  ");
            course_label.setTextFill(Paint.valueOf("black"));
        }
        else if(e.getSource()==semester_button)
        {
            semester_button.setStyle("-fx-background-color:white; -fx-border-color:#c0c0c0; -fx-border-width: 10px 5px 5px 5px  ");
            semester_label.setTextFill(Paint.valueOf("black"));
        }
        else if(e.getSource()==acc_button)
        {
            acc_button.setStyle("-fx-background-color:none; -fx-border-color:#c0c0c0; -fx-border-width: 10px 5px 5px 5px  ");
            acc_label.setTextFill(Paint.valueOf("black"));
        }
        else
        {
            subject_button.setStyle("-fx-background-color:white; -fx-border-color:#c0c0c0; -fx-border-width: 10px 5px 5px 5px  ");
            subject_label.setTextFill(Paint.valueOf("black"));
        }
    }

    public void click_logOut(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("logIn.fxml"));
        root=loader.load();
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alert.setVisible(false);
    }
}

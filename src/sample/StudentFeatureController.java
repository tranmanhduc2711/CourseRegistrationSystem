package sample;

import dao.SemesterDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentFeatureController   {
   // private Stage stage;
   // private Scene scene;
   // private Parent root;
    @FXML
    private AnchorPane course_but;
    @FXML
    private Label  regis_label;
    @FXML
    private AnchorPane courseList_but;
    @FXML
    private Label  list_label;
    private User currentStudent;
    private Semester currentSem;


    public void setUser(User user) throws IOException {
        currentStudent=user;

    }
    private  void setCurrentSemester() throws IOException
    {
        int sem = SemesterDAO.readFileSemester();
        System.out.print(sem);
        if(sem == -1)
            currentSem = null;
        else
            currentSem = SemesterDAO.getSemesterById(sem);
    }
    public void click_courseButton(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentRegisterCourse.fxml"));
        loader.load();
        StudentRegisterCourseController controller = loader.getController();
        controller.setUser(currentStudent,currentSem);
        Scene scene = new Scene(loader.getRoot());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();


    }
    public void click_courseListButton(MouseEvent event) throws IOException {

//        FXMLLoader loader= new FXMLLoader(getClass().getResource("TeacherUserTable.fxml"));
//        root=loader.load();
//        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
//        scene=new Scene(root);
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stage.show();
    }
    public void mouseEnter(MouseEvent e) throws IOException {
        if(e.getSource()==course_but) {
            course_but.setStyle("-fx-background-color:#5F6062");
            regis_label.setTextFill(Paint.valueOf("white"));
        }
        else if(e.getSource()==courseList_but)
        {
            courseList_but.setStyle("-fx-background-color:#5F6062");
            list_label.setTextFill(Paint.valueOf("white"));
        }

    }
    public void mouseExit(MouseEvent e) throws  IOException{
        if(e.getSource()==course_but) {
            course_but.setStyle("-fx-background-color:none; -fx-border-color:#c0c0c0; -fx-border-width: 10px 5px 5px 5px  ");
            regis_label.setTextFill(Paint.valueOf("black"));
        }
        else if(e.getSource()==courseList_but )
        {
            courseList_but.setStyle("-fx-background-color:white; -fx-border-color:#c0c0c0; -fx-border-width: 10px 5px 5px 5px  ");
            list_label.setTextFill(Paint.valueOf("black"));
        }

    }

    @FXML
    public void setLogout_but(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("logIn.fxml"));
        loader.load();
        Scene scene = new Scene(loader.getRoot());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }



}

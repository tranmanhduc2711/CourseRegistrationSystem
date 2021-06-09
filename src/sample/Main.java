package sample;


import dao.SemesterDAO;
import dao.UserDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;


public class Main extends Application {
     public static Semester currentSemester;
    static
    {
        int sem = 0;
        try {
            sem = SemesterDAO.readFileSemester();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print(sem);
        if(sem == -1)
            currentSemester = null;
        else
            currentSemester = SemesterDAO.getSemesterById(sem);
    }
     public Semester getCurrentSemester()
     {
         return currentSemester;
     }

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            UserDAO.init();
            Parent root = FXMLLoader.load(getClass().getResource("logIn.fxml"));

            primaryStage.setTitle("PORTAL HCMUS");
            Scene login_Scene=new Scene(root);
            primaryStage.setScene(login_Scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {

        launch(args);
//        List<Semester> l= SemesterDAO.getAllSemester();
//        for(Semester item:l)
//        {
//            System.out.println(item.getId());
//        }
    }
}

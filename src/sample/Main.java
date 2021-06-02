package sample;


import dao.SemesterDAO;
import dao.UserDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;


public class Main extends Application {
     public static Semester currentSemester;
     public Semester getCurrentSemester()
     {
         return currentSemester;
     }

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("classTable.fxml"));

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

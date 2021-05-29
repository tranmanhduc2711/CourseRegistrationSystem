package sample;


import dao.UserDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
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
//        List<User> l= UserDAO.getAllUserTeacher();
//        for(User item:l)
//        {
//            System.out.println(item.getUsername());
//        }
    }
}

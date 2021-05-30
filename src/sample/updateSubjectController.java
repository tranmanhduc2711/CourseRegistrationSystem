package sample;

import dao.SubjectDAO;
import dao.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class updateSubjectController implements Initializable {
    @FXML
    private TextField nameText;
    @FXML
    private TextField idText;
    @FXML
    private ComboBox<Integer> credit_box;
    @FXML
    private Label alert1;
    @FXML
    private Label alert2;

    private Subject sub;
    private boolean update = false;

    public void setUpdate(boolean u)
    {
        update = u;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        credit_box.getItems().addAll(1,2,3,4);
    }
    public void setTextField(Subject sub)
    {
        idText.setDisable(true);
        idText.setText(sub.getId());
        if(sub.getName() != null)
            nameText.setText(sub.getName());
        credit_box.getSelectionModel().select(sub.getCredits());
    }


    public void Submit(ActionEvent e)
    {
        alert1.setVisible(false);
        alert2.setVisible(false);
        String id = idText.getText();
        String name = nameText.getText();
        Integer cre=credit_box.getValue();
        if (id.isEmpty() || name.isEmpty() || credit_box.getValue()==null) {
           alert1.setVisible(true);
           return;
        } else {
            sub= new Subject();
            sub.setId(id);
            sub.setName(name);
            sub.setCredits(cre);
        }
        if(!update) {
            if(findSub(sub))
            {
                alert2.setVisible(true);
            }else {
                SubjectDAO.add_Subject(sub);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.close();
            }
        }
        else
        {
            SubjectDAO.updateSubject(sub);
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    private boolean findSub(Subject sub) {
        List<Subject> list = SubjectDAO.getAllSubject();
        for (Subject item : list)
            if (item.getName().equals(sub.getName()))
                return true;
        return false;
    }

}

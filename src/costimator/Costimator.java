package costimator;

import static com.sun.org.apache.bcel.internal.util.SecuritySupport.getResourceAsStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Jobair_Joty
 */
public class Costimator extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
       
        stage.setTitle("Costimator");
        root.getStylesheets().add
                (Costimator.class.getResource("costimator.css").toExternalForm());
        Scene scene = new Scene(root,450,550);
        stage.getIcons().add(new Image(Costimator.class.getResourceAsStream("icon.png")));
        stage.setScene(scene);
        stage.setResizable(false);
       
        stage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}

package game.ui;

import game.model.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Rundom extends Application {

    public static Controller ctrl = new Controller();
     
    @Override
    public void start(Stage stage) throws IOException {
        showWindow("primary.fxml");
    }


    public static void showWindow(String fxml){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Rundom.class.getResource(fxml));
            System.out.println(fxmlLoader);
            Scene scene;
            if(fxml.equals("canvasView.fxml")){
                scene =new Scene(fxmlLoader.load(), 700, 700);
            }else{
                scene =new Scene(fxmlLoader.load(), 773, 500);
            }
            Stage window=new Stage();
            window.setScene(scene);
            window.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch();
    }
}
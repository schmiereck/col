package de.schmiereck.col;

import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
   @Override
   public void start(final Stage stage) throws IOException {
      final FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
      final Scene scene = new Scene(fxmlLoader.load(), 320, 240);
      stage.setTitle("Hello!");

      final BorderPane welcomePane = (BorderPane)scene.lookup("#welcomePane");
      final Canvas welcomeCanvas = (Canvas)scene.lookup("#welcomeCanvas");

      final GraphicsContext graphics = welcomeCanvas.getGraphicsContext2D();

      graphics.setFill(Color.BLUE);
      graphics.fillRect(0, 0, welcomeCanvas.getWidth(), welcomeCanvas.getHeight());

      stage.setScene(scene);
      stage.show();

      DoubleBinding heightBinding = welcomePane.heightProperty()
              .subtract(welcomePane.getBoundsInParent().getHeight());
      welcomeCanvas.widthProperty().bind(welcomePane.widthProperty());
      //welcomeCanvas.heightProperty().bind(heightBinding);
   }

   public static void main(String[] args) {
      launch();
   }
}
module de.schmiereck.col {
   requires javafx.controls;
   requires javafx.fxml;


   opens de.schmiereck.col to javafx.fxml;
   exports de.schmiereck.col;
}
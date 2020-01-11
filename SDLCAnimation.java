package hw4;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



/*
 * goals:
 * create and fill out forms
 * create listeners that generate new windows
 */
public class SDLCAnimation extends Application {
	@Override
	public void start(Stage primaryStage){
		BorderPane pane = new BorderPane();


		VBox VBMethodologies = new VBox();
		VBMethodologies.getChildren().addAll(new Label("Here is a list of methodologies "
				+ "and their explanations"));

		Button btPlanning = new Button("Planning Phase");
		Button btAnalysis = new Button();
		Button btDesign = new Button();
		Button btImplementation = new Button();
		Button btMaintenance = new Button("Maintenance Phase");

		HBox SDLCOptions  = new HBox();
		SDLCOptions.getChildren().addAll(btMaintenance);
		HBox hBox = new HBox(5);
		hBox.getChildren().addAll(new Label(" Begin Learning about the SDLC by selecting a button: "));

		pane.setCenter(SDLCOptions);
		pane.setBottom(hBox);
		pane.setRight(VBMethodologies);

	}
	public static void main(String[] args) {
		launch(args);
	}
}

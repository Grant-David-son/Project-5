package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.Parent;

public class Main extends Application {

	private Label l1;
	private TextField a1;
	private Slider slide;
	private int hd;
	private Button show;
	private TextArea a2;
	private Label l2;
	private ComboBox<String> drop;
	public ArrayList<String> menu;
	private Button calc;
	private TextField labA0;
	private TextField labA1;
	private TextField labA2;
	private TextField labA3;
	private TextField labA4;
	private Label lab0;
	private Label lab1;
	private Label lab2;
	private Label lab3;
	private Label lab4;
	private Button stat;
	private TextField statA;
	public ArrayList<String> stationshd;
	public TextArea a10;


	@Override
	public void start(Stage stage) throws Exception {

		hd = 1;
		ArrayList<Control> kid = new ArrayList<Control>();

		a10 = new TextArea();
		a10.setEditable(false);
		a10.setLayoutX(250);
		a10.setLayoutY(14);
		a10.setMinSize(300, 300);
		a10.setMaxSize(300, 600);
		a10.setWrapText(true);
		kid.add(a10);

		Controller con = new Controller(a10);

		stage.setTitle("Project 5");


		l1 = new Label("Enter Hamming Distance: ");
		l1.setLayoutX(14);
		l1.setLayoutY(10);
		kid.add(l1);

		a1 = new TextField();
		a1.setEditable(false);
		a1.setText(""+hd);
		a1.setLayoutX(160);
		a1.setLayoutY(10);
		a1.setMaxWidth(30);
		kid.add(a1);

		slide = new Slider(1,4,1);;
		slide.setShowTickMarks(true);
		slide.setShowTickLabels(true);
		slide.setMajorTickUnit(1);
		slide.setMinorTickCount(0);
		slide.setSnapToTicks(true);
		slide.setLayoutX(14);
		slide.setLayoutY(30);
		slide.setOnMouseReleased(event -> {
			hd = (int)slide.getValue();
			a1.setText(""+hd);
		});
		kid.add(slide);

		a2 = new TextArea();
		a2.setEditable(false);
		a2.setLayoutX(14);
		a2.setLayoutY(95);
		a2.setMinSize(200, 300);
		a2.setMaxSize(200, 300);
		a2.setWrapText(true);
		kid.add(a2);

		show = new Button("Show Station");
		show.setLayoutX(14);
		show.setLayoutY(65);
		show.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e){
				a10.setText(a10.getText()+ "\n"+ "Show Station clicked, HD: "+hd);
				stationshd = con.getStations(hd, drop.getValue());
				a10.setText(a10.getText()+ "\n"+ "Selected station: "+ drop.getValue());
				String ret = "";
				for(String s: stationshd){
					ret += s +"\n";
				}
				a2.setText(ret);
			}
		});
		kid.add(show);

		l2 = new Label("Compare With: ");
		l2.setLayoutX(14);
		l2.setLayoutY(410);
		kid.add(l2);

		drop = new ComboBox<String>();
		menu = new ArrayList<String>();
		menu.add("WEST");
		for(String s: menu){
			drop.getItems().add(s);
		}
		drop.setLayoutX(130);
		drop.setLayoutY(410);
		//use .getValue();
		kid.add(drop);

		lab0 = new Label("Distance 0: ");
		labA0 = new TextField();
		labA0.setEditable(false);
		lab0.setLayoutX(14);
		lab0.setLayoutY(445);
		labA0.setLayoutX(130);
		labA0.setLayoutY(445);
		labA0.setMaxWidth(50);
		kid.add(lab0);
		kid.add(labA0);

		lab1 = new Label("Distance 1: ");
		labA1 = new TextField();
		labA1.setEditable(false);
		lab1.setLayoutX(14);
		lab1.setLayoutY(475);
		labA1.setLayoutX(130);
		labA1.setLayoutY(475);
		labA1.setMaxWidth(50);
		kid.add(lab1);
		kid.add(labA1);

		lab2 = new Label("Distance 2: ");
		labA2 = new TextField();
		labA2.setEditable(false);
		lab2.setLayoutX(14);
		lab2.setLayoutY(505);
		labA2.setLayoutX(130);
		labA2.setLayoutY(505);
		labA2.setMaxWidth(50);
		kid.add(lab2);
		kid.add(labA2);

		lab3 = new Label("Distance 3: ");
		labA3 = new TextField();
		labA3.setEditable(false);
		lab3.setLayoutX(14);
		lab3.setLayoutY(535);
		labA3.setLayoutX(130);
		labA3.setLayoutY(535);
		labA3.setMaxWidth(50);
		kid.add(lab3);
		kid.add(labA3);

		lab4 = new Label("Distance 4: ");
		labA4 = new TextField();
		labA4.setEditable(false);
		lab4.setLayoutX(14);
		lab4.setLayoutY(565);
		labA4.setLayoutX(130);
		labA4.setLayoutY(565);
		labA4.setMaxWidth(50);
		kid.add(lab4);
		kid.add(labA4);

		calc = new Button("Calculate HD");
		calc.setLayoutX(14);
		calc.setLayoutY(590);
		calc.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e){
				a10.setText(a10.getText()+ "\n"+ "Calculate HD clicked, Comaparing with: " + drop.getValue());
				int[] ham= con.getHDs(drop.getValue());
				labA0.setText("" + ham[0]);
				labA1.setText("" + ham[1]);
				labA2.setText("" + ham[2]);
				labA3.setText("" + ham[3]);
				labA4.setText("" + ham[4]);
				a10.setText(a10.getText()+ "\n"+ "Values Displayed");
			}
		});
		kid.add(calc);

		statA = new TextField();
		statA.setLayoutX(130);
		statA.setLayoutY(630);
		statA.setMaxWidth(50);
		kid.add(statA);

		stat = new Button("Add Station");
		stat.setLayoutX(14);
		stat.setLayoutY(630);
		stat.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e){
				a10.setText(a10.getText()+ "\n"+ "Add Station clicked, Checking: " + statA.getText());
				String s = statA.getText();
				s = s.toUpperCase();
				if(s.length() == 4){
					menu.add(s);
				drop.getItems().add(s);
				a10.setText(a10.getText()+ "\n"+ "" + s + " added.");
				}else{
					a10.setText(a10.getText()+ "\n"+ s +" is an invalid station name, station was not added.");
				}

			}
		});
		kid.add(stat);





		Pane root = new Pane();
		for(Control c: kid){
			root.getChildren().add(c);
		}
		Scene scene = new Scene(root, 600, 900);
		stage.setScene(scene);
		stage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}
}

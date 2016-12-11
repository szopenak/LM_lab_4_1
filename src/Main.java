import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class Main extends Application {
	
	// Buttons
	Button btn_start = new Button("START");
	Button btn_next = new Button("Next array");
    Button btn_file = new Button("Choose a file");

	/// Program data
	final FileChooser fileChooser = new FileChooser();
	DataReader data;
	Stage primaryStage;
	Scene mainScene;
	boolean first_start = true;
	
	// Text
	final Text curr_array_text = new Text("Currently analyzed array:");
	private TextField curr_array = new TextField();
	private Text welcome_text = new Text("Hello, please choose a file");
	
	// history elements
    Text history_text = new Text("History:");
    TextArea history = new TextArea();	        
    Text verdict_text = new Text();
    // general stackpane
    StackPane root = new StackPane();
    	
    // button row
    HBox hb = new HBox();

    // general vbox
    VBox vb = new VBox();
	
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.mainScene = initialize();
		initHandlers();
		primaryStage.setTitle("TM - LAB 4_1");
        primaryStage.setScene(mainScene);
        primaryStage.show();
		
		
	} 
	public static void main (String args[]) {
		launch(args);
	}
	
	// initialize handlers
	private void initHandlers() {
		 btn_file.setOnAction(new EventHandler<ActionEvent>() {
	       	 
	            @Override
	            public void handle(ActionEvent event) {
	            	File file = fileChooser.showOpenDialog(primaryStage);
	                if (file != null) {
	                	data = new DataReader(file.getPath());
	                	btn_start.setDisable(false);
	                	welcome_text.setText("Click the start button!");
	                	history.setText("Detected "+data.getLen()+ " arrays separeted by #!\n");
	                }
	                else {
	                	welcome_text.setText("Choose another one!");
	                }
	                
	            }
	        });
		 
		 btn_start.setOnAction(new EventHandler<ActionEvent>() {
        	 
	            @Override
	            public void handle(ActionEvent event) {
	            	// first start
	            		hb.getChildren().clear();
	            		hb.getChildren().add(btn_next);
	            		vb.getChildren().clear();
	            		vb.getChildren().addAll(curr_array_text, curr_array, verdict_text, hb,history_text,history);
	            		history.setEditable(false);
	            		curr_array.setEditable(false);
	            		curr_array.setFont(Font.font ("Verdana", FontWeight.BOLD, 12));
	            		verdict_text.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
	            		btn_next.fire();

	            }
	        });
		 
		 btn_next.setOnAction(new EventHandler<ActionEvent>() {
	       	 
	            @Override
	            public void handle(ActionEvent event) {
	            
	            	String array = data.getElement();
	            	if (!first_start == true){
	            	history.appendText(curr_array.getText()+" - verdict: "+verdict_text.getText()+"\n");
	            	} else { first_start = false;}
	                if (array == null) {
	                	System.out.println("END");
	                	vb.getChildren().clear();
	                	verdict_text.setText("No data left!");
	                	history.setPrefRowCount(10);
	            		vb.getChildren().addAll(verdict_text,history_text,history);
	                } else {
	                	curr_array.setText(array);
	                	verdict_text.setText(RegExpAnalyzer.check(array));
	                }
	            }
	        });
	        
		 
	}
	
	public Scene initialize () {
		hb.getChildren().addAll(btn_file, btn_start);
		hb.setAlignment(Pos.CENTER);
		hb.setSpacing(10);
		btn_start.setDisable(true);
		welcome_text.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
    	vb.getChildren().addAll(welcome_text, hb);
    	root.getChildren().add(vb);
    	vb.setSpacing(30);
    	vb.setAlignment(Pos.CENTER);
    	vb.setPadding(new Insets(20));
    	Scene scene = new Scene(root, 400,500);
    	primaryStage.setMinWidth(400);
    	primaryStage.setMinHeight(500);
    	return scene;
    }
	
}
	

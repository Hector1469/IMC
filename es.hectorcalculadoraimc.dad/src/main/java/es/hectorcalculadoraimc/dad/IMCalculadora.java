package es.hectorcalculadoraimc.dad;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class IMCalculadora extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {

		// para peso hbox
		Label pesoLabel = new Label("Peso:");
		TextField pesoTextField = new TextField();
		Label kgLabel = new Label("kg");

		// para alturaHBox
		Label alturaLabel = new Label("Altura:");
		TextField alturaTextField = new TextField();
		Label cmLabel = new Label("cm");

		// para imcHBox

		Label imcLabel = new Label("IMC:");
		Label imcResultado = new Label();

		//
		String Obeso = "Bajo peso | Normal | Sobrepeso | Obeso";
		Label tupeso = new Label(Obeso);

		// guardo todo dentro del vbox

		HBox pesoHBox = new HBox(5, pesoLabel, pesoTextField, kgLabel);
		HBox alturaHBox = new HBox(5, alturaLabel, alturaTextField, cmLabel);
		HBox imcHBox = new HBox(5, imcLabel, imcResultado);

		VBox root = new VBox(5, pesoHBox, alturaHBox, imcHBox, tupeso);
		
		pesoHBox.setAlignment(Pos.CENTER);
		alturaHBox.setAlignment(Pos.CENTER);
		imcHBox.setAlignment(Pos.CENTER);
		root.setAlignment(Pos.CENTER);

		DoubleProperty peso = new SimpleDoubleProperty();
		DoubleProperty altura = new SimpleDoubleProperty();
		DoubleProperty imc = new SimpleDoubleProperty();

		// bindings
		pesoTextField.textProperty().bindBidirectional(peso, new NumberStringConverter());
		alturaTextField.textProperty().bindBidirectional(altura, new NumberStringConverter());
		imc.bind(peso.divide((altura.divide(100).multiply(altura.divide(100)))));
		imcResultado.textProperty().bind(imc.asString("%.2f"));

		Scene scene = new Scene(root, 320, 200);
		primaryStage.setScene(scene);

		primaryStage.setTitle("IMC.fxml");
		primaryStage.show();
		
		//Ahora la logica
		
		imc.addListener((observable, oldValue, newValue) -> {
		    
		    String pesoCategoria = "";

		    if (newValue.doubleValue() < 18.5) {
		        pesoCategoria = "Bajo peso";
		    } else if (newValue.doubleValue() >= 18.5 && newValue.doubleValue() < 25) {
		        pesoCategoria = "Normal";
		    } else if (newValue.doubleValue() >= 25 && newValue.doubleValue() < 30) {
		        pesoCategoria = "Sobrepeso";
		    } else {
		        pesoCategoria = "Obeso";
		    }

		    tupeso.setText(pesoCategoria);
		});


	}

}

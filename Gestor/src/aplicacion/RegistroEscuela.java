/**
 * Ventana de registro de escuela o area academica
 * @author WonMi Lim y Jimmy Tsang
 */

package aplicacion;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class RegistroEscuela {
  String nombre;
  String codigo;
  
  /**
   * Inicializa la ventana
   * @param primaryStage 
   */
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Registro de Escuela");   
    
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(15);
    grid.setPadding(new Insets(25));
    
    Scene scene = new Scene(grid, 400, 210);
    
    scene.getStylesheets().add(
        RegistroEscuela.class.getResource("/css/General.css").toExternalForm());
    primaryStage.setScene(scene);
    
    Text titulo = new Text("Registro de Escuela o Area Academica");
    titulo.setFill(Color.WHITE);
    titulo.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    grid.add(titulo, 0, 0, 2, 1);

    Label lblNombre = new Label("Nombre:");
    grid.add(lblNombre, 0, 1);

    TextField txtNombre = new TextField();
    grid.add(txtNombre, 1, 1);

    Label lblCodigo = new Label("Codigo:");
    grid.add(lblCodigo, 0, 2);

    TextField txtCodigo = new TextField();
    grid.add(txtCodigo, 1, 2);
    
    Button btnRegistrar = new Button("Registrar");
    Button btnLimpiar = new Button("Limpiar campos");
    Button btnCerrar = new Button("Cerrar");
    HBox hbBtn = new HBox(10);
    hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
    hbBtn.getChildren().addAll(btnRegistrar, btnLimpiar, btnCerrar);
    grid.add(hbBtn, 1, 4);
    
    final Text lblError = new Text();
    lblError.setFill(Color.FIREBRICK);
    lblError.setFont(Font.font("Arial", FontWeight.BOLD, 12));
    grid.add(lblError, 1, 5);
    
    btnLimpiar.setOnAction((ActionEvent e) -> { 
      txtNombre.clear();
      txtCodigo.clear();
      nombre = null;
      codigo = null;
    });
    
    btnRegistrar.setOnAction((ActionEvent e) -> {        
      lblError.setText("Error en los datos ingresados");
    });
    
    btnCerrar.setOnAction((ActionEvent e) -> {        
      primaryStage.close();
    });
    
    primaryStage.show();
    
  }
    
}

/**
 * Ventana de registro de curso
 * @author WonMi Lim
 */

package aplicacion;

import dao.CursoDao;
import dao.EscuelaDao;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.Curso;
import modelo.Escuela;

public class RegistroCurso {
  String nombre;
  String codigo;
  String escuela;
  String creditos;
  String horas;
  ArrayList<Escuela> escuelas;
    
  /**
   * Inicializa la ventana RegistroCurso
   * @param primaryStage 
   */
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Registro de Curso");   
    
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(15);
    grid.setPadding(new Insets(25));
    
    Scene scene = new Scene(grid, 400, 300);
    
    //Carga los recurso de un archivo css
    scene.getStylesheets().add(
        RegistroEscuela.class.getResource("/css/General.css").toExternalForm());
    primaryStage.setScene(scene);
    
    Text titulo = new Text("Registro de Cursos");
    titulo.setFill(Color.WHITE);
    titulo.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    grid.add(titulo, 0, 0, 2, 1);

    //Seleccionar una escuela o area academica
    Label lblEscuela = new Label("Escuela propietaria del plan:");
    grid.add(lblEscuela, 0, 1);
    //Lista desplegable de escuelas o areas academicas
    ComboBox bxEscuela = new ComboBox();
    grid.add(bxEscuela, 1, 1);    
    //Se activa al seleccionar una escuela de la lista desplagable
    bxEscuela.setOnAction((Event ev) -> {
      escuela = bxEscuela.getSelectionModel().getSelectedItem().toString();    
    });

    //Campo de texto para el nombre
    Label lblNombre = new Label("Nombre:");
    grid.add(lblNombre, 0, 2);
    TextField txtNombre = new TextField();
    grid.add(txtNombre, 1, 2);

    //Campo de texto para el codigo
    Label lblCodigo = new Label("Codigo:");
    grid.add(lblCodigo, 0, 3);
    Label lblCodigoEscuela = new Label("");
    TextField txtCodigo = new TextField();
    HBox hbCodigo = new HBox(10);
    hbCodigo.setAlignment(Pos.CENTER_LEFT);
    hbCodigo.getChildren().addAll(lblCodigoEscuela, txtCodigo);
    grid.add(hbCodigo, 1, 3);
    
    //Lista desplegable de numeros del 0 al 4 para los creditos 
    Label lblCreditos = new Label("Creditos:");
    grid.add(lblCreditos, 0, 4);
    ComboBox bxCreditos = new ComboBox();
    grid.add(bxCreditos, 1, 4);
    bxCreditos.getItems().addAll("0", "1", "2", "3", "4");
    
    //Lista desplegable de numeros del 1 al 5 para las horas 
    Label lblHoras = new Label("Horas lectivas:");
    grid.add(lblHoras, 0, 5);
    ComboBox bxHoras = new ComboBox();
    grid.add(bxHoras, 1, 5);
    bxHoras.getItems().addAll("1", "2", "3", "4", "5");
    
    Button btnRegistrar = new Button("Registrar");
    Button btnLimpiar = new Button("Limpiar campos");
    Button btnCerrar = new Button("Cerrar");
    HBox hbBtn = new HBox(10);
    hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
    hbBtn.getChildren().addAll(btnRegistrar, btnLimpiar, btnCerrar);
    grid.add(hbBtn, 0, 6, 2, 1);
    
    final Text lblError = new Text();
    lblError.setFill(Color.FIREBRICK);
    lblError.setFont(Font.font("Arial", FontWeight.BOLD, 12));
    grid.add(lblError, 1, 7);
    
    //Cargar datos
    EscuelaDao escuelaDao = new EscuelaDao();
    escuelas = escuelaDao.getEscuelas();
    escuelas.forEach((e) -> {
      bxEscuela.getItems().add(e.getNombre());
    });
    
    /**
     * Se activa al seleccionar una escuela de la lista desplagable
     * imprime los dos primetos caracteres del codigo para el curso segun la 
     * escuela en el label
     */
    bxEscuela.setOnAction((Event ev) -> {
      escuela = bxEscuela.getSelectionModel().getSelectedItem().toString();
      for(Escuela e : escuelas){
        if(e.getNombre() == escuela){
          escuela = e.getCodigo();
        }
      }
      lblCodigoEscuela.setText(escuela);
    });
    
    //Registra el curso
    btnRegistrar.setOnAction((ActionEvent e) -> { 
      codigo = escuela + txtCodigo.getText();
      nombre = txtNombre.getText();
      creditos = bxCreditos.getSelectionModel().getSelectedItem().toString();
      horas = bxHoras.getSelectionModel().getSelectedItem().toString();
      lblError.setText("");
      if(codigo.length() == 6 && nombre != "" && creditos != "" && horas != ""){
        System.out.println("ls horas " + horas);
        Curso curso = new Curso(codigo, nombre, Integer.parseInt(creditos), Integer.parseInt(horas),escuela);
        CursoDao cursoDao = new CursoDao();
        System.out.println(curso.toString());
        cursoDao.insertarNuevoCurso(curso, escuela);
        btnLimpiar.fire();   
      }
      else{
        lblError.setText("Error en los datos ingresados");
      }
    });
    
    //Limpia los campos de texto
    btnLimpiar.setOnAction((ActionEvent e) -> { 
      txtNombre.clear();
      txtCodigo.clear();
      lblError.setText("");
      nombre = null;
      codigo = null;
      escuela = null;
      horas = null;
      creditos = null;
    });
    
    btnCerrar.setOnAction((ActionEvent e) -> { 
      primaryStage.close();
    });
         
    primaryStage.show();
  }
    
}

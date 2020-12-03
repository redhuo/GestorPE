/**
 * Ventana de registro de plan de estudio
 * @author WonMi Lim y Jimmy Tsang
 */

package aplicacion;

import dao.CursoDao;
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
import modelo.Escuela;
import dao.EscuelaDao;
import dao.PlanDeEstudioDao;
import modelo.Curso;
import modelo.PlanDeEstudio;

public class RegistroPlanEstudio {
  String escuela;
  String codigo;
  String fecha;
  String curso;
  String bloque;
  ArrayList<Escuela> escuelas;
  ArrayList<Curso> cursos;
  /**
   * Inicializa la ventana
   * @param primaryStage 
   */
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Registro de Plan de Estudio");  
    
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(15);
    grid.setPadding(new Insets(25));
    
    Scene scene = new Scene(grid, 400, 400);
    
    //Carga los recurso de un archivo css
    scene.getStylesheets().add(
    RegistroEscuela.class.getResource("/css/General.css").toExternalForm());
    primaryStage.setScene(scene);
    
    Text titulo = new Text("Registro de Plan de Estudio");
    titulo.setFill(Color.WHITE);
    titulo.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    grid.add(titulo, 0, 0, 2, 1);

    //Seleccionar una escuela o area academica
    Label lblEscuela = new Label("Escuela propietaria del plan:");
    grid.add(lblEscuela, 0, 1);
    //Lista desplegable de escuelas o areas academicas
    ComboBox bxEscuela = new ComboBox();
    grid.add(bxEscuela, 1, 1); 

    //Campo de texto para codigo del plan de estudio 
    Label lblCodigo = new Label("Codigo:");
    grid.add(lblCodigo, 0, 2);
    TextField txtCodigo = new TextField();
    grid.add(txtCodigo, 1, 2);
    
    //Campo de texto para la fecha de vigencia
    Label lblFecha = new Label("Vigencia:");
    grid.add(lblFecha, 0, 3);    
    TextField txtFecha = new TextField();
    txtFecha.setPromptText("dd/mm/yyyy");
    grid.add(txtFecha, 1, 3);
    
    Button btnRegistrar = new Button("Registrar");
    HBox hbBtnRegistrar = new HBox(10);
    hbBtnRegistrar.setAlignment(Pos.CENTER);
    hbBtnRegistrar.getChildren().add(btnRegistrar);
    grid.add(hbBtnRegistrar, 0, 4, 2, 1);
    
    //Campo de texto para los cursos pertenecientes a este plan de estudio
    Label lblCurso = new Label("Codigo del curso:");
    grid.add(lblCurso, 0, 5);    
    //Lista desplegable de escuelas o areas academicas
    ComboBox bxCurso = new ComboBox();
    grid.add(bxCurso, 1, 5);  
    
    //Lista desplegable para seleccionar el bloque al que pertenece el curso en el plan de estudio
    Label lblBloque = new Label("Bloque:");
    grid.add(lblBloque, 0, 6);    
    ComboBox bxBloque = new ComboBox();
    grid.add(bxBloque, 1, 6);
    bxBloque.getItems().addAll(
        "I Semestre", 
        "II Semestre",
        "III Semestre",
        "IV Semestre",
        "V Semestre",
        "VI Semestre",
        "VII Semestre",
        "VIII Semestre",
        "IX Semestre",
        "X Semestre");
    
    Button btnRegistrarCurso = new Button("Registrar curso al plan de estudio");
    HBox hbBtnRegistrarCurso = new HBox(10);
    hbBtnRegistrarCurso.setAlignment(Pos.CENTER);
    hbBtnRegistrarCurso.getChildren().add(btnRegistrarCurso);
    grid.add(hbBtnRegistrarCurso, 0, 7, 2, 1);
    
    final Text lblError = new Text();
    lblError.setFill(Color.FIREBRICK);
    lblError.setFont(Font.font("Arial", FontWeight.BOLD, 12));
    grid.add(lblError, 1, 8); 
    
    Button btnCerrar = new Button("Cerrar");
    Button btnLimpiar = new Button("Limpiar campos");
    HBox hbBtn = new HBox(10);
    hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
    hbBtn.getChildren().addAll(btnLimpiar, btnCerrar);
    grid.add(hbBtn, 1, 9);
    
    //Cargar datos
    EscuelaDao escuelaDao = new EscuelaDao();
    escuelas = escuelaDao.getEscuelas(); 
    escuelas.forEach((e) -> {
      bxEscuela.getItems().add(e.getNombre());
    }); 
    CursoDao cursoDao = new CursoDao ();
    cursos = cursoDao.getCursos();
    cursos.forEach((c) -> {
      bxCurso.getItems().add(c.getCodigo());
    });
    
    //Limpia los campos de texto
    btnLimpiar.setOnAction((ActionEvent e) -> { 
      txtCodigo.clear();
      txtFecha.clear();
    });
    
    //Registra el plan de estudio
    btnRegistrar.setOnAction((ActionEvent e) -> {        
      //lblError.setText("Error en los datos ingresados");
      escuela = bxEscuela.getSelectionModel().getSelectedItem().toString();  
      for(Escuela esc : escuelas){
        if(esc.getNombre() == escuela){
          escuela = esc.getCodigo();
        }
      }
      this.codigo = txtCodigo.getText();
      this.fecha = txtFecha.getText();
      if(this.codigo.length() == 4 && this.fecha != "" && this.escuela != ""){
        PlanDeEstudio nuevo = new PlanDeEstudio(Integer.parseInt(this.codigo),this.fecha,escuela);
        new PlanDeEstudioDao().insertarNuevoPlan(nuevo);
      }
      else{
        lblError.setText("Error en los datos ingresados");
      }
    }); 
    
    //Registra un curso al plan de estudio
    btnRegistrarCurso.setOnAction((ActionEvent e) -> {   
      bloque = bxBloque.getSelectionModel().getSelectedItem().toString();
      curso = bxCurso.getSelectionModel().getSelectedItem().toString();   
      this.codigo = txtCodigo.getText();
      int aux = 0;
      if(this.curso != "" && this.codigo != "" && this.bloque != ""){
        if(bloque.equals("I Semestre")){
          aux = 1;
        }
        if(bloque.equals("II Semestre")){
          aux = 2;
        }
        if(bloque.equals("III Semestre")){
          aux = 3;
        }
        if(bloque.equals("IV Semestre")){
          aux = 4;
        }
        if(bloque.equals("V Semestre")){
          aux = 5;
        }
        if(bloque.equals("VI Semestre")){
          aux = 6;
        }
        if(bloque.equals("VII Semestre")){
          aux = 7;
        }
        if(bloque.equals("VIII Semestre")){
          aux = 8;
        }
        if(bloque.equals("IX Semestre")){
          aux = 9;
        }
        if(bloque.equals("X Semestre")){
          aux = 10;
        }
        new PlanDeEstudioDao().insertarCurso(Integer.parseInt(codigo),curso,aux);
      }
      else{
        lblError.setText("Error en los datos ingresados");
      }
      
    }); 
    
    btnCerrar.setOnAction((ActionEvent e) -> {        
      primaryStage.close();
    }); 
    
    primaryStage.show();
  }

}

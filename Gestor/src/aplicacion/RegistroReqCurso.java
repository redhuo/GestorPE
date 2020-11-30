/**
 * Ventana de registro requisitos y correquisitos de un curso
 * @author WonMi Lim y Jimmy Tsang
 */

package aplicacion;

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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import modelo.Escuela;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import dao.CursoDao;
import modelo.Curso;

public class RegistroReqCurso {
  String escuela;
  String curso;
  String requisito;
  String correquisito;
  ArrayList<Escuela> escuelas;
  ArrayList<Curso> cursos;
  ArrayList<Curso> cursosReq;
  ArrayList<Curso> cursosCor;
  EscuelaDao escuelaDao;
  CursoDao cursoDao;
  
  /**
   * Inicializa la ventana
   * @param primaryStage 
   */
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Registro de Requisitos");        
    primaryStage.show();
    
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(15);
    grid.setPadding(new Insets(25));
    
    Scene scene = new Scene(grid, 430, 350);
    
    //Carga los recurso de un archivo css
    scene.getStylesheets().add(
    RegistroEscuela.class.getResource("/css/General.css").toExternalForm());
    primaryStage.setScene(scene);
    
    Text titulo = new Text("Registro de requisitos y correquisitos de curso");
    titulo.setFill(Color.WHITE);
    titulo.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    grid.add(titulo, 0, 0, 2, 1);

    //Seleccionar una escuela o area academica
    Label lblEscuela = new Label("Escuela propietaria del plan:");
    grid.add(lblEscuela, 0, 1);
    //Lista desplegable de escuelas o areas academicas
    ComboBox bxEscuela = new ComboBox();
    grid.add(bxEscuela, 1, 1);   
    
    //Lista desplegable de codigo de cursos
    Label lblCurso = new Label("Codigo del curso:");
    grid.add(lblCurso, 0, 2);
    ComboBox bxCurso = new ComboBox();
    grid.add(bxCurso, 1, 2);
    
    //Lista desplegable de cursos para registrar los requisitos
    Text tituloRequisito = new Text("Requisitos del curso");
    Label lblCurso1 = new Label("Codigo del curso:");
    tituloRequisito.setFill(Color.WHITE);
    tituloRequisito.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    ComboBox bxRequisito = new ComboBox();
    Button btnRegistrarReq = new Button("Registrar");
    VBox vbRequisito = new VBox(10);
    vbRequisito.setAlignment(Pos.BASELINE_CENTER);
    vbRequisito.getChildren().addAll(tituloRequisito,lblCurso1,bxRequisito,btnRegistrarReq);
    grid.add(vbRequisito, 0, 3);
    
    //Lista desplegable de cursos para registrar los correquisitos
    Text tituloCorrequisito = new Text("Correquisitos del curso");
    Label lblCurso2 = new Label("Codigo del curso:");
    tituloCorrequisito.setFill(Color.WHITE);
    tituloCorrequisito.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    ComboBox bxCorrequisito = new ComboBox();
    Button btnRegistrarCo = new Button("Registrar");
    VBox vbCorrequisito = new VBox(10);
    vbCorrequisito.setAlignment(Pos.BASELINE_CENTER);
    vbCorrequisito.getChildren().addAll(tituloCorrequisito,lblCurso2,bxCorrequisito,btnRegistrarCo);
    grid.add(vbCorrequisito, 1, 3);
        
    final Text lblError = new Text();
    lblError.setFill(Color.FIREBRICK);
    lblError.setFont(Font.font("Arial", FontWeight.BOLD, 12));
    Button btnCerrar = new Button("Cerrar");
    VBox vbBtn = new VBox(10);
    vbBtn.setAlignment(Pos.BASELINE_CENTER);
    vbBtn.getChildren().addAll(lblError, btnCerrar);
    grid.add(vbBtn, 0, 4, 2, 1);    
    
    //Cargar datos
    cursoDao = new CursoDao(); 
    escuelaDao = new EscuelaDao();
    escuelas = escuelaDao.getEscuelas();
    escuelas.forEach((e) -> {
      bxEscuela.getItems().add(e.getNombre());
    });    
    
    //Se activa al seleccionar una escuela de la lista desplagable
    bxEscuela.setOnAction((Event ev) -> {
      escuela = bxEscuela.getSelectionModel().getSelectedItem().toString();    
      for(Escuela e : escuelas){
        if(e.getNombre() == escuela){
          escuela = e.getCodigo();
        }
      }
      cursos = cursoDao.getCursosPorEscuela(escuela);
      cursos.forEach((c) -> {
        bxCurso.getItems().add(c.getCodigo());
      });
    });
    
    //Se activa al seleccionar un curso de la lista desplagable 
    bxCurso.setOnAction((Event ev) -> {
      curso = bxCurso.getSelectionModel().getSelectedItem().toString();    
      cursosReq = cursoDao.getCursos();
      cursosReq.forEach((c) -> {
        bxRequisito.getItems().add(c.getCodigo());
      });
      cursosReq.forEach((c) -> {
        bxCorrequisito.getItems().add(c.getCodigo());
      });
    });
    
    //Registra los cursos que son requisito para el curso
    btnRegistrarReq.setOnAction((ActionEvent e) -> {        
      requisito = bxRequisito.getSelectionModel().getSelectedItem().toString();
      cursoDao.insertarRequisito(curso, requisito);
      
    });
    
    //Registra los cursos que son correquisito para el curso
    btnRegistrarCo.setOnAction((ActionEvent e) -> {        
      correquisito = bxCorrequisito.getSelectionModel().getSelectedItem().toString();
      cursoDao.insertarCorrequisito(curso, correquisito);
    });
    
    btnCerrar.setOnAction((ActionEvent e) -> {        
      primaryStage.close();
    });    
    
    primaryStage.show();
  }
    
}

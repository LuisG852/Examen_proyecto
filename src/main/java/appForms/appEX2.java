package appForms;

import org.example.Cursos1;
import org.example.DTOS.CursosClass;
import org.example.DTOS.EstudiantesClass;
import org.example.DTOS.InscripcionesClass;
import org.example.Estudiantes1;
import org.example.Inscripciones1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class appEX2 extends JFrame{

    private Inscripciones1 inscripciones1;
    private Estudiantes1 estudiantes1;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Cursos1 cursos1;
    private JTextField txt1;
    private JButton btnagestudiante;
    private JTextField txt2;
    private JTextField txt3;
    private JTextField txt4;
    private JTextField txt5;
    private JTextField txt6;
    private JTextField txt7;
    private JButton btnagcurso;
    private JTextField txt8;
    private JTextField txt9;
    private JTextField txt10;
    private JTextField txt11;
    private JButton btnaginsc;
    private JButton btnupinsc;
    private JButton btndlinsc;
    private JPanel Panel;
    private JButton btndles;
    private JButton btndlcurso;

    public appEX2() {
        setTitle("Aplicación de Inscripciones");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(Panel);
        setLocationRelativeTo(null);
        setVisible(true);

        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DBfinal", "postgres", "1234");
            inscripciones1 = new Inscripciones1(connection);
            estudiantes1 = new Estudiantes1(connection);
            cursos1 = new Cursos1(connection);
        }catch (SQLException e){
            e.printStackTrace();
        }
        btnagestudiante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEstudiante();
            }
        });
        btndles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteEstudiante();
            }
        });
        btnagcurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCurso();
            }
        });
        btndlcurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCurso();
            }
        });
        btnaginsc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addInscripcion();

            }
        });
        btnupinsc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateInscripcion();
            }
        });
        btndlinsc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteInscripcion();
            }
        });
    }
    private void addEstudiante(){
        try{
            int idEstudiante = Integer.parseInt(txt1.getText());
            String nombre = txt2.getText();
            String apellido = txt3.getText();
            String email = txt4.getText();
            EstudiantesClass estudiante = new EstudiantesClass();
            estudiante.setIdEstudiante(idEstudiante);
            estudiante.setNombre(nombre);
            estudiante.setApellido(apellido);
            estudiante.setEmail(email);
            estudiantes1.insert(estudiante);
            System.out.println("Estudiante agregado");
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
    }
    private void addCurso(){
        try{
            int idCurso = Integer.parseInt(txt5.getText());
            String nombreCurso = txt6.getText();
            String profesor = txt7.getText();

            CursosClass curso = new CursosClass();
            curso.setIdCurso(idCurso);
            curso.setNombreCurso(nombreCurso);
            curso.setProfesor(profesor);

            cursos1.insert(curso);
            System.out.println("Curso agregado");

        }catch (NumberFormatException e){
            e.printStackTrace();
        }
    }
    private void addInscripcion(){
        try{
            int idEstudiante = Integer.parseInt(txt9.getText());
            int idCurso = Integer.parseInt(txt10.getText());
            java.util.Date fechaInsc =  dateFormat.parse(txt11.getText());

            InscripcionesClass inscripcion = new InscripcionesClass();
            inscripcion.setIdEstudiante(idEstudiante);
            inscripcion.setIdCurso(idCurso);
            inscripcion.setFechaInscripcion(new java.sql.Date(fechaInsc.getTime()));

            inscripciones1.insert(inscripcion);
            System.out.println("Inscripcion agregada");
        }catch (ParseException | NumberFormatException e){
            e.printStackTrace();
        }
    }
    private void updateInscripcion(){
        try{
            int idInscrip = Integer.parseInt(txt8.getText());
            int idEstudiante = Integer.parseInt(txt9.getText());
            int idCurso = Integer.parseInt(txt10.getText());
            java.util.Date fechainsc = dateFormat.parse(txt11.getText());

            InscripcionesClass inscripcion = new InscripcionesClass();
            inscripcion.setIdInscripcion(idInscrip);
            inscripcion.setIdEstudiante(idEstudiante);
            inscripcion.setIdCurso(idCurso);
            inscripcion.setFechaInscripcion(new java.sql.Date(fechainsc.getTime()));

            inscripciones1.update(inscripcion);
            System.out.println("Actualizado");
        }catch (ParseException  | NumberFormatException e){
            e.printStackTrace();
        }
    }
    private void deleteInscripcion(){
        int idInsc = Integer.parseInt(txt8.getText());
        inscripciones1.delete(idInsc);
        System.out.println("Inscripcion eliminada");
    }
    private void deleteCurso(){
        int idcurso = Integer.parseInt(txt5.getText());
        cursos1.delete(idcurso);
        System.out.println("Curso eliminado");
    }
    private void deleteEstudiante(){
        int idest = Integer.parseInt(txt1.getText());
        estudiantes1.delete(idest);
        System.out.println("Estudiante eliminado");
    }
}

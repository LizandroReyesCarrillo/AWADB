package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "alumno")

public class AlumnoModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = false, nullable = false)
    private int id;

    //@Column(nullable = false)
    private String nombres;

    //@Column(nullable = false)
    private String apellidos;

    //@Column(nullable = false)
    private String matricula;

    //@Column(nullable = false)
    private Double promedio;

    //@Column(nullable = false)
    private String fotoPerfilUrl;

    @Transient
    private String imagenUrl;

    public void setImagenUrl(String imagenUrl){
        this.imagenUrl = imagenUrl;
    }

    public String getImagenUrl(){
        return imagenUrl;
    }
    
    public void setFotoPerfilUrl(String fotoPerfilUrl){
        this.fotoPerfilUrl = fotoPerfilUrl;
    }

    public String getFotoPerfilUrl(){
        return fotoPerfilUrl;
    }

    public void setMatricula(String matricula){
        this.matricula = matricula;
    }

    public String getMatricula(){
        return matricula;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Double getPromedio() {
        return promedio;
    }

    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }

}

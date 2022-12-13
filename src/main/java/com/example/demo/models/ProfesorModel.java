package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "profesor")

public class ProfesorModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = false, nullable = false)
    private Integer id;

    //@Column(nullable = false)
    private Integer numeroEmpleado;

    //@Column(nullable = false)
    private String nombres;

    //@Column(nullable = false)
    private String apellidos;

    //@Column(nullable = false)
    private Integer horasClase;

    public void setNumeroEmpleado(Integer numeroEmpleado){
        this.numeroEmpleado = numeroEmpleado;
    }

    public Integer getNumeroEmpleado(){
        return numeroEmpleado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getHorasClase() {
        return horasClase;
    }

    public void setHorasClase(Integer horasClase) {
        this.horasClase = horasClase;
    }

}

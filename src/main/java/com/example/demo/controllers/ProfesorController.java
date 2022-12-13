package com.example.demo.controllers;

import java.util.ArrayList;

import com.example.demo.models.ProfesorModel;
import com.example.demo.services.ProfesorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {

    @Autowired
    ProfesorService profesorService;

    @GetMapping()
    public ArrayList<ProfesorModel> obtenerProfesores() {
        return profesorService.obtenerProfesores();
    }

    @PostMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity<Object> guardarProfesor(@RequestBody ProfesorModel profesor) {
        if (profesor.getNombres().equals("") || profesor.getApellidos().equals(null)
                || profesor.getNumeroEmpleado() < 0 || profesor.getHorasClase() < 0) {
            return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(this.profesorService.guardarProfesor(profesor), HttpStatus.CREATED);
        }
    }

    @GetMapping(path = "/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Object> obtenerProfesorPorId(@PathVariable("id") int id) {

        boolean respuesta = this.profesorService.obtenerPorId(id).isEmpty();
        if (!respuesta) {
            return new ResponseEntity<>(this.profesorService.obtenerPorId(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(this.profesorService.obtenerPorId(id), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> eliminarPorId(@PathVariable("id") int id) {
        boolean ok = this.profesorService.eliminarProfesor(id);
        if (ok) {
            return new ResponseEntity<>("{}" + id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("{}" + id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Object> modificarProfesor(@RequestBody ProfesorModel profesor, @PathVariable int id) {
        profesor.setId(id);
        if (profesor.getNombres() == null || profesor.getHorasClase() < 0) {
            return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(this.profesorService.guardarProfesor(profesor), HttpStatus.OK);
        }
    }

}

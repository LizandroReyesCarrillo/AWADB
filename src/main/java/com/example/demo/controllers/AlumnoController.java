package com.example.demo.controllers;

import java.io.IOException;
import java.util.ArrayList;

import com.example.demo.models.AlumnoModel;
import com.example.demo.repositories.AlumnoRepository;
import com.example.demo.services.AlumnoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    @Autowired
    AlumnoService alumnoService;

    @Autowired
    AlumnoRepository alumnoRepository;

    @GetMapping()
    public ArrayList<AlumnoModel> obtenerAlumnos() {
        return alumnoService.obtenerAlumnos();
    }

    @PostMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity<Object> guardarAlumnos(@RequestBody AlumnoModel alumno) {
        if (alumno.getNombres().equals("") || alumno.getApellidos().equals(null)
                || alumno.getMatricula().matches("[+-]?\\d*(\\.\\d+)?") || alumno.getPromedio() < 0) {
            return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(this.alumnoService.guardarAlumno(alumno), HttpStatus.CREATED);
        }
    }

    @PostMapping(path = "/{id}/fotoPerfil", consumes = "multipart/form-data")
    public ResponseEntity<Object> guardarAlumnosConImagen(@PathVariable("id") int id,
            @RequestParam("foto") MultipartFile file) throws IOException {
        return new ResponseEntity<>(this.alumnoService.subirFotoPerfil(id, file), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Object> obtenerAlumnosPorId(@PathVariable("id") int id) {
        boolean respuesta = this.alumnoService.obtenerPorId(id).isEmpty();
        if (!respuesta) {
            return new ResponseEntity<>(this.alumnoService.obtenerPorId(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(this.alumnoService.obtenerPorId(id), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> eliminarPorId(@PathVariable("id") int id) {
        boolean ok = this.alumnoService.eliminarAlumno(id);
        if (ok) {
            return new ResponseEntity<>("{}", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("{}", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Object> modificarAlumno(@RequestBody AlumnoModel alumno, @PathVariable int id) {
        alumno.setId(id);
        if (alumno.getNombres() == null || alumno.getMatricula().matches("[+-]?\\d*(\\.\\d+)?")) {
            return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(this.alumnoService.modificarAlumno(alumno), HttpStatus.OK);
        }
    }

}
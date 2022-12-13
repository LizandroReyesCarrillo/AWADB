package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.models.ProfesorModel;
import com.example.demo.repositories.ProfesorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesorService {
    @Autowired
    ProfesorRepository profesorRepository;
    
    public ArrayList<ProfesorModel> obtenerProfesores(){
        return (ArrayList<ProfesorModel>) profesorRepository.findAll();
    }

    public ProfesorModel guardarProfesor(ProfesorModel profesor){
        return profesorRepository.save(profesor);
    }

    public Optional<ProfesorModel> obtenerPorId(Integer id){
        return profesorRepository.findById(id);
    }

    public boolean eliminarProfesor(int id) {
        try{
            profesorRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }
}

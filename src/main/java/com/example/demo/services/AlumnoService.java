package com.example.demo.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Optional;

import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.demo.models.AlumnoModel;
import com.example.demo.repositories.AlumnoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AlumnoService {
    @Autowired
    AlumnoRepository alumnoRepository;

    String key = "ASIAZJQWOCS3RB5FAAVG";
    String secret = "mrEGJ9hPjEd0ERo7cxey37qxfQ/3zSTqB72MHQyA";
    String sessionToken = "FwoGZXIvYXdzENj//////////wEaDHTNvFIXgFjsAuu3ICLNAZ6S7n98wA2nl0Q8awY2GIfzPdX1BFEkQHbBt6xvRxo8HDGacN/BZYAW29cl2Ydo737meu0pX+3PTz8BV8wbhbNnqY6BGifahVNviC+czfKoTiC3KrOH8RNbUaABDrCf0ySN8UMWQ6BXFx3IEZzZ7ISjqzmiS4c5Q5uVnw0nOG0eK7JYKaa3asJ/OZtaAOlAre9Gl4uXGtYqeBMD8nZWijn/LhpkoEaPUIx2YtBfB/AVVsaX+f4jF2NPnVxZldCukdfAFe7hwkTBGESyHKYo24zAlAYyLYzhWIRGNakokdO0VVhnh1+FRojyNcgIEvJOPaNiC/6UI3uMQbNDqbWThboNzQ==";
    String bucket = "demospringboots3";
    // private String US_EAST_1;

    public AlumnoModel subirFotoPerfil(int id, MultipartFile foto) throws IOException {
        ArrayList<AlumnoModel> alumnoarray = new ArrayList();
        alumnoarray = alumnoRepository.findById(id);
        AlumnoModel alumno = alumnoarray.get(0);

        BasicSessionCredentials awsCreds = new BasicSessionCredentials(key, secret, sessionToken);
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withRegion(Regions.US_EAST_1).build();
        String key = "fotos/" + alumno.getMatricula() + "_" + alumno.getNombres() + "_fotoPerfil_"
                + foto.getOriginalFilename();

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, key, foto.getInputStream(),
                new ObjectMetadata()).withCannedAcl(CannedAccessControlList.PublicRead);
        s3.putObject(putObjectRequest);

        alumno.setFotoPerfilUrl("https://" + bucket + ".s3.amazonaws.com/" + key);

        return alumnoRepository.save(alumno);
    }

    public ArrayList<AlumnoModel> obtenerAlumnos() {
        return (ArrayList<AlumnoModel>) alumnoRepository.findAll();
    }

    public AlumnoModel guardarAlumno(AlumnoModel alumno) {
        return alumnoRepository.save(alumno);
    }

    public AlumnoModel modificarAlumno(AlumnoModel alumno) {
        return alumnoRepository.save(alumno);
    }

    public Optional<AlumnoModel> obtenerPorId(Integer id) {
        return alumnoRepository.findById(id);
    }

    public boolean eliminarAlumno(Integer id) {
        try {
            alumnoRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }
}

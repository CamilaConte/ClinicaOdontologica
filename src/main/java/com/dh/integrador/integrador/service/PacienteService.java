package com.dh.integrador.integrador.service;

import com.dh.integrador.integrador.entities.Paciente;
import com.dh.integrador.integrador.exceptions.ResourceNotFoundException;
import com.dh.integrador.integrador.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //para indicar que es un servicio (para la parte de Spring)
public class PacienteService{
    @Autowired
    PacienteRepository repository;

    public List<Paciente> listarPacientes() {
        return repository.findAll();
    }

    public Optional<Paciente> buscar(Long id) {
        return repository.findById(id);
    }

    public Paciente guardar(Paciente paciente) {
        return repository.save(paciente);
    }

    public Paciente actualizar(Paciente paciente) {
        //verificar si existe el paciente
        if (buscar(paciente.getId()).isPresent()){
            return repository.save(paciente);
        }else {
            return null;
        }
    }

    public void eliminar(Long id) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado = buscar(id);
        if(pacienteBuscado.isPresent()){
            repository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("No existe un paciente con el id " + id + ". Ingrese un id válido.");
        }

    }
}

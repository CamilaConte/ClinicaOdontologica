package com.dh.integrador.integrador.service;

import com.dh.integrador.integrador.entities.Odontologo;
import com.dh.integrador.integrador.entities.Paciente;
import com.dh.integrador.integrador.entities.Turno;
import com.dh.integrador.integrador.exceptions.BadRequestException;
import com.dh.integrador.integrador.exceptions.ResourceNotFoundException;
import com.dh.integrador.integrador.repository.OdontologoRepository;
import com.dh.integrador.integrador.repository.PacienteRepository;
import com.dh.integrador.integrador.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    TurnoRepository repository;
    @Autowired
    PacienteRepository pacienteRepository;
    @Autowired
    OdontologoRepository odontologoRepository;

    public Turno registrarTurno(Turno turno) throws BadRequestException{
        //controlamos que los id sean existentes
        Optional<Paciente> paciente = pacienteRepository.findById(turno.getPaciente().getId()); //tambien se puede usar el metodo buscar de este mismo service
        Optional<Odontologo> odontologo = odontologoRepository.findById(turno.getOdontologo().getId());

        if(paciente.isPresent() && odontologo.isPresent()){
            //entonces, podemos agregar el turno
            return repository.save(turno);
        }else{
            throw new BadRequestException("No se puede registrar el turno porque el paciente o el odontólogo no existen.");
        }

    }

    public List<Turno> listar(){
        return repository.findAll();
    }

    public void eliminar(Long id) throws ResourceNotFoundException{
        Optional<Turno> turnoBuscado = buscar(id);
        //isPresent es equivalente a "si tiene valor"
        if(turnoBuscado.isPresent()){
            repository.deleteById(id);
        }else {
            throw new ResourceNotFoundException("No existe un turno con el id "+ id + ". Ingresar un id correcto.");
        }
    }

    public Optional<Turno> buscar(Long id){
        return repository.findById(id);
    }

    public Turno actualizar(Turno turno){
        if (buscar(turno.getId()).isPresent()){
            return repository.save(turno);
        }else {
            return null;
        }
    }
}

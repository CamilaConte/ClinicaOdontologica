package com.dh.integrador.integrador.controller;

import com.dh.integrador.integrador.entities.Odontologo;
import com.dh.integrador.integrador.entities.Paciente;
import com.dh.integrador.integrador.entities.Turno;
import com.dh.integrador.integrador.exceptions.BadRequestException;
import com.dh.integrador.integrador.exceptions.ResourceNotFoundException;
import com.dh.integrador.integrador.service.OdontologoService;
import com.dh.integrador.integrador.service.PacienteService;
import com.dh.integrador.integrador.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    //inyeccion de dependencia
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno) throws BadRequestException {
        return ResponseEntity.ok(turnoService.registrarTurno(turno));
    }

    @GetMapping
    public ResponseEntity<List<Turno>> listarTurnos(){
        return ResponseEntity.ok(turnoService.listar());
    }

    @PutMapping
    public ResponseEntity<Turno> modificar(@RequestBody Turno turno){
        return ResponseEntity.ok(turnoService.actualizar(turno));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscar(@PathVariable Long id){
        if(turnoService.buscar(id).isPresent()){
            return ResponseEntity.ok(turnoService.buscar(id).get());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminar(id);
        return ResponseEntity.ok("Se eliminó el turno correctamente");
    }


}

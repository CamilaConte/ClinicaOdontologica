package com.dh.integrador.integrador.service;

import com.dh.integrador.integrador.entities.Domicilio;
import com.dh.integrador.integrador.entities.Odontologo;
import com.dh.integrador.integrador.entities.Paciente;
import com.dh.integrador.integrador.entities.Turno;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TurnoServiceTest {

    @Autowired
    PacienteService pacienteService;
    @Autowired
    OdontologoService odontologoService;
    @Autowired
    TurnoService turnoService;

    public void cargarDatos() throws Exception{
        Domicilio domicilio= new Domicilio("calle", 4562, "mendoza capital", "mendoza");
        Paciente paciente = pacienteService.guardar(new Paciente("Perez", "Juan", "jperez@hotmail.com", 12346, LocalDate.of(2020,04,01), domicilio));
        Odontologo odontologo = odontologoService.guardar(new Odontologo("Rafael", "Lopez", "123A"));

        turnoService.registrarTurno(new Turno(paciente, odontologo, LocalDate.of(2022, 12, 01)));
    }

    @Test
    public void registrarTest() throws Exception{
        cargarDatos();

        Optional<Turno> turnoBuscado = turnoService.buscar(1L);

        Assertions.assertNotNull(turnoBuscado);
    }

    @Test
    public void buscarTest() throws Exception{
        cargarDatos();

        Optional<Turno> turnoExistente = turnoService.buscar(1L);
        Optional<Turno> turnoNoExistente = turnoService.buscar(5L);

        //verifico si encuentra el turno creado y si sale null el turno que no fue creado

        Assertions.assertTrue(turnoExistente.isPresent());
        Assertions.assertFalse(turnoNoExistente.isPresent());

    }

    @Test
    public void eliminarTest() throws Exception{
        cargarDatos();

        turnoService.eliminar(1L);

        Optional<Turno> turnoBuscado = turnoService.buscar(1L);

        Assertions.assertFalse(turnoBuscado.isPresent());
    }

    @Test
    public void listarTest() throws Exception{
        cargarDatos();

        List<Turno> turnoList = turnoService.listar();

        Assertions.assertNotNull(turnoList);
    }
}
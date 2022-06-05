package com.dh.integrador.integrador.service;

import com.dh.integrador.integrador.entities.Domicilio;
import com.dh.integrador.integrador.entities.Paciente;
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
public class PacienteServiceTest {

    @Autowired
    PacienteService pacienteService;

    public void cargarDatos() throws Exception{
        Domicilio domicilio= new Domicilio("calle", 4562, "mendoza capital", "mendoza");
        Paciente paciente = pacienteService.guardar(new Paciente("Perez", "Juan", "jperez@hotmail.com", 12346, LocalDate.of(2020,04,01), domicilio));
    }

    @Test
    public void agregarTest() throws Exception{
        cargarDatos();

        Optional<Paciente> pacienteBuscado = pacienteService.buscar(1L);

        Assertions.assertTrue(pacienteBuscado.isPresent());
    }

    @Test
    public void buscarTest() throws Exception{
        cargarDatos();

        Optional<Paciente> pacienteExistente = pacienteService.buscar(1L);
        Optional<Paciente> pacienteNoExistente = pacienteService.buscar(5L);

        //verifico si encuentra el paciente creado y si sale null el paciente que no fue creado

        Assertions.assertTrue(pacienteExistente.isPresent());
        Assertions.assertFalse(pacienteNoExistente.isPresent());

    }

    @Test
    public void eliminarTest() throws Exception{
        cargarDatos();

        pacienteService.eliminar(1L);

        Optional<Paciente> pacienteBuscado = pacienteService.buscar(1L);

        Assertions.assertFalse(pacienteBuscado.isPresent());
    }

    @Test
    public void listarTest() throws Exception{
        cargarDatos();

        List<Paciente> pacienteList = pacienteService.listarPacientes();

        Assertions.assertNotNull(pacienteList);
    }
}
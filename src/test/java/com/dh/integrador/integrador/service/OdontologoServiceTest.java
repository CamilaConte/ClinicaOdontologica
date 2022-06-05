package com.dh.integrador.integrador.service;


import com.dh.integrador.integrador.entities.Odontologo;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class OdontologoServiceTest {

    @Autowired
    OdontologoService odontologoService;

    public void cargarDatos() throws Exception{
        Odontologo odontologo = odontologoService.guardar(new Odontologo("Rafael", "Lopez", "123A"));
    }

    @Test
    public void agregarTest() throws Exception{
        cargarDatos();

        Optional<Odontologo> odontologoBuscado = odontologoService.buscar(1L);

        Assertions.assertTrue(odontologoBuscado.isPresent());
    }

    @Test
    public void buscarTest() throws Exception{
        cargarDatos();

        Optional<Odontologo> odontologoExistente = odontologoService.buscar(1L);
        Optional<Odontologo> odontologoNoExistente = odontologoService.buscar(5L);

        //verifico si encuentra el odontologo creado y si sale null el odontologo que no fue creado

        Assertions.assertTrue(odontologoExistente.isPresent());
        Assertions.assertFalse(odontologoNoExistente.isPresent());

    }

    @Test
    public void eliminarTest() throws Exception{
        cargarDatos();

        odontologoService.eliminar(1L);

        Optional<Odontologo> odontologoBuscado = odontologoService.buscar(1L);

        Assertions.assertFalse(odontologoBuscado.isPresent());
    }

    @Test
    public void listarTest() throws Exception{
        cargarDatos();

        List<Odontologo> odontologoList = odontologoService.listarOdontologos();

        Assertions.assertNotNull(odontologoList);
    }
}
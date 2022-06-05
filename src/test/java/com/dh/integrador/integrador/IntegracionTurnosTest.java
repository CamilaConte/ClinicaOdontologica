package com.dh.integrador.integrador;

import com.dh.integrador.integrador.entities.Domicilio;
import com.dh.integrador.integrador.entities.Odontologo;
import com.dh.integrador.integrador.entities.Paciente;
import com.dh.integrador.integrador.entities.Turno;
import com.dh.integrador.integrador.service.OdontologoService;
import com.dh.integrador.integrador.service.PacienteService;
import com.dh.integrador.integrador.service.TurnoService;
import org.apache.tomcat.jni.Local;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionTurnosTest {

    @Autowired
    MockMvc mockMvc;

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
    public void listarTurnos() throws Exception{
        cargarDatos();
        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.get("/turnos")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        Assert.assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }
}

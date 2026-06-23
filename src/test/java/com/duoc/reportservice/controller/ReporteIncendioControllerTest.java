package com.duoc.reportservice.controller;

import com.duoc.reportservice.model.ReporteIncendio;
import com.duoc.reportservice.service.ReporteIncendioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReporteIncendioController.class)
class ReporteIncendioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ReporteIncendioService reporteIncendioService;

    @Test
    void testFindAll() throws Exception {
        ReporteIncendio r = new ReporteIncendio(1L, -33.456, -70.648, "Incendio", "url", ReporteIncendio.Estado.PENDIENTE);
        when(reporteIncendioService.findAll()).thenReturn(Arrays.asList(r));

        mockMvc.perform(get("/api/reporte-incendio"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].descripcion").value("Incendio"));
    }

    @Test
    void testFindById() throws Exception {
        ReporteIncendio r = new ReporteIncendio(1L, -33.456, -70.648, "Incendio", "url", ReporteIncendio.Estado.PENDIENTE);
        when(reporteIncendioService.finById(1L)).thenReturn(r);

        mockMvc.perform(get("/api/reporte-incendio/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descripcion").value("Incendio"));
    }

    @Test
    void testFindByEstado() throws Exception {
        ReporteIncendio r = new ReporteIncendio(1L, -33.456, -70.648, "Incendio", "url", ReporteIncendio.Estado.PENDIENTE);
        when(reporteIncendioService.findByEstado(ReporteIncendio.Estado.PENDIENTE)).thenReturn(Arrays.asList(r));

        mockMvc.perform(get("/api/reporte-incendio/estado").param("estado", "PENDIENTE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].estado").value("PENDIENTE"));
    }

    @Test
    void testFindByCoordenadas() throws Exception {
        ReporteIncendio r = new ReporteIncendio(1L, -33.456, -70.648, "Incendio", "url", ReporteIncendio.Estado.PENDIENTE);
        when(reporteIncendioService.findByLatitudLongitud(-33.456, -70.648)).thenReturn(Arrays.asList(r));

        mockMvc.perform(get("/api/reporte-incendio/coordenadas")
                        .param("latitud", "-33.456")
                        .param("longitud", "-70.648"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].latitud").value(-33.456));
    }

    @Test
    void testCreateReporte() throws Exception {
        ReporteIncendio r = new ReporteIncendio(1L, -33.456, -70.648, "Nuevo incendio", "url", ReporteIncendio.Estado.PENDIENTE);
        when(reporteIncendioService.save(any(ReporteIncendio.class))).thenReturn(r);

        mockMvc.perform(post("/api/reporte-incendio")
                        .contentType("application/json")
                        .content("{\"latitud\":-33.456,\"longitud\":-70.648,\"descripcion\":\"Nuevo incendio\",\"estado\":\"PENDIENTE\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descripcion").value("Nuevo incendio"));
    }

    @Test
    void testUpdateReporte() throws Exception {
        ReporteIncendio r = new ReporteIncendio(1L, -33.456, -70.648, "Actualizado", "url", ReporteIncendio.Estado.EN_COMBATE);
        when(reporteIncendioService.save(any(ReporteIncendio.class))).thenReturn(r);

        mockMvc.perform(put("/api/reporte-incendio/1")
                        .contentType("application/json")
                        .content("{\"latitud\":-33.456,\"longitud\":-70.648,\"descripcion\":\"Actualizado\",\"estado\":\"EN_COMBATE\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("EN_COMBATE"));
    }

    @Test
    void testDeleteReporte() throws Exception {
        mockMvc.perform(delete("/api/reporte-incendio/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testObtenerReportesPorEstado() throws Exception {
        Object[] fila = new Object[]{"PENDIENTE", 5L};
        when(reporteIncendioService.obtenerReportesPorEstado()).thenReturn(Arrays.asList(fila));

        mockMvc.perform(get("/api/reporte-incendio/group-by-estado"))
                .andExpect(status().isOk());
    }
}

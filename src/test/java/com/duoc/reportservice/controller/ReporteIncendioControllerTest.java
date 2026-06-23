package com.duoc.reportservice.controller;

import com.duoc.reportservice.model.ReporteIncendio;
import com.duoc.reportservice.service.ReporteIncendioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReporteIncendioController.class)
class ReporteIncendioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ReporteIncendioService reporteIncendioService;

    private ReporteIncendio reporteMock;

    @BeforeEach
    void setUp() {
        reporteMock = new ReporteIncendio();
        reporteMock.setId(1L);
        reporteMock.setLatitud(-33.456);
        reporteMock.setLongitud(-70.648);
        reporteMock.setDescripcion("Incendio en el bosque");
        reporteMock.setUrlEvidencia("http://example.com/evidencia.jpg");
        reporteMock.setEstado(ReporteIncendio.Estado.PENDIENTE);
    }

    @Test
    void testFindAll() throws Exception {
        when(reporteIncendioService.findAll()).thenReturn(Arrays.asList(reporteMock));

        mockMvc.perform(get("/api/reporte-incendio"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].descripcion").value("Incendio en el bosque"));

        verify(reporteIncendioService, times(1)).findAll();
    }

    @Test
    void testFindById() throws Exception {
        when(reporteIncendioService.finById(1L)).thenReturn(reporteMock);

        mockMvc.perform(get("/api/reporte-incendio/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("PENDIENTE"));

        verify(reporteIncendioService, times(1)).finById(1L);
    }

    @Test
    void testFindByEstado() throws Exception {
        when(reporteIncendioService.findByEstado(ReporteIncendio.Estado.PENDIENTE))
                .thenReturn(Arrays.asList(reporteMock));

        mockMvc.perform(get("/api/reporte-incendio/estado").param("estado", "PENDIENTE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));

        verify(reporteIncendioService, times(1)).findByEstado(ReporteIncendio.Estado.PENDIENTE);
    }

    @Test
    void testFindByCoordenadas() throws Exception {
        when(reporteIncendioService.findByLatitudLongitud(-33.456, -70.648))
                .thenReturn(Arrays.asList(reporteMock));

        mockMvc.perform(get("/api/reporte-incendio/coordenadas")
                        .param("latitud", "-33.456")
                        .param("longitud", "-70.648"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));

        verify(reporteIncendioService, times(1)).findByLatitudLongitud(-33.456, -70.648);
    }

    @Test
    void testCreateReporte() throws Exception {
        when(reporteIncendioService.save(any(ReporteIncendio.class))).thenReturn(reporteMock);

        mockMvc.perform(post("/api/reporte-incendio")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"latitud\":-33.456,\"longitud\":-70.648,\"descripcion\":\"Incendio en el bosque\",\"urlEvidencia\":\"http://example.com/evidencia.jpg\",\"estado\":\"PENDIENTE\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descripcion").value("Incendio en el bosque"));

        verify(reporteIncendioService, times(1)).save(any(ReporteIncendio.class));
    }

    @Test
    void testUpdateReporte() throws Exception {
        when(reporteIncendioService.save(any(ReporteIncendio.class))).thenReturn(reporteMock);

        mockMvc.perform(put("/api/reporte-incendio/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"latitud\":-33.456,\"longitud\":-70.648,\"descripcion\":\"Incendio en el bosque\",\"urlEvidencia\":\"http://example.com/evidencia.jpg\",\"estado\":\"PENDIENTE\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("PENDIENTE"));

        verify(reporteIncendioService, times(1)).save(any(ReporteIncendio.class));
    }

    @Test
    void testDeleteReporte() throws Exception {
        doNothing().when(reporteIncendioService).deleteById(1L);

        mockMvc.perform(delete("/api/reporte-incendio/1"))
                .andExpect(status().isOk());

        verify(reporteIncendioService, times(1)).deleteById(1L);
    }

    @Test
    void testObtenerReportesPorEstado() throws Exception {
        Object[] row1 = new Object[]{"PENDIENTE", 5L};
        Object[] row2 = new Object[]{"EN_COMBATE", 2L};
        List<Object[]> expected = Arrays.asList(row1, row2);
        when(reporteIncendioService.obtenerReportesPorEstado()).thenReturn(expected);

        mockMvc.perform(get("/api/reporte-incendio/group-by-estado"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));

        verify(reporteIncendioService, times(1)).obtenerReportesPorEstado();
    }
}

package com.duoc.reportservice.service;

import com.duoc.reportservice.model.ReporteIncendio;
import com.duoc.reportservice.repository.ReporteIncendioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReporteIncendioServiceTest {

    @Mock
    private ReporteIncendioRepository reporteIncendioRepository;

    @InjectMocks
    private ReporteIncendioService reporteIncendioService;

    private ReporteIncendio reporteMock;

    @BeforeEach
    void setUp() {
        reporteMock = new ReporteIncendio();
        reporteMock.setId(1L);
        reporteMock.setDescripcion("Fuego reportado cerca del cerro");
        reporteMock.setLatitud(-33.456);
        reporteMock.setLongitud(-70.648);
        reporteMock.setUrlEvidencia("http://ejemplo.com/foto.jpg");
        reporteMock.setEstado(ReporteIncendio.Estado.PENDIENTE);
    }

    @Test
    void testFindByEstado_Exitoso() {
        when(reporteIncendioRepository.findByEstado(ReporteIncendio.Estado.PENDIENTE))
                .thenReturn(Arrays.asList(reporteMock));

        List<ReporteIncendio> resultados = reporteIncendioService.findByEstado(ReporteIncendio.Estado.PENDIENTE);

        assertNotNull(resultados, "La lista de resultados no debe ser nula");
        assertFalse(resultados.isEmpty(), "La lista de resultados no debe estar vacía");
        assertEquals(1, resultados.size(), "Debe retornar exactamente 1 reporte");
        assertEquals(ReporteIncendio.Estado.PENDIENTE, resultados.get(0).getEstado(), "El estado del reporte debe coincidir");

        verify(reporteIncendioRepository, times(1)).findByEstado(ReporteIncendio.Estado.PENDIENTE);
    }

    @Test
    void testFindByLatitudLongitud_Exitoso() {
        when(reporteIncendioRepository.findByLatitudAndLongitud(-33.456, -70.648))
                .thenReturn(Arrays.asList(reporteMock));

        List<ReporteIncendio> resultados = reporteIncendioService.findByLatitudLongitud(-33.456, -70.648);

        assertNotNull(resultados);
        assertFalse(resultados.isEmpty());
        assertEquals(-33.456, resultados.get(0).getLatitud());
        assertEquals(-70.648, resultados.get(0).getLongitud());
        assertEquals("Fuego reportado cerca del cerro", resultados.get(0).getDescripcion());

        verify(reporteIncendioRepository, times(1)).findByLatitudAndLongitud(-33.456, -70.648);
    }
}
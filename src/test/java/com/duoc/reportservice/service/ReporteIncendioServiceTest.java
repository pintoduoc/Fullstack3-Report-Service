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
import java.util.Optional;

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
    void testFindAll_Exitoso() {
        when(reporteIncendioRepository.findAll()).thenReturn(Arrays.asList(reporteMock));

        List<ReporteIncendio> resultado = reporteIncendioService.findAll();

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(reporteIncendioRepository, times(1)).findAll();
    }

    @Test
    void testFinById_Exitoso() {
        when(reporteIncendioRepository.findById(1L)).thenReturn(Optional.of(reporteMock));

        ReporteIncendio resultado = reporteIncendioService.finById(1L);

        assertNotNull(resultado);
        assertEquals("Fuego reportado cerca del cerro", resultado.getDescripcion());
        verify(reporteIncendioRepository, times(1)).findById(1L);
    }

    @Test
    void testFinById_NoExistente() {
        when(reporteIncendioRepository.findById(99L)).thenReturn(Optional.empty());

        ReporteIncendio resultado = reporteIncendioService.finById(99L);

        assertNull(resultado);
        verify(reporteIncendioRepository, times(1)).findById(99L);
    }

    @Test
    void testFindByEstado_Exitoso() {
        when(reporteIncendioRepository.findByEstado(ReporteIncendio.Estado.PENDIENTE))
                .thenReturn(Arrays.asList(reporteMock));

        List<ReporteIncendio> resultados = reporteIncendioService.findByEstado(ReporteIncendio.Estado.PENDIENTE);

        assertNotNull(resultados);
        assertFalse(resultados.isEmpty());
        assertEquals(1, resultados.size());
        assertEquals(ReporteIncendio.Estado.PENDIENTE, resultados.get(0).getEstado());
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
        verify(reporteIncendioRepository, times(1)).findByLatitudAndLongitud(-33.456, -70.648);
    }

    @Test
    void testSave_Exitoso() {
        when(reporteIncendioRepository.save(any(ReporteIncendio.class))).thenReturn(reporteMock);

        ReporteIncendio resultado = reporteIncendioService.save(reporteMock);

        assertNotNull(resultado);
        assertEquals(ReporteIncendio.Estado.PENDIENTE, resultado.getEstado());
        verify(reporteIncendioRepository, times(1)).save(any(ReporteIncendio.class));
    }

    @Test
    void testDeleteById_Exitoso() {
        doNothing().when(reporteIncendioRepository).deleteById(1L);

        reporteIncendioService.deleteById(1L);

        verify(reporteIncendioRepository, times(1)).deleteById(1L);
    }

    @Test
    void testObtenerReportesPorEstado() {
        Object[] fila1 = new Object[]{"PENDIENTE", 5L};
        Object[] fila2 = new Object[]{"EN_COMBATE", 3L};
        when(reporteIncendioRepository.obtenerReportesPorEstado()).thenReturn(Arrays.asList(fila1, fila2));

        List<Object[]> resultado = reporteIncendioService.obtenerReportesPorEstado();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(reporteIncendioRepository, times(1)).obtenerReportesPorEstado();
    }
}

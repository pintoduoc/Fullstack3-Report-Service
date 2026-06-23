package com.duoc.reportservice.service;

import com.duoc.reportservice.model.ReporteIncendio;
import com.duoc.reportservice.repository.ReporteIncendioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteIncendioService {
    @Autowired
    private ReporteIncendioRepository reporteIncendioRepository;

    public List<ReporteIncendio> findAll() {
        return reporteIncendioRepository.findAll();
    }

    public ReporteIncendio finById(Long id) {
        return reporteIncendioRepository.findById(id).orElse(null);
    }

    public List<ReporteIncendio> findByEstado(ReporteIncendio.Estado estado) {
        return reporteIncendioRepository.findByEstado(estado);
    }

    public List<ReporteIncendio> findByLatitudLongitud(Double latitud, Double longitud) {
        return reporteIncendioRepository.findByLatitudAndLongitud(latitud, longitud);
    }

    public ReporteIncendio save(ReporteIncendio reporte) {
        return reporteIncendioRepository.save(reporte);
    }

    public void deleteById(Long id) {
        reporteIncendioRepository.deleteById(id);
    }

    public List<Object[]> obtenerReportesPorEstado() {
        return reporteIncendioRepository.obtenerReportesPorEstado();
    }
}

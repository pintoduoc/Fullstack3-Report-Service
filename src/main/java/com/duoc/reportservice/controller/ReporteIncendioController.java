package com.duoc.reportservice.controller;

import com.duoc.reportservice.model.ReporteIncendio;
import com.duoc.reportservice.service.ReporteIncendioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reporte-incendio")
public class ReporteIncendioController {
    @Autowired
    private ReporteIncendioService reporteIncendioService;

    //Obtener todos los reportes de incendio. No se requiere ningún parámetro ni cuerpo para esta consulta.
    @GetMapping
    public List<ReporteIncendio> findAll() {
        return reporteIncendioService.findAll();
    }

    //Obtener reporte de incendio por id. Se debe enviar el id del reporte en la URL. (Ejemplo: /api/reporte-incendio/1)
    @GetMapping("/{id}")
    public ReporteIncendio finById(@PathVariable Long id) {
        return reporteIncendioService.finById(id);
    }


    //Obtener reportes de incendio por estado. Se debe enviar el estado del reporte en la URL. (Ejemplo: /api/reporte-incendio/estado?estado=PENDIENTE)
    @GetMapping("/estado")
    public List<ReporteIncendio> findByEstado(@RequestParam ReporteIncendio.Estado estado) {
        return reporteIncendioService.findByEstado(estado);
    }

    //Obtener reportes de incendio por coordenadas. Se debe enviar las coordenadas exactas en la URL. (Ejemplo: /api/reporte-incendio/coordenadas?latitud=12.345&longitud=67.890)
    @GetMapping("/coordenadas")
    public List<ReporteIncendio> findByLatitudLongitud(
            @RequestParam Double latitud,
            @RequestParam Double longitud) {
        return reporteIncendioService.findByLatitudLongitud(latitud, longitud);
    }



    //Crear reporte. Se debe enviar el reporte en el cuerpo de la solicitud.
    //El cuerpo puede contener todos los campos del reporte, excepto el id.
    //Los campos del reporte a ingresar son: latitud, longitud, descripcion, urlEvidencia y estado (PENDIENTE/EN_COMBATE/CONTROLADO/EXTINGUIDO).
    //Ejemplo de cuerpo de solicitud:
    /*
    {
        "latitud": -33.456,
        "longitud": -70.654,
        "descripcion": "Incendio en el bosque",
        "urlEvidencia": "http://example.com/evidencia.jpg",
        "estado": "PENDIENTE"
    }
    */
    @PostMapping
    public ReporteIncendio createReporteIncendio(@RequestBody ReporteIncendio reporte) {
        return reporteIncendioService.save(reporte);
    }

    //Actualizar reporte por id. Se debe enviar el id del reporte a actualizar en la URL y el reporte actualizado en el cuerpo de la solicitud (sin id en el cuerpo).
    @PutMapping("/{id}")
    public ReporteIncendio updateReporteIncendio(@PathVariable Long id, @RequestBody ReporteIncendio reporte) {
        reporte.setId(id);
        return reporteIncendioService.save(reporte);
    }

    //Eliminar reporte por id. Se debe enviar el id del reporte a eliminar en la URL. (Ejemplo: /api/reporte-incendio/1)
    @DeleteMapping("/{id}")
    public void deleteReporteIncendio(@PathVariable Long id) {
        reporteIncendioService.deleteById(id);
    }

    //Obtener todos los reportes de incendio agrupados por estado. (Ejemplo: /api/reporte-incendio/group-by-estado)
    @GetMapping("/group-by-estado")
    public List<Object[]> obtenerReportesPorEstado() {
        return reporteIncendioService.obtenerReportesPorEstado();
    }
}

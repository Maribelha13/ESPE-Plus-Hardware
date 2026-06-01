package com.espe.service;

import com.espe.dto.ReporteCategoriaDTO;
import com.espe.entity.CategoriaHardware;
import com.espe.entity.EstadoHardware;
import com.espe.entity.HardwareEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
public class HardwareService {


    // Generar simulación de 10,000 registros para las pruebas de rendimiento
    private List<HardwareEntity> generarDatosSimulados() {
        List<HardwareEntity> lista = new ArrayList<>();
        Random random = new Random();
        CategoriaHardware[] categorias = CategoriaHardware.values();
        EstadoHardware[] estados = EstadoHardware.values();

        for (long i = 1; i <= 10000; i++) {
            String modelo = "Modelo-" + i;
            CategoriaHardware cat = categorias[random.nextInt(categorias.length)];
            BigDecimal precio = BigDecimal.valueOf(300 + (random.nextDouble() * 2200)); // Precios entre $300 y $2500


            // Alternar fechas: unas dentro de los últimos 5 años y otras más antiguas
            LocalDate fecha = LocalDate.now().minusYears(random.nextInt(8)).minusDays(random.nextInt(365));
            EstadoHardware est = estados[random.nextInt(estados.length)];

            lista.add(new HardwareEntity(i, modelo, cat, precio, fecha, est));
        }
        return lista;
    }

    /**
     * PROCESAMIENTO IMPERATIVO (Estructuras de control clásicas y bucles)
     * Requisito: Filtra compras de últimos 5 años, estado ACTIVO, agrupa por categoría,
     * calcula valor total, promedio y el equipo más caro.
     * Incluye simulación de hilos bloqueantes de la rúbrica.
     */
    public Map<CategoriaHardware, ReporteCategoriaDTO> procesarImperativo() {
        List<HardwareEntity> datos = generarDatosSimulados();
        LocalDate limiteFecha = LocalDate.now().minusYears(5);


        // Mapa para agrupar los resultados por categoría
        Map<CategoriaHardware, List<HardwareEntity>> aglomerado = new HashMap<>();

        int contadorSleep = 0;

        for (HardwareEntity equipo : datos) {


            // Simulación de Hilo Bloqueante: Aplica sleep de 1ms solo a los primeros 500 registros
            if (contadorSleep < 500) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                contadorSleep++;
            }

            // Filtros imperativos
            if (equipo.getFechaCompra().isAfter(limiteFecha) && equipo.getEstado() == EstadoHardware.ACTIVO) {
                CategoriaHardware cat = equipo.getCategoria();
                if (!aglomerado.containsKey(cat)) {
                    aglomerado.put(cat, new ArrayList<>());
                }
                aglomerado.get(cat).add(equipo);
            }
        }


        // Calcular métricas analíticas finales de forma imperativa
        Map<CategoriaHardware, ReporteCategoriaDTO> reporteFinal = new HashMap<>();

        for (Map.Entry<CategoriaHardware, List<HardwareEntity>> entry : aglomerado.entrySet()) {
            List<HardwareEntity> equiposFiltrados = entry.getValue();

            BigDecimal valorTotal = BigDecimal.ZERO;
            HardwareEntity masCaro = null;

            for (HardwareEntity e : equiposFiltrados) {
                valorTotal = valorTotal.add(e.getPrecio());
                if (masCaro == null || e.getPrecio().compareTo(masCaro.getPrecio()) > 0) {
                    masCaro = e;
                }
            }

            double promedio = equiposFiltrados.isEmpty() ? 0.0 :
                    valorTotal.doubleValue() / equiposFiltrados.size();

            reporteFinal.put(entry.getKey(), new ReporteCategoriaDTO(valorTotal, promedio, masCaro));
        }

        return reporteFinal;
    }

    /**
     * PROCESAMIENTO DECLARATIVO / FUNCIONAL (Java Streams API)
     * Realiza exactamente la misma lógica analítica pero de forma óptima y legible.
     * Incluye simulación de hilos bloqueantes de la rúbrica.
     */
    public Map<CategoriaHardware, ReporteCategoriaDTO> procesarFuncional() {
        List<HardwareEntity> datos = generarDatosSimulados();
        LocalDate limiteFecha = LocalDate.now().minusYears(5);


        // Simulador de hilos bloqueantes mapeado en el flujo declarativo
        java.util.concurrent.atomic.AtomicInteger contadorSleep = new java.util.concurrent.atomic.AtomicInteger(0);


        // Agrupación directa utilizando collectors funcionales
        Map<CategoriaHardware, List<HardwareEntity>> aglomerado = datos.stream()
                .peek(equipo -> {
                    if (contadorSleep.getAndIncrement() < 500) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                })
                .filter(e -> e.getFechaCompra().isAfter(limiteFecha))
                .filter(e -> e.getEstado() == EstadoHardware.ACTIVO)
                .collect(java.util.stream.Collectors.groupingBy(HardwareEntity::getCategoria));

        Map<CategoriaHardware, ReporteCategoriaDTO> reporteFinal = new HashMap<>();

        aglomerado.forEach((categoria, listaEquipos) -> {
            BigDecimal valorTotal = listaEquipos.stream()
                    .map(HardwareEntity::getPrecio)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            double promedio = listaEquipos.stream()
                    .mapToDouble(e -> e.getPrecio().doubleValue())
                    .average()
                    .orElse(0.0);

            HardwareEntity masCaro = listaEquipos.stream()
                    .max(Comparator.comparing(HardwareEntity::getPrecio))
                    .orElse(null);

            reporteFinal.put(categoria, new ReporteCategoriaDTO(valorTotal, promedio, masCaro));
        });

        return reporteFinal;
    }
}
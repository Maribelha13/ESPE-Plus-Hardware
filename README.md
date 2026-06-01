# Laboratorio de Programación: Evaluación de Paradigmas y Rendimiento

**Institución:** Universidad de las Fuerzas Armadas (ESPE)  
**Asignatura:** Programación Avanzada  
**Proyecto:** AUTÓNOMO Comparar la eficiencia y legibilidad de distintos paradigmas de programación mediante la resolución de un problema algorítmico complejo.
**Nombre:** Maribel Amaguaña
**NRC:** 30405
**Fecha:** 31 de mayo de 2026

---

## 1. Descripción del Proyecto
Este módulo forma parte del ecosistema **ESPE-Plus** y está diseñado para realizar análisis de datos masivos sobre el inventario de infraestructura tecnológica. El sistema simula la carga y procesamiento analítico de **10,000 registros** de hardware (`HardwareEntity`) clasificándolos por categorías (Servidores, PCs, Laptops), aplicando reglas de negocio financieras estrictas mediante `BigDecimal` y filtros de auditoría temporal (compras de los últimos 5 años y en estado `ACTIVO`).

El objetivo principal del laboratorio es evaluar de forma práctica y crítica el rendimiento, la legibilidad y la eficiencia entre dos paradigmas de desarrollo en Java: **Procesamiento Imperativo Tradicional** frente a **Procesamiento Declarativo/Funcional (Java Streams API)**, incluyendo simulaciones de penalización por hilos bloqueantes (`Thread.sleep`) para emular latencias de red en entornos distribuidos modernos.

---

## 2. Cuadro Comparativo de Rendimiento

A continuación, se presentan los resultados reales de tiempo de ejecución obtenidos de forma nativa en el entorno local de desarrollo tras desplegar la aplicación y consumir los endpoints analíticos expuestos en el controlador de la API:

| Métrica / Criterio | Enfoque Imperativo (Bucles Clásicos) | Enfoque Declarativo / Funcional (Java Streams API) |
| :--- | :--- | :--- |
| **Tiempo de Ejecución** | **3991 ms** | **1668 ms** |
| **Estructuras Utilizadas** | Bucles `for-each`, condicionales anidados, control manual de mapas (`HashMap`), inicialización y mutación explícita de variables acumuladoras. | Flujo continuo de tuberías (`Stream`), operaciones intermedias inertes (`filter`, `peek`) y colectores de reducción avanzados (`groupingBy`). |
| **Legibilidad del Código** | Código verboso y extenso. Requiere múltiples niveles de indentación, gestión manual de excepciones para la concurrencia y control de llaves propenso a errores de sintaxis. | Código compacto, declarativo y expresivo. Describe el **"qué se debe hacer"** en lugar del "cómo", centralizando la lógica en un pipeline limpio. |
| **Manejo de Estados** | Estado mutable. El mapa y los acumuladores cambian de valor constantemente en cada ciclo de la iteración. | Estado inmutable/aislado. El flujo procesa los datos de manera limpia, delegando la mutación interna de manera segura al colector. |

---

## 3. Análisis Crítico y Conclusiones

* **Eficiencia Temporal Brutal:** El enfoque **Declarativo/Funcional (1668 ms)** superó al enfoque **Imperativo (3991 ms)** con una reducción de tiempo superior al 58%. Esto demuestra que el pipeline de Java Streams permite al compilador y a la Máquina Virtual de Java (JVM) optimizar las rutinas de filtrado y el empaquetado de datos en memoria de forma mucho más ágil que la construcción y verificación secuencial manual de colecciones del ciclo `for`.
* **Impacto de Hilos Bloqueantes:** El uso controlado de `Thread.sleep(1)` para los primeros 500 registros introdujo una penalización base exacta de 500 milisegundos en ambos paradigmas. A pesar de compartir exactamente este mismo cuello de botella simulado, la arquitectura de Streams manejó la acumulación y reducción analítica posterior de los 9,500 registros restantes con una latencia de procesamiento marginalmente inferior.
* **Precisión Financiera:** La implementación de la clase `BigDecimal` en lugar de primitivos de punto flotante (`double`/`float`) garantizó una precisión matemática absoluta en el cálculo del `valorTotal` de cada categoría (superando el millón y medio de dólares simulados por sección) sin arrastrar los típicos errores microscópicos de redondeo binario, cumpliendo con los estándares de calidad de software financiero.
* **Mantenibilidad del Software:** El paradigma funcional reduce significativamente la complejidad ciclomática del código. Al eliminar variables bandera, inicializadores vacíos y bifurcaciones `if-else` anidadas, se disminuye drásticamente la probabilidad de introducir errores de lógica o problemas sintácticos comunes durante el mantenimiento a largo plazo de la plataforma de infraestructura de TICs.
# Gestión de Consultorio Médico (Java)

Sistema de escritorio para la gestión de pacientes y turnos de un consultorio médico, desarrollado en java en Java.

  <img width="1352" height="761" alt="image" src="https://github.com/user-attachments/assets/d566d764-52a8-4f26-a726-72c3e03ece68" />
  <img width="1352" height="761" alt="image" src="https://github.com/user-attachments/assets/9c9d3540-bf07-48c8-87b7-c0acd39b763e" />
  <img width="1352" height="761" alt="image" src="https://github.com/user-attachments/assets/bc81d92e-c761-47f8-88a2-b52f920c5ee1" />


---

## Objetivo del Proyecto

El objetivo principal es construir una aplicación de escritorio funcional aplicando el patrón de diseño **Modelo-Vista-Controlador (MVC)**.
---

## Características Principales

* **Gestión de Pacientes:** Funcionalidad CRUD (Crear, Leer, Actualizar, Borrar) completa para pacientes.
* **Gestión de Turnos:** Creación y asignación de turnos.
* **Patrón de Diseño MVC:** La arquitectura del proyecto separa claramente la lógica de negocio (Modelo), la interfaz de usuario (Vista) y el manejo de entradas (Controlador).
* **Persistencia con JDBC Puro:** En lugar de depender de un ORM, el proyecto maneja la conexión y las consultas a la base de datos directamente con JDBC. Esto asegura un entendimiento profundo de la capa de persistencia en Java.
* **Principios de Diseño:** Se aplicaron principios de diseño como la [Menciona uno que uses, ej: "Separación de responsabilidades (SRP)"] para mantener un código limpio y mantenible.

---

## Tecnologías Utilizadas

* **Lenguaje:** Java
* **Interfaz Gráfica:** [Swing]
* **Base de Datos:** [MySQL Workbench]
* **Conexión:** JDBC
* **Gestión de Dependencias:** [Maven]

---

## Cómo Ejecutar

1.  Clonar el repositorio: `git clone https://github.com/lauta12/ConsultorioMedico`
2.  Importar el proyecto en [Tu IDE, ej: IntelliJ IDEA o Eclipse].
3.  Configurar la conexión a la base de datos en `[src/main/java/ConsultorioMedico/util/Conexion.java]`.
4.  Ejecutar la clase principal `Main.java`.

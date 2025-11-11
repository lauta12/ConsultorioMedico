# 🏥 Gestión de Consultorio Médico (Java)

Sistema de escritorio para la gestión de pacientes y turnos de un consultorio médico, desarrollado en Java.

  <img width="1352" height="761" alt="image" src="https://github.com/user-attachments/assets/d566d764-52a8-4f26-a726-72c3e03ece68" />
  <img width="1352" height="761" alt="image" src="https://github.com/user-attachments/assets/9c9d3540-bf07-48c8-87b7-c0acd39b763e" />
  <img width="1352" height="761" alt="image" src="https://github.com/user-attachments/assets/cf3122b5-1672-4fba-8473-f0c2f15ee428" />


---

## 🎯 Objetivo del Proyecto

El objetivo principal es construir una aplicación de escritorio funcional aplicando el patrón de diseño **Modelo-Vista-Controlador (MVC)**.
---

## 💡 Características Principales

* **Gestión de Pacientes:** Funcionalidad CRUD (Crear, Leer, Actualizar, Borrar) completa para pacientes.
* **Gestión de Turnos:** Creación y asignación de turnos.
* **Patrón de Diseño MVC:** La arquitectura del proyecto separa la lógica de negocio (Modelo), la interfaz de usuario (Vista) y el manejo de entradas (Controlador).
* **Persistencia con JDBC:** En lugar de depender de un ORM, el proyecto maneja la conexión y las consultas a la base de datos directamente con JDBC.
* **Principios de Diseño:** Se aplicaron principios de diseño como la Separación de responsabilidades (SRP) y DAO (Data Access Object para mantener un código limpio y mantenible.

---

## 🛠️ Tecnologías Utilizadas

* **Lenguaje:** Java
* **Interfaz Gráfica:** Swing
* **Base de Datos:** MySQL
* **Conexión:** JDBC
* **Gestión de Dependencias:** Maven

---

## 🚀 Cómo Ejecutar

1.  Clonar el repositorio: `git clone https://github.com/lauta12/ConsultorioMedico`.
2.  Importar el proyecto en Cualquier IDE: IntelliJ IDEA, Eclipse, VSCode, etc.
3.  Abrir MySQLWorkbench y crear la base de datos usando el archivo `database.sql` en la raíz del proyecto: `ConsultorioMedico/database.sql`.
4.  (Opcional) Cargar datos a la base de datos usando el archivo `mock_data.sql` en la raíz del proyecto: `ConsultorioMedico/mock_data.sql`.
5.  Configurar la conexión a la base de datos en `src/main/java/ConsultorioMedico/util/Conexion.java`.
6.  Ejecutar la clase principal `Main.java` en `src/main/java/Main.java`.

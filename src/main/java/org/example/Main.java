package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String usuario = "RIBERA";
        String contraseña = "ribera";

        //Tabla DEPARTAMENTO
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
             Statement statement = conn.createStatement()) {

            try { statement.executeUpdate("DROP TABLE EMPLEADO");
            } catch (SQLException e) {
            }
            try { statement.executeUpdate("DROP TABLE DEPARTAMENTO"); } catch (SQLException e) {
            }

            String sql = "CREATE TABLE DEPARTAMENTO (" +
                    "id NUMBER PRIMARY KEY, " +
                    "nombre VARCHAR2(100))";

            statement.executeUpdate(sql);
            System.out.println("Tabla 'DEPARTAMENTO' creada");

        } catch (SQLException e) {
            System.out.println("Error al crear la tabla DEPARTAMENTO: " + e.getMessage());
        }

        // Tabla EMPLEADO
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
             Statement statement = conn.createStatement()) {

            String sql = "CREATE TABLE EMPLEADO (" +
                    "id NUMBER PRIMARY KEY, " +
                    "nombre VARCHAR2(100), " +
                    "salario NUMBER(10, 2), " +
                    "departamento_id NUMBER, " +
                    "CONSTRAINT fk_departamento FOREIGN KEY (departamento_id) REFERENCES departamento(id))";

            statement.executeUpdate(sql);
            System.out.println("Tabla 'EMPLEADO' creada");

        } catch (SQLException e) {
            System.out.println("Error al crear la tabla EMPLEADO: " + e.getMessage());
        }
    }
}

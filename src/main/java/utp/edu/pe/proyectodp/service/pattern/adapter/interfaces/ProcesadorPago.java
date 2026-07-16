// service/pattern/adapter/interfaces/ProcesadorPago.java
package utp.edu.pe.proyectodp.service.pattern.adapter.interfaces;

public interface ProcesadorPago {
    String getCodigo();
    void procesarPago(double monto);
}
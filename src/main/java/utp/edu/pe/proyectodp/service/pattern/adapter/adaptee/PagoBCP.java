package utp.edu.pe.proyectodp.service.pattern.adapter.adaptee;

public class PagoBCP {

    public void pagarConBCP(double monto){

        log.info("Pago realizado con banco BCP: S/ " + monto);

    }
}
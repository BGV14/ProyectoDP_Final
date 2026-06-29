package utp.edu.pe.proyectodp.service.pattern.adapter.adaptee;

public class PagoBCP {

    public void pagarConBCP(double monto){

        System.out.println("Pago realizado con banco BCP: S/ " + monto);

    }
}
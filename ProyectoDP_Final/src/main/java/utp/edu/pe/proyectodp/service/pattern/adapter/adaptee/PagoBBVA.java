package utp.edu.pe.proyectodp.service.pattern.adapter.adaptee;

public class PagoBBVA {

    public void pagarConBBVA(double monto){

        System.out.println("Pago realizado con banco BBVA: S/ " + monto);
    }
}
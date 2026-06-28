package utp.edu.pe.proyectodp.service.pattern.adapter.adaptee;

public class PagoMastercard {

    public void pagarConMastercard(double monto){

        System.out.println("Pago realizado con tarjeta Mastercard: S/ " + monto);
    }
}
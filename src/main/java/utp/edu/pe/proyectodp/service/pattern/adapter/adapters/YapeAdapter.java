package utp.edu.pe.proyectodp.service.pattern.adapter.adapters;

import utp.edu.pe.proyectodp.service.pattern.adapter.adaptee.PagoYape;
import utp.edu.pe.proyectodp.service.pattern.adapter.interfaces.ProcesadorPago;

public class YapeAdapter implements ProcesadorPago {

    private PagoYape pagoYape;

    public YapeAdapter(PagoYape pagoYape){

        this.pagoYape=pagoYape;
    }

    @Override
    public void procesarPago(double monto) {

        pagoYape.pagarConYape(monto);
    }
}
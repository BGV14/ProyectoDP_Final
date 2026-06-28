package utp.edu.pe.proyectodp.service.pattern.adapter.adapters;

import utp.edu.pe.proyectodp.service.pattern.adapter.adaptee.PagoVisa;
import utp.edu.pe.proyectodp.service.pattern.adapter.interfaces.ProcesadorPago;

public class VisaAdapter implements ProcesadorPago {

    private PagoVisa pagoVisa;

    public VisaAdapter(PagoVisa pagoVisa){

        this.pagoVisa=pagoVisa;
    }

    @Override
    public void procesarPago(double monto) {

        pagoVisa.pagarConVisa(monto);
    }
}
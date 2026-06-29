package utp.edu.pe.proyectodp.service.pattern.adapter.adapters;

import utp.edu.pe.proyectodp.service.pattern.adapter.adaptee.PagoMastercard;
import utp.edu.pe.proyectodp.service.pattern.adapter.interfaces.ProcesadorPago;

public class MastercardAdapter implements ProcesadorPago {

    private PagoMastercard pagoMastercard;

    public MastercardAdapter(PagoMastercard pagoMastercard){

        this.pagoMastercard=pagoMastercard;

    }

    @Override
    public void procesarPago(double monto) {

        pagoMastercard.pagarConMastercard(monto);
    }
}
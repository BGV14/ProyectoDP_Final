package utp.edu.pe.proyectodp.service.pattern.adapter.adapters;

import org.springframework.stereotype.Component;
import utp.edu.pe.proyectodp.service.pattern.adapter.adaptee.PagoMastercard;
import utp.edu.pe.proyectodp.service.pattern.adapter.interfaces.ProcesadorPago;

@Component
public class MastercardAdapter implements ProcesadorPago {
    private final PagoMastercard pagoMastercard;
    public MastercardAdapter(){ this.pagoMastercard = new PagoMastercard(); }
    @Override public String getCodigo(){ return "MASTERCARD"; }
    @Override public void procesarPago(double monto){ pagoMastercard.pagarConMastercard(monto); }
}
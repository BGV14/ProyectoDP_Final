package utp.edu.pe.proyectodp.service.pattern.adapter.adapters;

import org.springframework.stereotype.Component;
import utp.edu.pe.proyectodp.service.pattern.adapter.adaptee.PagoVisa;
import utp.edu.pe.proyectodp.service.pattern.adapter.interfaces.ProcesadorPago;

@Component
public class VisaAdapter implements ProcesadorPago {
    private final PagoVisa pagoVisa;
    public VisaAdapter(){ this.pagoVisa = new PagoVisa(); }
    @Override public String getCodigo(){ return "VISA"; }
    @Override public void procesarPago(double monto){ pagoVisa.pagarConVisa(monto); }
}
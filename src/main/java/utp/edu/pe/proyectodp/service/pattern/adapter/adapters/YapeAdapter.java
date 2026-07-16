package utp.edu.pe.proyectodp.service.pattern.adapter.adapters;

import org.springframework.stereotype.Component;
import utp.edu.pe.proyectodp.service.pattern.adapter.adaptee.PagoYape;
import utp.edu.pe.proyectodp.service.pattern.adapter.interfaces.ProcesadorPago;

@Component
public class YapeAdapter implements ProcesadorPago {
    private final PagoYape pagoYape;
    public YapeAdapter(){ this.pagoYape = new PagoYape(); }
    @Override public String getCodigo(){ return "YAPE"; }
    @Override public void procesarPago(double monto){ pagoYape.pagarConYape(monto); }
}
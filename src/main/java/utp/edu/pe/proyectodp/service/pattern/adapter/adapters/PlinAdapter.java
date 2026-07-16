package utp.edu.pe.proyectodp.service.pattern.adapter.adapters;

import org.springframework.stereotype.Component;
import utp.edu.pe.proyectodp.service.pattern.adapter.adaptee.PagoPlin;
import utp.edu.pe.proyectodp.service.pattern.adapter.interfaces.ProcesadorPago;

@Component
public class PlinAdapter implements ProcesadorPago {
    private final PagoPlin pagoPlin;
    public PlinAdapter(){ this.pagoPlin = new PagoPlin(); }
    @Override public String getCodigo(){ return "PLIN"; }
    @Override public void procesarPago(double monto){ pagoPlin.pagarConPlin(monto); }
}
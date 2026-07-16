package utp.edu.pe.proyectodp.service.pattern.adapter.adapters;

import org.springframework.stereotype.Component;
import utp.edu.pe.proyectodp.service.pattern.adapter.adaptee.PagoBCP;
import utp.edu.pe.proyectodp.service.pattern.adapter.interfaces.ProcesadorPago;

@Component
public class BCPAdapter implements ProcesadorPago {
    private final PagoBCP pagoBCP;
    public BCPAdapter(){ this.pagoBCP = new PagoBCP(); }
    @Override public String getCodigo(){ return "BCP"; }
    @Override public void procesarPago(double monto){ pagoBCP.pagarConBCP(monto); }
}
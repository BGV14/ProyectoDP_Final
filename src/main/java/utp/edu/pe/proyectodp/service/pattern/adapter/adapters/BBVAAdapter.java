// adapters/BBVAAdapter.java
package utp.edu.pe.proyectodp.service.pattern.adapter.adapters;

import org.springframework.stereotype.Component;
import utp.edu.pe.proyectodp.service.pattern.adapter.adaptee.PagoBBVA;
import utp.edu.pe.proyectodp.service.pattern.adapter.interfaces.ProcesadorPago;

@Component
public class BBVAAdapter implements ProcesadorPago {

    private final PagoBBVA pagoBBVA;

    public BBVAAdapter() {
        this.pagoBBVA = new PagoBBVA();
    }

    @Override
    public String getCodigo() {
        return "BBVA";
    }

    @Override
    public void procesarPago(double monto) {
        pagoBBVA.pagarConBBVA(monto);
    }
}
package utp.edu.pe.proyectodp.service.pattern.adapter.adapters;

import utp.edu.pe.proyectodp.service.pattern.adapter.adaptee.PagoBBVA;
import utp.edu.pe.proyectodp.service.pattern.adapter.interfaces.ProcesadorPago;

public class BBVAAdapter implements ProcesadorPago {

    private PagoBBVA pagoBBVA;

    public BBVAAdapter(PagoBBVA pagoBBVA){

        this.pagoBBVA=pagoBBVA;

    }

    @Override
    public void procesarPago(double monto) {

        pagoBBVA.pagarConBBVA(monto);

    }

}
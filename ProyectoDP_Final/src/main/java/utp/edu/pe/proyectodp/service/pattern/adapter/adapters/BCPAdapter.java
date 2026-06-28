package utp.edu.pe.proyectodp.service.pattern.adapter.adapters;

import utp.edu.pe.proyectodp.service.pattern.adapter.adaptee.PagoBCP;
import utp.edu.pe.proyectodp.service.pattern.adapter.interfaces.ProcesadorPago;

public class BCPAdapter implements ProcesadorPago {

    private PagoBCP pagoBCP;

    public BCPAdapter(PagoBCP pagoBCP){

        this.pagoBCP=pagoBCP;

    }

    @Override
    public void procesarPago(double monto) {

        pagoBCP.pagarConBCP(monto);
    }
}

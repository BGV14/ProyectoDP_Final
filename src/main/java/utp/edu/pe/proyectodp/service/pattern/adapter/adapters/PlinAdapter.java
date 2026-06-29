package utp.edu.pe.proyectodp.service.pattern.adapter.adapters;

import utp.edu.pe.proyectodp.service.pattern.adapter.adaptee.PagoPlin;
import utp.edu.pe.proyectodp.service.pattern.adapter.interfaces.ProcesadorPago;

public class PlinAdapter implements ProcesadorPago {

    private PagoPlin pagoPlin;

    public PlinAdapter(PagoPlin pagoPlin){

        this.pagoPlin=pagoPlin;

    }

    @Override
    public void procesarPago(double monto) {

        pagoPlin.pagarConPlin(monto);
    }

}
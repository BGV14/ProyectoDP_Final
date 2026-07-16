package utp.edu.pe.proyectodp.service.pattern.adapter.adaptee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PagoBCP {

    public void pagarConBCP(double monto){

        log.info("Pago realizado con banco BCP: S/ " + monto);

    }
}
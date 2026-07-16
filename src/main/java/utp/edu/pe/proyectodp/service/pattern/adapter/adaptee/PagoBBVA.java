package utp.edu.pe.proyectodp.service.pattern.adapter.adaptee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PagoBBVA {

    public void pagarConBBVA(double monto){

        log.info("Pago realizado con banco BBVA: S/ " + monto);
    }
}
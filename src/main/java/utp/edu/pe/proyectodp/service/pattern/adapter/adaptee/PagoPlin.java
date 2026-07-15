package utp.edu.pe.proyectodp.service.pattern.adapter.adaptee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PagoPlin {

    public void pagarConPlin(double monto){
        log.info("Pago realizado con Plin: S/{} ",monto);
    }
}
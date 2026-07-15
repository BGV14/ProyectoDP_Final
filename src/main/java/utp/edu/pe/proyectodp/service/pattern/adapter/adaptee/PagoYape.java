package utp.edu.pe.proyectodp.service.pattern.adapter.adaptee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PagoYape {

    public void pagarConYape(double monto){
        log.info("Pago realizado con Yape: S/{} ", monto);
    }
}
package utp.edu.pe.proyectodp.service.pattern.adapter.adaptee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PagoMastercard {

    public void pagarConMastercard(double monto){
        log.info("Pago realizado con tarjeta Mastercard: S/{} " + monto);
    }
}
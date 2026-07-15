package utp.edu.pe.proyectodp.service.pattern.adapter.adaptee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PagoVisa {

    public void pagarConVisa(double monto){
        log.info("Pago realizado con tarjeta Visa: S/{} ", monto);
    }
}

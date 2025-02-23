package com.example.demo.web.restcontroller;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/pagos")
@CrossOrigin(origins = "*")
public class PaymentRestController {

    @Value("${stripe.public.key}")
    private String stripePublicKey;

    @PostMapping("/crear-pago")
    public Map<String, String> crearSesionPago(@RequestBody Map<String, Object> datos) throws StripeException {
        Long precio = ((Number) datos.get("precio")).longValue();
        String moneda = (String) datos.get("moneda");
        String descripcion = (String) datos.get("descripcion");

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:4200/pago-exitoso")
                .setCancelUrl("http://localhost:4200/pago-cancelado")
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency(moneda)
                                                .setUnitAmount(precio)
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName(descripcion)
                                                                .build()
                                                )
                                                .build()
                                )
                                .build()
                )
                .build();

        Session session = Session.create(params);
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("id", session.getId());
        return respuesta;
    }
}
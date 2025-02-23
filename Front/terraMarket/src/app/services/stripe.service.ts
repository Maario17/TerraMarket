import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { firstValueFrom } from 'rxjs';
import { loadStripe } from '@stripe/stripe-js';

@Injectable({
  providedIn: 'root'
})
export class StripeService {
  private stripePromise = loadStripe('pk_test_xxxxxxxxxxxxxxxxx');

  constructor(private http: HttpClient) { }

  async pagar(precio: number, moneda: string, descripcion: string) {
    const datos = { precio, moneda, descripcion };
    const res = await firstValueFrom(this.http.post<{ id: string }>('http://localhost:8888/api/pagos/crear-sesion', datos));

    const stripe = await this.stripePromise;
    if (stripe && res?.id) {
      await stripe.redirectToCheckout({ sessionId: res.id });
    }
  }
}

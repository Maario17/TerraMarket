import { Cliente } from './../../models/cliente';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { Pedido } from '../../models/pedido';
import { HttpClient } from '@angular/common/http';
import { LineaPedido } from '../../models/lineaPedido';

@Injectable({
  providedIn: 'root'
})
export class CestaService {

  private baseUrl = `http://localhost:8888/ws/pedidos`;

  constructor(private httpClient: HttpClient) { }

  findPedidoPendiente(cliente: Cliente) {
    cliente.id = 1;
    const url = `${this.baseUrl}/buscarPedidoPendiente?idCliente=${cliente.id}`;
    console.log(url);
    return this.httpClient.get<Pedido>(url);


  }
  alterLineaPedido(lineaPedido: LineaPedido | null, cantidad: number, cliente: Cliente): Observable<LineaPedido> {
    cliente.id = 1;
    this.baseUrl = `http://localhost:8888/ws/clientes/${cliente.id}/pedidos`;
    console.log(this.baseUrl);
    const url = `${this.baseUrl}/${lineaPedido?.pedido?.id}/alterLineaPedido/${lineaPedido?.id}`;
    console.log(url);

    const body = cantidad;
    return this.httpClient.put<LineaPedido>(url, body);
  }

  deleteLineaPedido(lineaPedido: LineaPedido | null, cliente: Cliente) {
    cliente.id = 1;
    this.baseUrl = `http://localhost:8888/ws/clientes/${cliente.id}/pedidos`;
    console.log(this.baseUrl);
    const url = `${this.baseUrl}/${lineaPedido?.pedido?.id}/alterLineaPedido/${lineaPedido?.id}`;
    console.log(url);

    return this.httpClient.delete<LineaPedido>(url );
  }
}

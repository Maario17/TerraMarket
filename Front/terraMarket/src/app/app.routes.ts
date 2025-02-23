import { Routes } from '@angular/router';
import path from 'node:path';
import { PedidosComponent } from './components/pedidos/pedidos.component';
import { DetallesPedidoComponent } from './components/detalles-pedido/detalles-pedido.component';
import { EditarPerfilClienteComponent } from './components/editar-perfil-cliente/editar-perfil-cliente.component';
import { RealizarPedidoComponent } from './components/realizar-pedido/realizar-pedido.component';
import { CestaComponent } from './components/cesta/cesta.component';
import { MarketViewComponent } from './components/market-view/market-view.component';

export const routes: Routes = [
  { path: '', redirectTo: '/pedidos', pathMatch: 'full' },
  { path: 'pedidos', component: PedidosComponent },
  { path: 'detalles-pedido', component: DetallesPedidoComponent },
  { path: 'mercado/:id', component: MarketViewComponent },
  { path: 'cesta', component: CestaComponent },
  { path: 'realizar-pedido', component: RealizarPedidoComponent },
];

import { Component } from '@angular/core';
import { Location } from '@angular/common';

@Component({
  selector: 'app-back',
  standalone: true,
  imports: [],
  templateUrl: './back.component.html',
  styleUrl: './back.component.scss'
})
export class BackComponent {

  constructor (private location:Location){} // autowired

  volverAtras():void{
    this.location.back();
    //console.log("atras")
  }
}

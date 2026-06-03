import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { SesionService } from '../../servicios/sesion.service';

@Component({
  selector: 'app-ingreso',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './ingreso.component.html',
  styleUrl: './ingreso.component.css'
})
export class IngresoComponent {
  email = 'admin@minicity.com';
  password = 'admin123';
  mensaje = '';
  cargando = false;

  constructor(private sesion: SesionService, private router: Router) {}

  ingresar(): void {
    this.mensaje = '';
    this.cargando = true;

    this.sesion.ingresar(this.email, this.password).subscribe({
      next: (respuesta) => {
        this.cargando = false;
        if (respuesta.success) {
          this.sesion.guardar(respuesta.token);
          this.router.navigate(['/panel']);
          return;
        }
        this.mensaje = respuesta.mensaje;
      },
      error: () => {
        this.cargando = false;
        this.mensaje = 'No se pudo conectar con el Back-End en localhost:8081';
      }
    });
  }
}

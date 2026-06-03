import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { forkJoin } from 'rxjs';
import { Nino, Reserva, Resumen, TipoEntrada } from '../../modelos/core';
import { CoreService } from '../../servicios/core.service';
import { SesionService } from '../../servicios/sesion.service';

@Component({
  selector: 'app-panel',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './panel.component.html',
  styleUrl: './panel.component.css'
})
export class PanelComponent implements OnInit {
  resumen: Resumen = {
    registros: 0,
    monedas: 0,
    cumpleanos: 0,
    escolares: 0,
    reservas: 0,
    visitas: 0,
    personasMesActual: 0,
    personasMesAnterior: 0,
    comparacionTemporada: {
      mesActual: '',
      mesAnterior: '',
      personasMesActual: 0,
      personasMesAnterior: 0,
      diferencia: 0,
      porcentaje: 0,
      estado: 'igual',
      mensaje: ''
    }
  };

  ninos: Nino[] = [];
  reservas: Reserva[] = [];
  tiposEntrada: TipoEntrada[] = [];
  mensaje = '';
  cargando = true;
  guardando = false;
  mostrarFormulario = false;

  nuevoNino: Nino = this.ninoVacio();
  nuevaReserva: Reserva = this.reservaVacia();

  constructor(
    private core: CoreService,
    private sesion: SesionService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.cargarTodo();
  }

  cargarTodo(): void {
    this.cargando = true;
    this.mensaje = '';

    forkJoin({
      resumen: this.core.resumen(),
      ninos: this.core.ninos(),
      reservas: this.core.reservas(),
      tipos: this.core.tiposEntrada()
    }).subscribe({
      next: (datos) => {
        this.resumen = datos.resumen;
        this.ninos = datos.ninos;
        this.reservas = datos.reservas;
        this.tiposEntrada = datos.tipos;
        this.cargando = false;
      },
      error: () => {
        this.cargando = false;
        this.mensaje = 'No se pudo cargar la informacion. Revisa que el Back-End este prendido en localhost:8081.';
      }
    });
  }

  guardarNino(): void {
    this.guardando = true;

    const peticion = this.nuevoNino.id
      ? this.core.actualizarNino(this.nuevoNino.id, this.nuevoNino)
      : this.core.crearNino(this.nuevoNino);

    peticion.subscribe({
      next: () => {
        this.guardando = false;
        this.nuevoNino = this.ninoVacio();
        this.mostrarFormulario = false;
        this.cargarTodo();
      },
      error: (error) => {
        this.guardando = false;
        this.mensaje = error?.error?.mensaje || 'No se pudo guardar el nino';
      }
    });
  }

  editarNino(nino: Nino): void {
    this.nuevoNino = {
      ...nino,
      tipoEntradaId: nino.tipoEntradaId || nino.tipoEntrada?.id || 1
    };
    this.mostrarFormulario = true;
    this.mensaje = '';
  }

  cancelarEdicion(): void {
    this.nuevoNino = this.ninoVacio();
    this.mostrarFormulario = false;
    this.mensaje = '';
  }

  eliminarNino(nino: Nino): void {
    if (!nino.id) {
      return;
    }

    const confirmar = confirm(`Seguro que deseas eliminar a ${nino.nombre}?`);
    if (!confirmar) {
      return;
    }

    this.core.eliminarNino(nino.id).subscribe({
      next: () => this.cargarTodo(),
      error: () => {
        this.mensaje = 'No se pudo eliminar el registro';
      }
    });
  }

  guardarReserva(): void {
    this.guardando = true;
    this.core.crearReserva(this.nuevaReserva).subscribe({
      next: () => {
        this.guardando = false;
        this.nuevaReserva = this.reservaVacia();
        this.cargarTodo();
      },
      error: () => {
        this.guardando = false;
        this.mensaje = 'No se pudo guardar la reserva';
      }
    });
  }

  cerrarSesion(): void {
    this.sesion.salir();
    this.router.navigate(['/login']);
  }

  private ninoVacio(): Nino {
    return {
      nombre: '',
      edad: 6,
      representante: '',
      cedulaRepresentante: '',
      telefono: '',
      tipoEntradaId: 1,
      dineroVirtual: 0,
      puntosAcumulados: 0
    };
  }

  private reservaVacia(): Reserva {
    return {
      nombreNino: '',
      representante: '',
      telefono: '',
      tipoEntrada: 'Entrada normal',
      estado: 'pendiente',
      observaciones: ''
    };
  }
}

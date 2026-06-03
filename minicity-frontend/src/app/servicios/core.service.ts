import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Nino, Reserva, Resumen, TipoEntrada } from '../modelos/core';
import { obtenerApi } from '../config/api.config';

@Injectable({ providedIn: 'root' })
export class CoreService {
  private api = obtenerApi();

  constructor(private http: HttpClient) {}

  resumen(): Observable<Resumen> {
    return this.http.get<Resumen>(`${this.api}/api/panel/resumen`);
  }

  ninos(): Observable<Nino[]> {
    return this.http.get<Nino[]>(`${this.api}/api/ninos`);
  }

  tiposEntrada(): Observable<TipoEntrada[]> {
    return this.http.get<TipoEntrada[]>(`${this.api}/api/tipos-entrada`);
  }

  reservas(): Observable<Reserva[]> {
    return this.http.get<Reserva[]>(`${this.api}/api/reservas`);
  }

  crearNino(nino: Nino): Observable<Nino> {
    return this.http.post<Nino>(`${this.api}/api/ninos`, nino);
  }

  actualizarNino(id: number, nino: Nino): Observable<Nino> {
    return this.http.put<Nino>(`${this.api}/api/ninos/${id}`, nino);
  }

  eliminarNino(id: number): Observable<boolean> {
    return this.http.delete<boolean>(`${this.api}/api/ninos/${id}`);
  }

  crearReserva(reserva: Reserva): Observable<Reserva> {
    return this.http.post<Reserva>(`${this.api}/api/reservas`, reserva);
  }
}

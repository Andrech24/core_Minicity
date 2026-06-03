import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { obtenerApi } from '../config/api.config';

@Injectable({ providedIn: 'root' })
export class SesionService {
  private api = `${obtenerApi()}/api/auth`;

  constructor(private http: HttpClient) {}

  ingresar(email: string, password: string): Observable<{ success: boolean; mensaje: string; token: string }> {
    return this.http.post<{ success: boolean; mensaje: string; token: string }>(`${this.api}/login`, { email, password });
  }

  guardar(token: string): void {
    localStorage.setItem('minicity_sesion', token);
  }

  activa(): boolean {
    return !!localStorage.getItem('minicity_sesion');
  }

  salir(): void {
    localStorage.removeItem('minicity_sesion');
  }
}

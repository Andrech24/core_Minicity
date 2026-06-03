export interface TipoEntrada {
  id: number;
  nombre: string;
  dineroBase: number;
}

export interface Nino {
  id?: number;
  nombre: string;
  edad: number;
  representante: string;
  cedulaRepresentante: string;
  telefono: string;
  fechaIngreso?: string;
  tipoEntrada?: TipoEntrada;
  tipoEntradaId?: number;
  tipoEntradaNombre?: string;
  dineroVirtual: number;
  puntosAcumulados: number;
}

export interface Reserva {
  id?: number;
  ninoId?: number;
  nombreNino: string;
  representante: string;
  telefono: string;
  fechaReserva?: string;
  tipoEntrada: string;
  estado?: string;
  observaciones?: string;
}

export interface ComparacionTemporada {
  mesActual: string;
  mesAnterior: string;
  personasMesActual: number;
  personasMesAnterior: number;
  diferencia: number;
  porcentaje: number;
  estado: string;
  mensaje: string;
}

export interface Resumen {
  registros: number;
  monedas: number;
  cumpleanos: number;
  escolares: number;
  reservas: number;
  visitas: number;
  personasMesActual: number;
  personasMesAnterior: number;
  comparacionTemporada: ComparacionTemporada;
}

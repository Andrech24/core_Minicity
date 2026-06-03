package com.minicity.servicio;

import java.time.YearMonth;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.minicity.modelo.Nino;
import com.minicity.modelo.Reserva;
import com.minicity.modelo.Visita;
import com.minicity.repositorio.NinoRepositorio;
import com.minicity.repositorio.ReservaRepositorio;
import com.minicity.repositorio.VisitaRepositorio;

@Service
public class PanelServicio {
    private final NinoRepositorio ninoRepositorio;
    private final ReservaRepositorio reservaRepositorio;
    private final VisitaRepositorio visitaRepositorio;

    public PanelServicio(
        NinoRepositorio ninoRepositorio,
        ReservaRepositorio reservaRepositorio,
        VisitaRepositorio visitaRepositorio
    ) {
        this.ninoRepositorio = ninoRepositorio;
        this.reservaRepositorio = reservaRepositorio;
        this.visitaRepositorio = visitaRepositorio;
    }

    public Map<String, Object> resumen() {
        List<Nino> ninos = ninoRepositorio.findAllByOrderByIdAsc();
        List<Reserva> reservas = reservaRepositorio.findAll();
        List<Visita> visitas = visitaRepositorio.findAll();

        Map<String, Object> resumen = new LinkedHashMap<>();
        resumen.put("registros", ninos.size());
        resumen.put("monedas", ninos.stream().mapToInt(Nino::getDineroVirtual).sum());
        resumen.put("cumpleanos", contarReservas(reservas, "cumple"));
        resumen.put("escolares", contarVisitas(visitas, "escolar"));
        resumen.put("reservas", reservas.size());
        resumen.put("visitas", visitas.size());
        resumen.put("personasMesActual", contarVisitasMes(visitas, YearMonth.now()));
        resumen.put("personasMesAnterior", contarVisitasMes(visitas, YearMonth.now().minusMonths(1)));
        resumen.put("comparacionTemporada", compararTemporada(visitas));
        return resumen;
    }

    private long contarReservas(List<Reserva> reservas, String texto) {
        return reservas.stream()
            .filter(r -> r.getTipoEntrada() != null && r.getTipoEntrada().toLowerCase().contains(texto))
            .count();
    }

    private long contarVisitas(List<Visita> visitas, String texto) {
        return visitas.stream()
            .filter(v -> v.getTipoEntrada() != null && v.getTipoEntrada().toLowerCase().contains(texto))
            .count();
    }

    private long contarVisitasMes(List<Visita> visitas, YearMonth mes) {
        return visitas.stream()
            .filter(v -> v.getFechaVisita() != null)
            .filter(v -> YearMonth.from(v.getFechaVisita()).equals(mes))
            .count();
    }

    private Map<String, Object> compararTemporada(List<Visita> visitas) {
        YearMonth mesActual = YearMonth.now();
        YearMonth mesAnterior = mesActual.minusMonths(1);
        long actual = contarVisitasMes(visitas, mesActual);
        long anterior = contarVisitasMes(visitas, mesAnterior);
        long diferencia = actual - anterior;
        long porcentaje = anterior == 0 ? (actual > 0 ? 100 : 0) : Math.round((diferencia * 100.0) / anterior);

        String estado;
        String mensaje;
        if (diferencia > 0) {
            estado = "subio";
            mensaje = "La temporada subio frente al mes anterior.";
        } else if (diferencia < 0) {
            estado = "bajo";
            mensaje = "La temporada bajo frente al mes anterior.";
        } else {
            estado = "igual";
            mensaje = "La temporada se mantiene igual que el mes anterior.";
        }

        Map<String, Object> datos = new LinkedHashMap<>();
        datos.put("mesActual", mesActual.toString());
        datos.put("mesAnterior", mesAnterior.toString());
        datos.put("personasMesActual", actual);
        datos.put("personasMesAnterior", anterior);
        datos.put("diferencia", diferencia);
        datos.put("porcentaje", porcentaje);
        datos.put("estado", estado);
        datos.put("mensaje", mensaje);
        return datos;
    }
}

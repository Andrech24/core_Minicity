# Como probar MiniCity Core Angular + Spring Boot

## 1. Back-End

Abre una terminal en la carpeta principal y ejecuta:

```bash
mvnw.cmd spring-boot:run
```

El Back-End queda en:

```text
http://localhost:8081
```

## 2. Front-End

Abre otra terminal:

```bash
cd minicity-frontend
npm install
npm start
```

El Front-End queda en:

```text
http://localhost:4200
```

## 3. Usuario

```text
Correo: admin@minicity.com
Clave: admin123
```

## 4. Base de datos

El proyecto ya esta conectado a Neon en `application.properties`.

Cuando ingreses, el panel carga automaticamente:

- Registros de `ninos`.
- Monedas acumuladas.
- Puntos acumulados por cada nino.
- Reservas.
- Tipos de entrada.
- Resumen del core.
- Comparacion de temporada entre mes anterior y mes actual.
- Edicion y eliminacion de ninos.

La comparacion mensual usa la tabla `visitas` y muestra si la temporada subio, bajo o se mantuvo igual.

## 5. Verificado

Se verifico:

```bash
mvnw.cmd test
npm run build
```

Tambien se probo el API real contra Neon:

```text
GET http://localhost:8081/api/ninos
GET http://localhost:8081/api/panel/resumen
```

El API devolvio registros reales de Neon.

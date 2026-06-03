const API_LOCAL = 'http://localhost:8081';
const API_RENDER = 'https://minicity-backend-kevin.onrender.com';

export function obtenerApi(): string {
  return window.location.hostname === 'minicity-kevin.vercel.app' ? API_RENDER : API_LOCAL;
}

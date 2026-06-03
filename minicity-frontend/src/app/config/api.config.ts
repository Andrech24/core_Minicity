const API_LOCAL = 'http://localhost:8081';
const API_RENDER = 'https://minicity-backend-kevin.onrender.com';

export function obtenerApi(): string {
  const host = window.location.hostname;

  if (host === 'localhost' || host === '127.0.0.1') {
    return API_LOCAL;
  }

  return API_RENDER;
}
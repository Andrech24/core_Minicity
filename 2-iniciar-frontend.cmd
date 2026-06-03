@echo off
cd minicity-frontend
echo Instalando dependencias del Front-End si hace falta...
call npm install
echo Iniciando Front-End MiniCity en http://localhost:4200
call npm start

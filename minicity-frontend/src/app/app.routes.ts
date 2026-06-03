import { Routes } from '@angular/router';
import { guardiaSesion } from './guards/sesion.guard';
import { IngresoComponent } from './paginas/ingreso/ingreso.component';
import { PanelComponent } from './paginas/panel/panel.component';

export const routes: Routes = [
  { path: 'login', component: IngresoComponent },
  { path: 'panel', component: PanelComponent, canActivate: [guardiaSesion] },
  { path: '', redirectTo: 'panel', pathMatch: 'full' },
  { path: '**', redirectTo: 'panel' }
];

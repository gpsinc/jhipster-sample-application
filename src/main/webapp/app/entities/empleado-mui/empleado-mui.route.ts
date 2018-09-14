import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { EmpleadoMui } from 'app/shared/model/empleado-mui.model';
import { EmpleadoMuiService } from './empleado-mui.service';
import { EmpleadoMuiComponent } from './empleado-mui.component';
import { EmpleadoMuiDetailComponent } from './empleado-mui-detail.component';
import { EmpleadoMuiUpdateComponent } from './empleado-mui-update.component';
import { EmpleadoMuiDeletePopupComponent } from './empleado-mui-delete-dialog.component';
import { IEmpleadoMui } from 'app/shared/model/empleado-mui.model';

@Injectable({ providedIn: 'root' })
export class EmpleadoMuiResolve implements Resolve<IEmpleadoMui> {
    constructor(private service: EmpleadoMuiService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((empleado: HttpResponse<EmpleadoMui>) => empleado.body));
        }
        return of(new EmpleadoMui());
    }
}

export const empleadoRoute: Routes = [
    {
        path: 'empleado-mui',
        component: EmpleadoMuiComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Empleados'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'empleado-mui/:id/view',
        component: EmpleadoMuiDetailComponent,
        resolve: {
            empleado: EmpleadoMuiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Empleados'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'empleado-mui/new',
        component: EmpleadoMuiUpdateComponent,
        resolve: {
            empleado: EmpleadoMuiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Empleados'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'empleado-mui/:id/edit',
        component: EmpleadoMuiUpdateComponent,
        resolve: {
            empleado: EmpleadoMuiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Empleados'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const empleadoPopupRoute: Routes = [
    {
        path: 'empleado-mui/:id/delete',
        component: EmpleadoMuiDeletePopupComponent,
        resolve: {
            empleado: EmpleadoMuiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Empleados'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

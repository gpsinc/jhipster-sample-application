import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { DireccionMui } from 'app/shared/model/direccion-mui.model';
import { DireccionMuiService } from './direccion-mui.service';
import { DireccionMuiComponent } from './direccion-mui.component';
import { DireccionMuiDetailComponent } from './direccion-mui-detail.component';
import { DireccionMuiUpdateComponent } from './direccion-mui-update.component';
import { DireccionMuiDeletePopupComponent } from './direccion-mui-delete-dialog.component';
import { IDireccionMui } from 'app/shared/model/direccion-mui.model';

@Injectable({ providedIn: 'root' })
export class DireccionMuiResolve implements Resolve<IDireccionMui> {
    constructor(private service: DireccionMuiService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((direccion: HttpResponse<DireccionMui>) => direccion.body));
        }
        return of(new DireccionMui());
    }
}

export const direccionRoute: Routes = [
    {
        path: 'direccion-mui',
        component: DireccionMuiComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Direccions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'direccion-mui/:id/view',
        component: DireccionMuiDetailComponent,
        resolve: {
            direccion: DireccionMuiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Direccions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'direccion-mui/new',
        component: DireccionMuiUpdateComponent,
        resolve: {
            direccion: DireccionMuiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Direccions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'direccion-mui/:id/edit',
        component: DireccionMuiUpdateComponent,
        resolve: {
            direccion: DireccionMuiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Direccions'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const direccionPopupRoute: Routes = [
    {
        path: 'direccion-mui/:id/delete',
        component: DireccionMuiDeletePopupComponent,
        resolve: {
            direccion: DireccionMuiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Direccions'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

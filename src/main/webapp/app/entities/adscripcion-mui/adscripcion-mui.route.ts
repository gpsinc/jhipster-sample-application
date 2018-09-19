import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { AdscripcionMui } from 'app/shared/model/adscripcion-mui.model';
import { AdscripcionMuiService } from './adscripcion-mui.service';
import { AdscripcionMuiComponent } from './adscripcion-mui.component';
import { AdscripcionMuiDetailComponent } from './adscripcion-mui-detail.component';
import { AdscripcionMuiUpdateComponent } from './adscripcion-mui-update.component';
import { AdscripcionMuiDeletePopupComponent } from './adscripcion-mui-delete-dialog.component';
import { IAdscripcionMui } from 'app/shared/model/adscripcion-mui.model';

@Injectable({ providedIn: 'root' })
export class AdscripcionMuiResolve implements Resolve<IAdscripcionMui> {
    constructor(private service: AdscripcionMuiService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((adscripcion: HttpResponse<AdscripcionMui>) => adscripcion.body));
        }
        return of(new AdscripcionMui());
    }
}

export const adscripcionRoute: Routes = [
    {
        path: 'adscripcion-mui',
        component: AdscripcionMuiComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Adscripcions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'adscripcion-mui/:id/view',
        component: AdscripcionMuiDetailComponent,
        resolve: {
            adscripcion: AdscripcionMuiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Adscripcions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'adscripcion-mui/new',
        component: AdscripcionMuiUpdateComponent,
        resolve: {
            adscripcion: AdscripcionMuiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Adscripcions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'adscripcion-mui/:id/edit',
        component: AdscripcionMuiUpdateComponent,
        resolve: {
            adscripcion: AdscripcionMuiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Adscripcions'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const adscripcionPopupRoute: Routes = [
    {
        path: 'adscripcion-mui/:id/delete',
        component: AdscripcionMuiDeletePopupComponent,
        resolve: {
            adscripcion: AdscripcionMuiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Adscripcions'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

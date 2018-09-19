import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { AdscripcionResponsableMui } from 'app/shared/model/adscripcion-responsable-mui.model';
import { AdscripcionResponsableMuiService } from './adscripcion-responsable-mui.service';
import { AdscripcionResponsableMuiComponent } from './adscripcion-responsable-mui.component';
import { AdscripcionResponsableMuiDetailComponent } from './adscripcion-responsable-mui-detail.component';
import { AdscripcionResponsableMuiUpdateComponent } from './adscripcion-responsable-mui-update.component';
import { AdscripcionResponsableMuiDeletePopupComponent } from './adscripcion-responsable-mui-delete-dialog.component';
import { IAdscripcionResponsableMui } from 'app/shared/model/adscripcion-responsable-mui.model';

@Injectable({ providedIn: 'root' })
export class AdscripcionResponsableMuiResolve implements Resolve<IAdscripcionResponsableMui> {
    constructor(private service: AdscripcionResponsableMuiService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(map((adscripcionResponsable: HttpResponse<AdscripcionResponsableMui>) => adscripcionResponsable.body));
        }
        return of(new AdscripcionResponsableMui());
    }
}

export const adscripcionResponsableRoute: Routes = [
    {
        path: 'adscripcion-responsable-mui',
        component: AdscripcionResponsableMuiComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AdscripcionResponsables'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'adscripcion-responsable-mui/:id/view',
        component: AdscripcionResponsableMuiDetailComponent,
        resolve: {
            adscripcionResponsable: AdscripcionResponsableMuiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AdscripcionResponsables'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'adscripcion-responsable-mui/new',
        component: AdscripcionResponsableMuiUpdateComponent,
        resolve: {
            adscripcionResponsable: AdscripcionResponsableMuiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AdscripcionResponsables'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'adscripcion-responsable-mui/:id/edit',
        component: AdscripcionResponsableMuiUpdateComponent,
        resolve: {
            adscripcionResponsable: AdscripcionResponsableMuiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AdscripcionResponsables'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const adscripcionResponsablePopupRoute: Routes = [
    {
        path: 'adscripcion-responsable-mui/:id/delete',
        component: AdscripcionResponsableMuiDeletePopupComponent,
        resolve: {
            adscripcionResponsable: AdscripcionResponsableMuiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AdscripcionResponsables'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProveedorMui } from 'app/shared/model/proveedor-mui.model';
import { ProveedorMuiService } from './proveedor-mui.service';
import { ProveedorMuiComponent } from './proveedor-mui.component';
import { ProveedorMuiDetailComponent } from './proveedor-mui-detail.component';
import { ProveedorMuiUpdateComponent } from './proveedor-mui-update.component';
import { ProveedorMuiDeletePopupComponent } from './proveedor-mui-delete-dialog.component';
import { IProveedorMui } from 'app/shared/model/proveedor-mui.model';

@Injectable({ providedIn: 'root' })
export class ProveedorMuiResolve implements Resolve<IProveedorMui> {
    constructor(private service: ProveedorMuiService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((proveedor: HttpResponse<ProveedorMui>) => proveedor.body));
        }
        return of(new ProveedorMui());
    }
}

export const proveedorRoute: Routes = [
    {
        path: 'proveedor-mui',
        component: ProveedorMuiComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Proveedors'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'proveedor-mui/:id/view',
        component: ProveedorMuiDetailComponent,
        resolve: {
            proveedor: ProveedorMuiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Proveedors'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'proveedor-mui/new',
        component: ProveedorMuiUpdateComponent,
        resolve: {
            proveedor: ProveedorMuiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Proveedors'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'proveedor-mui/:id/edit',
        component: ProveedorMuiUpdateComponent,
        resolve: {
            proveedor: ProveedorMuiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Proveedors'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const proveedorPopupRoute: Routes = [
    {
        path: 'proveedor-mui/:id/delete',
        component: ProveedorMuiDeletePopupComponent,
        resolve: {
            proveedor: ProveedorMuiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Proveedors'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { DocumentoProveedorMui } from 'app/shared/model/documento-proveedor-mui.model';
import { DocumentoProveedorMuiService } from './documento-proveedor-mui.service';
import { DocumentoProveedorMuiComponent } from './documento-proveedor-mui.component';
import { DocumentoProveedorMuiDetailComponent } from './documento-proveedor-mui-detail.component';
import { DocumentoProveedorMuiUpdateComponent } from './documento-proveedor-mui-update.component';
import { DocumentoProveedorMuiDeletePopupComponent } from './documento-proveedor-mui-delete-dialog.component';
import { IDocumentoProveedorMui } from 'app/shared/model/documento-proveedor-mui.model';

@Injectable({ providedIn: 'root' })
export class DocumentoProveedorMuiResolve implements Resolve<IDocumentoProveedorMui> {
    constructor(private service: DocumentoProveedorMuiService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((documentoProveedor: HttpResponse<DocumentoProveedorMui>) => documentoProveedor.body));
        }
        return of(new DocumentoProveedorMui());
    }
}

export const documentoProveedorRoute: Routes = [
    {
        path: 'documento-proveedor-mui',
        component: DocumentoProveedorMuiComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DocumentoProveedors'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'documento-proveedor-mui/:id/view',
        component: DocumentoProveedorMuiDetailComponent,
        resolve: {
            documentoProveedor: DocumentoProveedorMuiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DocumentoProveedors'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'documento-proveedor-mui/new',
        component: DocumentoProveedorMuiUpdateComponent,
        resolve: {
            documentoProveedor: DocumentoProveedorMuiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DocumentoProveedors'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'documento-proveedor-mui/:id/edit',
        component: DocumentoProveedorMuiUpdateComponent,
        resolve: {
            documentoProveedor: DocumentoProveedorMuiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DocumentoProveedors'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const documentoProveedorPopupRoute: Routes = [
    {
        path: 'documento-proveedor-mui/:id/delete',
        component: DocumentoProveedorMuiDeletePopupComponent,
        resolve: {
            documentoProveedor: DocumentoProveedorMuiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DocumentoProveedors'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

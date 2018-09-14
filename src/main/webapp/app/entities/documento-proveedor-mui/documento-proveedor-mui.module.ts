import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared';
import {
    DocumentoProveedorMuiComponent,
    DocumentoProveedorMuiDetailComponent,
    DocumentoProveedorMuiUpdateComponent,
    DocumentoProveedorMuiDeletePopupComponent,
    DocumentoProveedorMuiDeleteDialogComponent,
    documentoProveedorRoute,
    documentoProveedorPopupRoute
} from './';

const ENTITY_STATES = [...documentoProveedorRoute, ...documentoProveedorPopupRoute];

@NgModule({
    imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DocumentoProveedorMuiComponent,
        DocumentoProveedorMuiDetailComponent,
        DocumentoProveedorMuiUpdateComponent,
        DocumentoProveedorMuiDeleteDialogComponent,
        DocumentoProveedorMuiDeletePopupComponent
    ],
    entryComponents: [
        DocumentoProveedorMuiComponent,
        DocumentoProveedorMuiUpdateComponent,
        DocumentoProveedorMuiDeleteDialogComponent,
        DocumentoProveedorMuiDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationDocumentoProveedorMuiModule {}

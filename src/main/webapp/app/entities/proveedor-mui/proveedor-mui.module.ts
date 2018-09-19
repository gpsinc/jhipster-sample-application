import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared';
import { JhipsterSampleApplicationAdminModule } from 'app/admin/admin.module';
import {
    ProveedorMuiComponent,
    ProveedorMuiDetailComponent,
    ProveedorMuiUpdateComponent,
    ProveedorMuiDeletePopupComponent,
    ProveedorMuiDeleteDialogComponent,
    proveedorRoute,
    proveedorPopupRoute
} from './';

const ENTITY_STATES = [...proveedorRoute, ...proveedorPopupRoute];

@NgModule({
    imports: [JhipsterSampleApplicationSharedModule, JhipsterSampleApplicationAdminModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ProveedorMuiComponent,
        ProveedorMuiDetailComponent,
        ProveedorMuiUpdateComponent,
        ProveedorMuiDeleteDialogComponent,
        ProveedorMuiDeletePopupComponent
    ],
    entryComponents: [
        ProveedorMuiComponent,
        ProveedorMuiUpdateComponent,
        ProveedorMuiDeleteDialogComponent,
        ProveedorMuiDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationProveedorMuiModule {}

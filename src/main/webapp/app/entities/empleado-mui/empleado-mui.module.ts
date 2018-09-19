import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared';
import { JhipsterSampleApplicationAdminModule } from 'app/admin/admin.module';
import {
    EmpleadoMuiComponent,
    EmpleadoMuiDetailComponent,
    EmpleadoMuiUpdateComponent,
    EmpleadoMuiDeletePopupComponent,
    EmpleadoMuiDeleteDialogComponent,
    empleadoRoute,
    empleadoPopupRoute
} from './';

const ENTITY_STATES = [...empleadoRoute, ...empleadoPopupRoute];

@NgModule({
    imports: [JhipsterSampleApplicationSharedModule, JhipsterSampleApplicationAdminModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EmpleadoMuiComponent,
        EmpleadoMuiDetailComponent,
        EmpleadoMuiUpdateComponent,
        EmpleadoMuiDeleteDialogComponent,
        EmpleadoMuiDeletePopupComponent
    ],
    entryComponents: [EmpleadoMuiComponent, EmpleadoMuiUpdateComponent, EmpleadoMuiDeleteDialogComponent, EmpleadoMuiDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationEmpleadoMuiModule {}

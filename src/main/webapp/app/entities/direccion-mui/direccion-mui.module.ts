import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared';
import {
    DireccionMuiComponent,
    DireccionMuiDetailComponent,
    DireccionMuiUpdateComponent,
    DireccionMuiDeletePopupComponent,
    DireccionMuiDeleteDialogComponent,
    direccionRoute,
    direccionPopupRoute
} from './';

const ENTITY_STATES = [...direccionRoute, ...direccionPopupRoute];

@NgModule({
    imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DireccionMuiComponent,
        DireccionMuiDetailComponent,
        DireccionMuiUpdateComponent,
        DireccionMuiDeleteDialogComponent,
        DireccionMuiDeletePopupComponent
    ],
    entryComponents: [
        DireccionMuiComponent,
        DireccionMuiUpdateComponent,
        DireccionMuiDeleteDialogComponent,
        DireccionMuiDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationDireccionMuiModule {}

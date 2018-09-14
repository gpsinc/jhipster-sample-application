import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared';
import {
    AdscripcionMuiComponent,
    AdscripcionMuiDetailComponent,
    AdscripcionMuiUpdateComponent,
    AdscripcionMuiDeletePopupComponent,
    AdscripcionMuiDeleteDialogComponent,
    adscripcionRoute,
    adscripcionPopupRoute
} from './';

const ENTITY_STATES = [...adscripcionRoute, ...adscripcionPopupRoute];

@NgModule({
    imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AdscripcionMuiComponent,
        AdscripcionMuiDetailComponent,
        AdscripcionMuiUpdateComponent,
        AdscripcionMuiDeleteDialogComponent,
        AdscripcionMuiDeletePopupComponent
    ],
    entryComponents: [
        AdscripcionMuiComponent,
        AdscripcionMuiUpdateComponent,
        AdscripcionMuiDeleteDialogComponent,
        AdscripcionMuiDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationAdscripcionMuiModule {}

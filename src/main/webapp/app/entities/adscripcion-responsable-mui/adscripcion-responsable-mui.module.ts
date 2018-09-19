import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared';
import {
    AdscripcionResponsableMuiComponent,
    AdscripcionResponsableMuiDetailComponent,
    AdscripcionResponsableMuiUpdateComponent,
    AdscripcionResponsableMuiDeletePopupComponent,
    AdscripcionResponsableMuiDeleteDialogComponent,
    adscripcionResponsableRoute,
    adscripcionResponsablePopupRoute
} from './';

const ENTITY_STATES = [...adscripcionResponsableRoute, ...adscripcionResponsablePopupRoute];

@NgModule({
    imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AdscripcionResponsableMuiComponent,
        AdscripcionResponsableMuiDetailComponent,
        AdscripcionResponsableMuiUpdateComponent,
        AdscripcionResponsableMuiDeleteDialogComponent,
        AdscripcionResponsableMuiDeletePopupComponent
    ],
    entryComponents: [
        AdscripcionResponsableMuiComponent,
        AdscripcionResponsableMuiUpdateComponent,
        AdscripcionResponsableMuiDeleteDialogComponent,
        AdscripcionResponsableMuiDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationAdscripcionResponsableMuiModule {}

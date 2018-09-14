import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared';
import {
    PersonaMuiComponent,
    PersonaMuiDetailComponent,
    PersonaMuiUpdateComponent,
    PersonaMuiDeletePopupComponent,
    PersonaMuiDeleteDialogComponent,
    personaRoute,
    personaPopupRoute
} from './';

const ENTITY_STATES = [...personaRoute, ...personaPopupRoute];

@NgModule({
    imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PersonaMuiComponent,
        PersonaMuiDetailComponent,
        PersonaMuiUpdateComponent,
        PersonaMuiDeleteDialogComponent,
        PersonaMuiDeletePopupComponent
    ],
    entryComponents: [PersonaMuiComponent, PersonaMuiUpdateComponent, PersonaMuiDeleteDialogComponent, PersonaMuiDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationPersonaMuiModule {}

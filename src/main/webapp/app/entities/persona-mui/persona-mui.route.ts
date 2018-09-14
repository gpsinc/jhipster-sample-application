import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { PersonaMui } from 'app/shared/model/persona-mui.model';
import { PersonaMuiService } from './persona-mui.service';
import { PersonaMuiComponent } from './persona-mui.component';
import { PersonaMuiDetailComponent } from './persona-mui-detail.component';
import { PersonaMuiUpdateComponent } from './persona-mui-update.component';
import { PersonaMuiDeletePopupComponent } from './persona-mui-delete-dialog.component';
import { IPersonaMui } from 'app/shared/model/persona-mui.model';

@Injectable({ providedIn: 'root' })
export class PersonaMuiResolve implements Resolve<IPersonaMui> {
    constructor(private service: PersonaMuiService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((persona: HttpResponse<PersonaMui>) => persona.body));
        }
        return of(new PersonaMui());
    }
}

export const personaRoute: Routes = [
    {
        path: 'persona-mui',
        component: PersonaMuiComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Personas'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'persona-mui/:id/view',
        component: PersonaMuiDetailComponent,
        resolve: {
            persona: PersonaMuiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Personas'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'persona-mui/new',
        component: PersonaMuiUpdateComponent,
        resolve: {
            persona: PersonaMuiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Personas'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'persona-mui/:id/edit',
        component: PersonaMuiUpdateComponent,
        resolve: {
            persona: PersonaMuiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Personas'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const personaPopupRoute: Routes = [
    {
        path: 'persona-mui/:id/delete',
        component: PersonaMuiDeletePopupComponent,
        resolve: {
            persona: PersonaMuiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Personas'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

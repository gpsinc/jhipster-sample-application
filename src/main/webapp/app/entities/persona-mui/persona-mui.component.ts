import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IPersonaMui } from 'app/shared/model/persona-mui.model';
import { Principal } from 'app/core';
import { PersonaMuiService } from './persona-mui.service';

@Component({
    selector: 'jhi-persona-mui',
    templateUrl: './persona-mui.component.html'
})
export class PersonaMuiComponent implements OnInit, OnDestroy {
    personas: IPersonaMui[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private personaService: PersonaMuiService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.personaService.query().subscribe(
            (res: HttpResponse<IPersonaMui[]>) => {
                this.personas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInPersonas();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IPersonaMui) {
        return item.id;
    }

    registerChangeInPersonas() {
        this.eventSubscriber = this.eventManager.subscribe('personaListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}

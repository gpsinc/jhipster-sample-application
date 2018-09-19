import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IAdscripcionResponsableMui } from 'app/shared/model/adscripcion-responsable-mui.model';
import { Principal } from 'app/core';
import { AdscripcionResponsableMuiService } from './adscripcion-responsable-mui.service';

@Component({
    selector: 'jhi-adscripcion-responsable-mui',
    templateUrl: './adscripcion-responsable-mui.component.html'
})
export class AdscripcionResponsableMuiComponent implements OnInit, OnDestroy {
    adscripcionResponsables: IAdscripcionResponsableMui[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private adscripcionResponsableService: AdscripcionResponsableMuiService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.adscripcionResponsableService.query().subscribe(
            (res: HttpResponse<IAdscripcionResponsableMui[]>) => {
                this.adscripcionResponsables = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInAdscripcionResponsables();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IAdscripcionResponsableMui) {
        return item.id;
    }

    registerChangeInAdscripcionResponsables() {
        this.eventSubscriber = this.eventManager.subscribe('adscripcionResponsableListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}

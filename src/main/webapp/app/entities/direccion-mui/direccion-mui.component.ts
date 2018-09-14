import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IDireccionMui } from 'app/shared/model/direccion-mui.model';
import { Principal } from 'app/core';
import { DireccionMuiService } from './direccion-mui.service';

@Component({
    selector: 'jhi-direccion-mui',
    templateUrl: './direccion-mui.component.html'
})
export class DireccionMuiComponent implements OnInit, OnDestroy {
    direccions: IDireccionMui[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private direccionService: DireccionMuiService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.direccionService.query().subscribe(
            (res: HttpResponse<IDireccionMui[]>) => {
                this.direccions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInDireccions();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IDireccionMui) {
        return item.id;
    }

    registerChangeInDireccions() {
        this.eventSubscriber = this.eventManager.subscribe('direccionListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}

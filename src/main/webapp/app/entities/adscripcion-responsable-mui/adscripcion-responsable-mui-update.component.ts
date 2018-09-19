import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IAdscripcionResponsableMui } from 'app/shared/model/adscripcion-responsable-mui.model';
import { AdscripcionResponsableMuiService } from './adscripcion-responsable-mui.service';
import { IProveedorMui } from 'app/shared/model/proveedor-mui.model';
import { ProveedorMuiService } from 'app/entities/proveedor-mui';

@Component({
    selector: 'jhi-adscripcion-responsable-mui-update',
    templateUrl: './adscripcion-responsable-mui-update.component.html'
})
export class AdscripcionResponsableMuiUpdateComponent implements OnInit {
    private _adscripcionResponsable: IAdscripcionResponsableMui;
    isSaving: boolean;

    proveedors: IProveedorMui[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private adscripcionResponsableService: AdscripcionResponsableMuiService,
        private proveedorService: ProveedorMuiService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ adscripcionResponsable }) => {
            this.adscripcionResponsable = adscripcionResponsable;
        });
        this.proveedorService.query().subscribe(
            (res: HttpResponse<IProveedorMui[]>) => {
                this.proveedors = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.adscripcionResponsable.id !== undefined) {
            this.subscribeToSaveResponse(this.adscripcionResponsableService.update(this.adscripcionResponsable));
        } else {
            this.subscribeToSaveResponse(this.adscripcionResponsableService.create(this.adscripcionResponsable));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAdscripcionResponsableMui>>) {
        result.subscribe(
            (res: HttpResponse<IAdscripcionResponsableMui>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackProveedorById(index: number, item: IProveedorMui) {
        return item.id;
    }
    get adscripcionResponsable() {
        return this._adscripcionResponsable;
    }

    set adscripcionResponsable(adscripcionResponsable: IAdscripcionResponsableMui) {
        this._adscripcionResponsable = adscripcionResponsable;
    }
}

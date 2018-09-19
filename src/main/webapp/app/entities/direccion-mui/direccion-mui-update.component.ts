import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IDireccionMui } from 'app/shared/model/direccion-mui.model';
import { DireccionMuiService } from './direccion-mui.service';

@Component({
    selector: 'jhi-direccion-mui-update',
    templateUrl: './direccion-mui-update.component.html'
})
export class DireccionMuiUpdateComponent implements OnInit {
    private _direccion: IDireccionMui;
    isSaving: boolean;

    constructor(private direccionService: DireccionMuiService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ direccion }) => {
            this.direccion = direccion;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.direccion.id !== undefined) {
            this.subscribeToSaveResponse(this.direccionService.update(this.direccion));
        } else {
            this.subscribeToSaveResponse(this.direccionService.create(this.direccion));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDireccionMui>>) {
        result.subscribe((res: HttpResponse<IDireccionMui>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get direccion() {
        return this._direccion;
    }

    set direccion(direccion: IDireccionMui) {
        this._direccion = direccion;
    }
}

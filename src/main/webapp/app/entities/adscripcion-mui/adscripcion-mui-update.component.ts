import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IAdscripcionMui } from 'app/shared/model/adscripcion-mui.model';
import { AdscripcionMuiService } from './adscripcion-mui.service';
import { IEmpleadoMui } from 'app/shared/model/empleado-mui.model';
import { EmpleadoMuiService } from 'app/entities/empleado-mui';
import { IProveedorMui } from 'app/shared/model/proveedor-mui.model';
import { ProveedorMuiService } from 'app/entities/proveedor-mui';
import { IAdscripcionResponsableMui } from 'app/shared/model/adscripcion-responsable-mui.model';
import { AdscripcionResponsableMuiService } from 'app/entities/adscripcion-responsable-mui';

@Component({
    selector: 'jhi-adscripcion-mui-update',
    templateUrl: './adscripcion-mui-update.component.html'
})
export class AdscripcionMuiUpdateComponent implements OnInit {
    private _adscripcion: IAdscripcionMui;
    isSaving: boolean;

    empleados: IEmpleadoMui[];

    proveedors: IProveedorMui[];

    adscripcionresponsables: IAdscripcionResponsableMui[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private adscripcionService: AdscripcionMuiService,
        private empleadoService: EmpleadoMuiService,
        private proveedorService: ProveedorMuiService,
        private adscripcionResponsableService: AdscripcionResponsableMuiService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ adscripcion }) => {
            this.adscripcion = adscripcion;
        });
        this.empleadoService.query().subscribe(
            (res: HttpResponse<IEmpleadoMui[]>) => {
                this.empleados = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.proveedorService.query().subscribe(
            (res: HttpResponse<IProveedorMui[]>) => {
                this.proveedors = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.adscripcionResponsableService.query().subscribe(
            (res: HttpResponse<IAdscripcionResponsableMui[]>) => {
                this.adscripcionresponsables = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.adscripcion.id !== undefined) {
            this.subscribeToSaveResponse(this.adscripcionService.update(this.adscripcion));
        } else {
            this.subscribeToSaveResponse(this.adscripcionService.create(this.adscripcion));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAdscripcionMui>>) {
        result.subscribe((res: HttpResponse<IAdscripcionMui>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackEmpleadoById(index: number, item: IEmpleadoMui) {
        return item.id;
    }

    trackProveedorById(index: number, item: IProveedorMui) {
        return item.id;
    }

    trackAdscripcionResponsableById(index: number, item: IAdscripcionResponsableMui) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
    get adscripcion() {
        return this._adscripcion;
    }

    set adscripcion(adscripcion: IAdscripcionMui) {
        this._adscripcion = adscripcion;
    }
}

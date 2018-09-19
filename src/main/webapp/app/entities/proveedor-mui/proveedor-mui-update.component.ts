import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IProveedorMui } from 'app/shared/model/proveedor-mui.model';
import { ProveedorMuiService } from './proveedor-mui.service';
import { IDireccionMui } from 'app/shared/model/direccion-mui.model';
import { DireccionMuiService } from 'app/entities/direccion-mui';
import { IPersonaMui } from 'app/shared/model/persona-mui.model';
import { PersonaMuiService } from 'app/entities/persona-mui';
import { IUser, UserService } from 'app/core';
import { IAdscripcionMui } from 'app/shared/model/adscripcion-mui.model';
import { AdscripcionMuiService } from 'app/entities/adscripcion-mui';

@Component({
    selector: 'jhi-proveedor-mui-update',
    templateUrl: './proveedor-mui-update.component.html'
})
export class ProveedorMuiUpdateComponent implements OnInit {
    private _proveedor: IProveedorMui;
    isSaving: boolean;

    direccions: IDireccionMui[];

    personas: IPersonaMui[];

    users: IUser[];

    adscripcions: IAdscripcionMui[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private proveedorService: ProveedorMuiService,
        private direccionService: DireccionMuiService,
        private personaService: PersonaMuiService,
        private userService: UserService,
        private adscripcionService: AdscripcionMuiService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ proveedor }) => {
            this.proveedor = proveedor;
        });
        this.direccionService.query({ filter: 'proveedor-is-null' }).subscribe(
            (res: HttpResponse<IDireccionMui[]>) => {
                if (!this.proveedor.direccionId) {
                    this.direccions = res.body;
                } else {
                    this.direccionService.find(this.proveedor.direccionId).subscribe(
                        (subRes: HttpResponse<IDireccionMui>) => {
                            this.direccions = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.personaService.query({ filter: 'proveedor-is-null' }).subscribe(
            (res: HttpResponse<IPersonaMui[]>) => {
                if (!this.proveedor.personaId) {
                    this.personas = res.body;
                } else {
                    this.personaService.find(this.proveedor.personaId).subscribe(
                        (subRes: HttpResponse<IPersonaMui>) => {
                            this.personas = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.userService.query().subscribe(
            (res: HttpResponse<IUser[]>) => {
                this.users = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.adscripcionService.query().subscribe(
            (res: HttpResponse<IAdscripcionMui[]>) => {
                this.adscripcions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.proveedor.id !== undefined) {
            this.subscribeToSaveResponse(this.proveedorService.update(this.proveedor));
        } else {
            this.subscribeToSaveResponse(this.proveedorService.create(this.proveedor));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IProveedorMui>>) {
        result.subscribe((res: HttpResponse<IProveedorMui>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackDireccionById(index: number, item: IDireccionMui) {
        return item.id;
    }

    trackPersonaById(index: number, item: IPersonaMui) {
        return item.id;
    }

    trackUserById(index: number, item: IUser) {
        return item.id;
    }

    trackAdscripcionById(index: number, item: IAdscripcionMui) {
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
    get proveedor() {
        return this._proveedor;
    }

    set proveedor(proveedor: IProveedorMui) {
        this._proveedor = proveedor;
    }
}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IEmpleadoMui } from 'app/shared/model/empleado-mui.model';
import { EmpleadoMuiService } from './empleado-mui.service';
import { IProveedorMui } from 'app/shared/model/proveedor-mui.model';
import { ProveedorMuiService } from 'app/entities/proveedor-mui';
import { IUser, UserService } from 'app/core';
import { IPersonaMui } from 'app/shared/model/persona-mui.model';
import { PersonaMuiService } from 'app/entities/persona-mui';
import { IAdscripcionMui } from 'app/shared/model/adscripcion-mui.model';
import { AdscripcionMuiService } from 'app/entities/adscripcion-mui';

@Component({
    selector: 'jhi-empleado-mui-update',
    templateUrl: './empleado-mui-update.component.html'
})
export class EmpleadoMuiUpdateComponent implements OnInit {
    private _empleado: IEmpleadoMui;
    isSaving: boolean;

    proveedors: IProveedorMui[];

    users: IUser[];

    personas: IPersonaMui[];

    adscripcions: IAdscripcionMui[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private empleadoService: EmpleadoMuiService,
        private proveedorService: ProveedorMuiService,
        private userService: UserService,
        private personaService: PersonaMuiService,
        private adscripcionService: AdscripcionMuiService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ empleado }) => {
            this.empleado = empleado;
        });
        this.proveedorService.query().subscribe(
            (res: HttpResponse<IProveedorMui[]>) => {
                this.proveedors = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.userService.query().subscribe(
            (res: HttpResponse<IUser[]>) => {
                this.users = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.personaService.query({ filter: 'empleado-is-null' }).subscribe(
            (res: HttpResponse<IPersonaMui[]>) => {
                if (!this.empleado.personaId) {
                    this.personas = res.body;
                } else {
                    this.personaService.find(this.empleado.personaId).subscribe(
                        (subRes: HttpResponse<IPersonaMui>) => {
                            this.personas = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
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
        if (this.empleado.id !== undefined) {
            this.subscribeToSaveResponse(this.empleadoService.update(this.empleado));
        } else {
            this.subscribeToSaveResponse(this.empleadoService.create(this.empleado));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEmpleadoMui>>) {
        result.subscribe((res: HttpResponse<IEmpleadoMui>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackUserById(index: number, item: IUser) {
        return item.id;
    }

    trackPersonaById(index: number, item: IPersonaMui) {
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
    get empleado() {
        return this._empleado;
    }

    set empleado(empleado: IEmpleadoMui) {
        this._empleado = empleado;
    }
}

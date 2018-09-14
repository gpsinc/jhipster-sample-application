import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEmpleadoMui } from 'app/shared/model/empleado-mui.model';

@Component({
    selector: 'jhi-empleado-mui-detail',
    templateUrl: './empleado-mui-detail.component.html'
})
export class EmpleadoMuiDetailComponent implements OnInit {
    empleado: IEmpleadoMui;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ empleado }) => {
            this.empleado = empleado;
        });
    }

    previousState() {
        window.history.back();
    }
}

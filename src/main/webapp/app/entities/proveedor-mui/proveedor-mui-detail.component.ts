import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProveedorMui } from 'app/shared/model/proveedor-mui.model';

@Component({
    selector: 'jhi-proveedor-mui-detail',
    templateUrl: './proveedor-mui-detail.component.html'
})
export class ProveedorMuiDetailComponent implements OnInit {
    proveedor: IProveedorMui;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ proveedor }) => {
            this.proveedor = proveedor;
        });
    }

    previousState() {
        window.history.back();
    }
}

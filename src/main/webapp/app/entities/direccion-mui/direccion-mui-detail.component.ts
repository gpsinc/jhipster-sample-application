import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDireccionMui } from 'app/shared/model/direccion-mui.model';

@Component({
    selector: 'jhi-direccion-mui-detail',
    templateUrl: './direccion-mui-detail.component.html'
})
export class DireccionMuiDetailComponent implements OnInit {
    direccion: IDireccionMui;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ direccion }) => {
            this.direccion = direccion;
        });
    }

    previousState() {
        window.history.back();
    }
}

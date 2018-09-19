import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAdscripcionMui } from 'app/shared/model/adscripcion-mui.model';

@Component({
    selector: 'jhi-adscripcion-mui-detail',
    templateUrl: './adscripcion-mui-detail.component.html'
})
export class AdscripcionMuiDetailComponent implements OnInit {
    adscripcion: IAdscripcionMui;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ adscripcion }) => {
            this.adscripcion = adscripcion;
        });
    }

    previousState() {
        window.history.back();
    }
}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAdscripcionResponsableMui } from 'app/shared/model/adscripcion-responsable-mui.model';

@Component({
    selector: 'jhi-adscripcion-responsable-mui-detail',
    templateUrl: './adscripcion-responsable-mui-detail.component.html'
})
export class AdscripcionResponsableMuiDetailComponent implements OnInit {
    adscripcionResponsable: IAdscripcionResponsableMui;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ adscripcionResponsable }) => {
            this.adscripcionResponsable = adscripcionResponsable;
        });
    }

    previousState() {
        window.history.back();
    }
}

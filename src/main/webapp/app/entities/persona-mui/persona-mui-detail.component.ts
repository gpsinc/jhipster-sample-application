import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPersonaMui } from 'app/shared/model/persona-mui.model';

@Component({
    selector: 'jhi-persona-mui-detail',
    templateUrl: './persona-mui-detail.component.html'
})
export class PersonaMuiDetailComponent implements OnInit {
    persona: IPersonaMui;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ persona }) => {
            this.persona = persona;
        });
    }

    previousState() {
        window.history.back();
    }
}

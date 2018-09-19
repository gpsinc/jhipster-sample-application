import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IPersonaMui } from 'app/shared/model/persona-mui.model';
import { PersonaMuiService } from './persona-mui.service';

@Component({
    selector: 'jhi-persona-mui-update',
    templateUrl: './persona-mui-update.component.html'
})
export class PersonaMuiUpdateComponent implements OnInit {
    private _persona: IPersonaMui;
    isSaving: boolean;

    constructor(private personaService: PersonaMuiService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ persona }) => {
            this.persona = persona;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.persona.id !== undefined) {
            this.subscribeToSaveResponse(this.personaService.update(this.persona));
        } else {
            this.subscribeToSaveResponse(this.personaService.create(this.persona));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPersonaMui>>) {
        result.subscribe((res: HttpResponse<IPersonaMui>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get persona() {
        return this._persona;
    }

    set persona(persona: IPersonaMui) {
        this._persona = persona;
    }
}

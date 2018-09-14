import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPersonaMui } from 'app/shared/model/persona-mui.model';
import { PersonaMuiService } from './persona-mui.service';

@Component({
    selector: 'jhi-persona-mui-delete-dialog',
    templateUrl: './persona-mui-delete-dialog.component.html'
})
export class PersonaMuiDeleteDialogComponent {
    persona: IPersonaMui;

    constructor(private personaService: PersonaMuiService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.personaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'personaListModification',
                content: 'Deleted an persona'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-persona-mui-delete-popup',
    template: ''
})
export class PersonaMuiDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ persona }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PersonaMuiDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.persona = persona;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
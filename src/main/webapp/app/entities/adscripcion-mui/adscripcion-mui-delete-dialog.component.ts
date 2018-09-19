import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAdscripcionMui } from 'app/shared/model/adscripcion-mui.model';
import { AdscripcionMuiService } from './adscripcion-mui.service';

@Component({
    selector: 'jhi-adscripcion-mui-delete-dialog',
    templateUrl: './adscripcion-mui-delete-dialog.component.html'
})
export class AdscripcionMuiDeleteDialogComponent {
    adscripcion: IAdscripcionMui;

    constructor(
        private adscripcionService: AdscripcionMuiService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.adscripcionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'adscripcionListModification',
                content: 'Deleted an adscripcion'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-adscripcion-mui-delete-popup',
    template: ''
})
export class AdscripcionMuiDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ adscripcion }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AdscripcionMuiDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.adscripcion = adscripcion;
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

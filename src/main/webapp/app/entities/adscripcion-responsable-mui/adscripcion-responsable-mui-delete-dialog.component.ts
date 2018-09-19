import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAdscripcionResponsableMui } from 'app/shared/model/adscripcion-responsable-mui.model';
import { AdscripcionResponsableMuiService } from './adscripcion-responsable-mui.service';

@Component({
    selector: 'jhi-adscripcion-responsable-mui-delete-dialog',
    templateUrl: './adscripcion-responsable-mui-delete-dialog.component.html'
})
export class AdscripcionResponsableMuiDeleteDialogComponent {
    adscripcionResponsable: IAdscripcionResponsableMui;

    constructor(
        private adscripcionResponsableService: AdscripcionResponsableMuiService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.adscripcionResponsableService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'adscripcionResponsableListModification',
                content: 'Deleted an adscripcionResponsable'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-adscripcion-responsable-mui-delete-popup',
    template: ''
})
export class AdscripcionResponsableMuiDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ adscripcionResponsable }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AdscripcionResponsableMuiDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.adscripcionResponsable = adscripcionResponsable;
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

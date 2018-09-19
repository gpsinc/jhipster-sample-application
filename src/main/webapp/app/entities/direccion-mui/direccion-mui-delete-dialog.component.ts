import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDireccionMui } from 'app/shared/model/direccion-mui.model';
import { DireccionMuiService } from './direccion-mui.service';

@Component({
    selector: 'jhi-direccion-mui-delete-dialog',
    templateUrl: './direccion-mui-delete-dialog.component.html'
})
export class DireccionMuiDeleteDialogComponent {
    direccion: IDireccionMui;

    constructor(private direccionService: DireccionMuiService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.direccionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'direccionListModification',
                content: 'Deleted an direccion'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-direccion-mui-delete-popup',
    template: ''
})
export class DireccionMuiDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ direccion }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DireccionMuiDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.direccion = direccion;
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

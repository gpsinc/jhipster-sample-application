import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProveedorMui } from 'app/shared/model/proveedor-mui.model';
import { ProveedorMuiService } from './proveedor-mui.service';

@Component({
    selector: 'jhi-proveedor-mui-delete-dialog',
    templateUrl: './proveedor-mui-delete-dialog.component.html'
})
export class ProveedorMuiDeleteDialogComponent {
    proveedor: IProveedorMui;

    constructor(private proveedorService: ProveedorMuiService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.proveedorService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'proveedorListModification',
                content: 'Deleted an proveedor'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-proveedor-mui-delete-popup',
    template: ''
})
export class ProveedorMuiDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ proveedor }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ProveedorMuiDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.proveedor = proveedor;
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

import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDocumentoProveedorMui } from 'app/shared/model/documento-proveedor-mui.model';
import { DocumentoProveedorMuiService } from './documento-proveedor-mui.service';

@Component({
    selector: 'jhi-documento-proveedor-mui-delete-dialog',
    templateUrl: './documento-proveedor-mui-delete-dialog.component.html'
})
export class DocumentoProveedorMuiDeleteDialogComponent {
    documentoProveedor: IDocumentoProveedorMui;

    constructor(
        private documentoProveedorService: DocumentoProveedorMuiService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.documentoProveedorService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'documentoProveedorListModification',
                content: 'Deleted an documentoProveedor'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-documento-proveedor-mui-delete-popup',
    template: ''
})
export class DocumentoProveedorMuiDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ documentoProveedor }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DocumentoProveedorMuiDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.documentoProveedor = documentoProveedor;
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

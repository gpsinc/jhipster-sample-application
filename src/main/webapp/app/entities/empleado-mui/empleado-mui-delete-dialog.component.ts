import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEmpleadoMui } from 'app/shared/model/empleado-mui.model';
import { EmpleadoMuiService } from './empleado-mui.service';

@Component({
    selector: 'jhi-empleado-mui-delete-dialog',
    templateUrl: './empleado-mui-delete-dialog.component.html'
})
export class EmpleadoMuiDeleteDialogComponent {
    empleado: IEmpleadoMui;

    constructor(private empleadoService: EmpleadoMuiService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.empleadoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'empleadoListModification',
                content: 'Deleted an empleado'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-empleado-mui-delete-popup',
    template: ''
})
export class EmpleadoMuiDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ empleado }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EmpleadoMuiDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.empleado = empleado;
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

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IDocumentoProveedorMui } from 'app/shared/model/documento-proveedor-mui.model';
import { DocumentoProveedorMuiService } from './documento-proveedor-mui.service';
import { IProveedorMui } from 'app/shared/model/proveedor-mui.model';
import { ProveedorMuiService } from 'app/entities/proveedor-mui';

@Component({
    selector: 'jhi-documento-proveedor-mui-update',
    templateUrl: './documento-proveedor-mui-update.component.html'
})
export class DocumentoProveedorMuiUpdateComponent implements OnInit {
    private _documentoProveedor: IDocumentoProveedorMui;
    isSaving: boolean;

    proveedors: IProveedorMui[];

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private documentoProveedorService: DocumentoProveedorMuiService,
        private proveedorService: ProveedorMuiService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ documentoProveedor }) => {
            this.documentoProveedor = documentoProveedor;
        });
        this.proveedorService.query().subscribe(
            (res: HttpResponse<IProveedorMui[]>) => {
                this.proveedors = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.documentoProveedor.id !== undefined) {
            this.subscribeToSaveResponse(this.documentoProveedorService.update(this.documentoProveedor));
        } else {
            this.subscribeToSaveResponse(this.documentoProveedorService.create(this.documentoProveedor));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDocumentoProveedorMui>>) {
        result.subscribe(
            (res: HttpResponse<IDocumentoProveedorMui>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackProveedorById(index: number, item: IProveedorMui) {
        return item.id;
    }
    get documentoProveedor() {
        return this._documentoProveedor;
    }

    set documentoProveedor(documentoProveedor: IDocumentoProveedorMui) {
        this._documentoProveedor = documentoProveedor;
    }
}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IDocumentoProveedorMui } from 'app/shared/model/documento-proveedor-mui.model';

@Component({
    selector: 'jhi-documento-proveedor-mui-detail',
    templateUrl: './documento-proveedor-mui-detail.component.html'
})
export class DocumentoProveedorMuiDetailComponent implements OnInit {
    documentoProveedor: IDocumentoProveedorMui;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ documentoProveedor }) => {
            this.documentoProveedor = documentoProveedor;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}

/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { DocumentoProveedorMuiComponent } from 'app/entities/documento-proveedor-mui/documento-proveedor-mui.component';
import { DocumentoProveedorMuiService } from 'app/entities/documento-proveedor-mui/documento-proveedor-mui.service';
import { DocumentoProveedorMui } from 'app/shared/model/documento-proveedor-mui.model';

describe('Component Tests', () => {
    describe('DocumentoProveedorMui Management Component', () => {
        let comp: DocumentoProveedorMuiComponent;
        let fixture: ComponentFixture<DocumentoProveedorMuiComponent>;
        let service: DocumentoProveedorMuiService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [DocumentoProveedorMuiComponent],
                providers: []
            })
                .overrideTemplate(DocumentoProveedorMuiComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DocumentoProveedorMuiComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DocumentoProveedorMuiService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new DocumentoProveedorMui(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.documentoProveedors[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});

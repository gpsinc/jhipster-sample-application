/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { DocumentoProveedorMuiDetailComponent } from 'app/entities/documento-proveedor-mui/documento-proveedor-mui-detail.component';
import { DocumentoProveedorMui } from 'app/shared/model/documento-proveedor-mui.model';

describe('Component Tests', () => {
    describe('DocumentoProveedorMui Management Detail Component', () => {
        let comp: DocumentoProveedorMuiDetailComponent;
        let fixture: ComponentFixture<DocumentoProveedorMuiDetailComponent>;
        const route = ({ data: of({ documentoProveedor: new DocumentoProveedorMui(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [DocumentoProveedorMuiDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DocumentoProveedorMuiDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DocumentoProveedorMuiDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.documentoProveedor).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});

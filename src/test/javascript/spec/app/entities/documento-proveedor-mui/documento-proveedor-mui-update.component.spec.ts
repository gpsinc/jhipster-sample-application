/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { DocumentoProveedorMuiUpdateComponent } from 'app/entities/documento-proveedor-mui/documento-proveedor-mui-update.component';
import { DocumentoProveedorMuiService } from 'app/entities/documento-proveedor-mui/documento-proveedor-mui.service';
import { DocumentoProveedorMui } from 'app/shared/model/documento-proveedor-mui.model';

describe('Component Tests', () => {
    describe('DocumentoProveedorMui Management Update Component', () => {
        let comp: DocumentoProveedorMuiUpdateComponent;
        let fixture: ComponentFixture<DocumentoProveedorMuiUpdateComponent>;
        let service: DocumentoProveedorMuiService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [DocumentoProveedorMuiUpdateComponent]
            })
                .overrideTemplate(DocumentoProveedorMuiUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DocumentoProveedorMuiUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DocumentoProveedorMuiService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new DocumentoProveedorMui(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.documentoProveedor = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new DocumentoProveedorMui();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.documentoProveedor = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});

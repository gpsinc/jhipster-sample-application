/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ProveedorMuiUpdateComponent } from 'app/entities/proveedor-mui/proveedor-mui-update.component';
import { ProveedorMuiService } from 'app/entities/proveedor-mui/proveedor-mui.service';
import { ProveedorMui } from 'app/shared/model/proveedor-mui.model';

describe('Component Tests', () => {
    describe('ProveedorMui Management Update Component', () => {
        let comp: ProveedorMuiUpdateComponent;
        let fixture: ComponentFixture<ProveedorMuiUpdateComponent>;
        let service: ProveedorMuiService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ProveedorMuiUpdateComponent]
            })
                .overrideTemplate(ProveedorMuiUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProveedorMuiUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProveedorMuiService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ProveedorMui(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.proveedor = entity;
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
                    const entity = new ProveedorMui();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.proveedor = entity;
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

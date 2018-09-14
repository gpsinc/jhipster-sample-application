/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { EmpleadoMuiUpdateComponent } from 'app/entities/empleado-mui/empleado-mui-update.component';
import { EmpleadoMuiService } from 'app/entities/empleado-mui/empleado-mui.service';
import { EmpleadoMui } from 'app/shared/model/empleado-mui.model';

describe('Component Tests', () => {
    describe('EmpleadoMui Management Update Component', () => {
        let comp: EmpleadoMuiUpdateComponent;
        let fixture: ComponentFixture<EmpleadoMuiUpdateComponent>;
        let service: EmpleadoMuiService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [EmpleadoMuiUpdateComponent]
            })
                .overrideTemplate(EmpleadoMuiUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EmpleadoMuiUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EmpleadoMuiService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EmpleadoMui(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.empleado = entity;
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
                    const entity = new EmpleadoMui();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.empleado = entity;
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

/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { DireccionMuiUpdateComponent } from 'app/entities/direccion-mui/direccion-mui-update.component';
import { DireccionMuiService } from 'app/entities/direccion-mui/direccion-mui.service';
import { DireccionMui } from 'app/shared/model/direccion-mui.model';

describe('Component Tests', () => {
    describe('DireccionMui Management Update Component', () => {
        let comp: DireccionMuiUpdateComponent;
        let fixture: ComponentFixture<DireccionMuiUpdateComponent>;
        let service: DireccionMuiService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [DireccionMuiUpdateComponent]
            })
                .overrideTemplate(DireccionMuiUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DireccionMuiUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DireccionMuiService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new DireccionMui(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.direccion = entity;
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
                    const entity = new DireccionMui();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.direccion = entity;
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

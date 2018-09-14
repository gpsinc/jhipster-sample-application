/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { AdscripcionMuiUpdateComponent } from 'app/entities/adscripcion-mui/adscripcion-mui-update.component';
import { AdscripcionMuiService } from 'app/entities/adscripcion-mui/adscripcion-mui.service';
import { AdscripcionMui } from 'app/shared/model/adscripcion-mui.model';

describe('Component Tests', () => {
    describe('AdscripcionMui Management Update Component', () => {
        let comp: AdscripcionMuiUpdateComponent;
        let fixture: ComponentFixture<AdscripcionMuiUpdateComponent>;
        let service: AdscripcionMuiService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [AdscripcionMuiUpdateComponent]
            })
                .overrideTemplate(AdscripcionMuiUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AdscripcionMuiUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AdscripcionMuiService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new AdscripcionMui(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.adscripcion = entity;
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
                    const entity = new AdscripcionMui();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.adscripcion = entity;
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

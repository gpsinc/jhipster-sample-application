/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { AdscripcionResponsableMuiUpdateComponent } from 'app/entities/adscripcion-responsable-mui/adscripcion-responsable-mui-update.component';
import { AdscripcionResponsableMuiService } from 'app/entities/adscripcion-responsable-mui/adscripcion-responsable-mui.service';
import { AdscripcionResponsableMui } from 'app/shared/model/adscripcion-responsable-mui.model';

describe('Component Tests', () => {
    describe('AdscripcionResponsableMui Management Update Component', () => {
        let comp: AdscripcionResponsableMuiUpdateComponent;
        let fixture: ComponentFixture<AdscripcionResponsableMuiUpdateComponent>;
        let service: AdscripcionResponsableMuiService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [AdscripcionResponsableMuiUpdateComponent]
            })
                .overrideTemplate(AdscripcionResponsableMuiUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AdscripcionResponsableMuiUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AdscripcionResponsableMuiService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new AdscripcionResponsableMui(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.adscripcionResponsable = entity;
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
                    const entity = new AdscripcionResponsableMui();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.adscripcionResponsable = entity;
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

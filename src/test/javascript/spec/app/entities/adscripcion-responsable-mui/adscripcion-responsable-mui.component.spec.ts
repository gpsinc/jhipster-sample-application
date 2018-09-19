/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { AdscripcionResponsableMuiComponent } from 'app/entities/adscripcion-responsable-mui/adscripcion-responsable-mui.component';
import { AdscripcionResponsableMuiService } from 'app/entities/adscripcion-responsable-mui/adscripcion-responsable-mui.service';
import { AdscripcionResponsableMui } from 'app/shared/model/adscripcion-responsable-mui.model';

describe('Component Tests', () => {
    describe('AdscripcionResponsableMui Management Component', () => {
        let comp: AdscripcionResponsableMuiComponent;
        let fixture: ComponentFixture<AdscripcionResponsableMuiComponent>;
        let service: AdscripcionResponsableMuiService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [AdscripcionResponsableMuiComponent],
                providers: []
            })
                .overrideTemplate(AdscripcionResponsableMuiComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AdscripcionResponsableMuiComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AdscripcionResponsableMuiService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new AdscripcionResponsableMui(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.adscripcionResponsables[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});

/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { AdscripcionMuiComponent } from 'app/entities/adscripcion-mui/adscripcion-mui.component';
import { AdscripcionMuiService } from 'app/entities/adscripcion-mui/adscripcion-mui.service';
import { AdscripcionMui } from 'app/shared/model/adscripcion-mui.model';

describe('Component Tests', () => {
    describe('AdscripcionMui Management Component', () => {
        let comp: AdscripcionMuiComponent;
        let fixture: ComponentFixture<AdscripcionMuiComponent>;
        let service: AdscripcionMuiService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [AdscripcionMuiComponent],
                providers: []
            })
                .overrideTemplate(AdscripcionMuiComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AdscripcionMuiComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AdscripcionMuiService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new AdscripcionMui(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.adscripcions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});

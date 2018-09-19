/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { DireccionMuiComponent } from 'app/entities/direccion-mui/direccion-mui.component';
import { DireccionMuiService } from 'app/entities/direccion-mui/direccion-mui.service';
import { DireccionMui } from 'app/shared/model/direccion-mui.model';

describe('Component Tests', () => {
    describe('DireccionMui Management Component', () => {
        let comp: DireccionMuiComponent;
        let fixture: ComponentFixture<DireccionMuiComponent>;
        let service: DireccionMuiService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [DireccionMuiComponent],
                providers: []
            })
                .overrideTemplate(DireccionMuiComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DireccionMuiComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DireccionMuiService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new DireccionMui(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.direccions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});

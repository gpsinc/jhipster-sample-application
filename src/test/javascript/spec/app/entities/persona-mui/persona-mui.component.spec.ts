/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { PersonaMuiComponent } from 'app/entities/persona-mui/persona-mui.component';
import { PersonaMuiService } from 'app/entities/persona-mui/persona-mui.service';
import { PersonaMui } from 'app/shared/model/persona-mui.model';

describe('Component Tests', () => {
    describe('PersonaMui Management Component', () => {
        let comp: PersonaMuiComponent;
        let fixture: ComponentFixture<PersonaMuiComponent>;
        let service: PersonaMuiService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [PersonaMuiComponent],
                providers: []
            })
                .overrideTemplate(PersonaMuiComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PersonaMuiComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PersonaMuiService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new PersonaMui(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.personas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});

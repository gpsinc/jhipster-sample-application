/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { PersonaMuiDetailComponent } from 'app/entities/persona-mui/persona-mui-detail.component';
import { PersonaMui } from 'app/shared/model/persona-mui.model';

describe('Component Tests', () => {
    describe('PersonaMui Management Detail Component', () => {
        let comp: PersonaMuiDetailComponent;
        let fixture: ComponentFixture<PersonaMuiDetailComponent>;
        const route = ({ data: of({ persona: new PersonaMui(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [PersonaMuiDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PersonaMuiDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PersonaMuiDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.persona).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});

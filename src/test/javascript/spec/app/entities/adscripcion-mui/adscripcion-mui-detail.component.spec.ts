/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { AdscripcionMuiDetailComponent } from 'app/entities/adscripcion-mui/adscripcion-mui-detail.component';
import { AdscripcionMui } from 'app/shared/model/adscripcion-mui.model';

describe('Component Tests', () => {
    describe('AdscripcionMui Management Detail Component', () => {
        let comp: AdscripcionMuiDetailComponent;
        let fixture: ComponentFixture<AdscripcionMuiDetailComponent>;
        const route = ({ data: of({ adscripcion: new AdscripcionMui(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [AdscripcionMuiDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AdscripcionMuiDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AdscripcionMuiDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.adscripcion).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});

/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { AdscripcionResponsableMuiDetailComponent } from 'app/entities/adscripcion-responsable-mui/adscripcion-responsable-mui-detail.component';
import { AdscripcionResponsableMui } from 'app/shared/model/adscripcion-responsable-mui.model';

describe('Component Tests', () => {
    describe('AdscripcionResponsableMui Management Detail Component', () => {
        let comp: AdscripcionResponsableMuiDetailComponent;
        let fixture: ComponentFixture<AdscripcionResponsableMuiDetailComponent>;
        const route = ({ data: of({ adscripcionResponsable: new AdscripcionResponsableMui(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [AdscripcionResponsableMuiDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AdscripcionResponsableMuiDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AdscripcionResponsableMuiDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.adscripcionResponsable).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});

/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { DireccionMuiDetailComponent } from 'app/entities/direccion-mui/direccion-mui-detail.component';
import { DireccionMui } from 'app/shared/model/direccion-mui.model';

describe('Component Tests', () => {
    describe('DireccionMui Management Detail Component', () => {
        let comp: DireccionMuiDetailComponent;
        let fixture: ComponentFixture<DireccionMuiDetailComponent>;
        const route = ({ data: of({ direccion: new DireccionMui(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [DireccionMuiDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DireccionMuiDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DireccionMuiDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.direccion).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});

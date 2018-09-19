/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { EmpleadoMuiDetailComponent } from 'app/entities/empleado-mui/empleado-mui-detail.component';
import { EmpleadoMui } from 'app/shared/model/empleado-mui.model';

describe('Component Tests', () => {
    describe('EmpleadoMui Management Detail Component', () => {
        let comp: EmpleadoMuiDetailComponent;
        let fixture: ComponentFixture<EmpleadoMuiDetailComponent>;
        const route = ({ data: of({ empleado: new EmpleadoMui(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [EmpleadoMuiDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EmpleadoMuiDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EmpleadoMuiDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.empleado).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});

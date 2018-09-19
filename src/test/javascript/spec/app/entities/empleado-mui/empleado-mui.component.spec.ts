/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { EmpleadoMuiComponent } from 'app/entities/empleado-mui/empleado-mui.component';
import { EmpleadoMuiService } from 'app/entities/empleado-mui/empleado-mui.service';
import { EmpleadoMui } from 'app/shared/model/empleado-mui.model';

describe('Component Tests', () => {
    describe('EmpleadoMui Management Component', () => {
        let comp: EmpleadoMuiComponent;
        let fixture: ComponentFixture<EmpleadoMuiComponent>;
        let service: EmpleadoMuiService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [EmpleadoMuiComponent],
                providers: []
            })
                .overrideTemplate(EmpleadoMuiComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EmpleadoMuiComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EmpleadoMuiService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new EmpleadoMui(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.empleados[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});

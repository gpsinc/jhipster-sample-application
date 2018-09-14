/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ProveedorMuiDetailComponent } from 'app/entities/proveedor-mui/proveedor-mui-detail.component';
import { ProveedorMui } from 'app/shared/model/proveedor-mui.model';

describe('Component Tests', () => {
    describe('ProveedorMui Management Detail Component', () => {
        let comp: ProveedorMuiDetailComponent;
        let fixture: ComponentFixture<ProveedorMuiDetailComponent>;
        const route = ({ data: of({ proveedor: new ProveedorMui(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ProveedorMuiDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ProveedorMuiDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProveedorMuiDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.proveedor).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});

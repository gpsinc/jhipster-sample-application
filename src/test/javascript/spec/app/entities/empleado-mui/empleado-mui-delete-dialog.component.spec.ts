/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { EmpleadoMuiDeleteDialogComponent } from 'app/entities/empleado-mui/empleado-mui-delete-dialog.component';
import { EmpleadoMuiService } from 'app/entities/empleado-mui/empleado-mui.service';

describe('Component Tests', () => {
    describe('EmpleadoMui Management Delete Component', () => {
        let comp: EmpleadoMuiDeleteDialogComponent;
        let fixture: ComponentFixture<EmpleadoMuiDeleteDialogComponent>;
        let service: EmpleadoMuiService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [EmpleadoMuiDeleteDialogComponent]
            })
                .overrideTemplate(EmpleadoMuiDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EmpleadoMuiDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EmpleadoMuiService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});

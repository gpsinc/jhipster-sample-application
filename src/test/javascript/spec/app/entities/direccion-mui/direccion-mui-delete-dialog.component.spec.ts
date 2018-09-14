/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { DireccionMuiDeleteDialogComponent } from 'app/entities/direccion-mui/direccion-mui-delete-dialog.component';
import { DireccionMuiService } from 'app/entities/direccion-mui/direccion-mui.service';

describe('Component Tests', () => {
    describe('DireccionMui Management Delete Component', () => {
        let comp: DireccionMuiDeleteDialogComponent;
        let fixture: ComponentFixture<DireccionMuiDeleteDialogComponent>;
        let service: DireccionMuiService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [DireccionMuiDeleteDialogComponent]
            })
                .overrideTemplate(DireccionMuiDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DireccionMuiDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DireccionMuiService);
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
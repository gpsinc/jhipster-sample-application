/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { AdscripcionResponsableMuiDeleteDialogComponent } from 'app/entities/adscripcion-responsable-mui/adscripcion-responsable-mui-delete-dialog.component';
import { AdscripcionResponsableMuiService } from 'app/entities/adscripcion-responsable-mui/adscripcion-responsable-mui.service';

describe('Component Tests', () => {
    describe('AdscripcionResponsableMui Management Delete Component', () => {
        let comp: AdscripcionResponsableMuiDeleteDialogComponent;
        let fixture: ComponentFixture<AdscripcionResponsableMuiDeleteDialogComponent>;
        let service: AdscripcionResponsableMuiService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [AdscripcionResponsableMuiDeleteDialogComponent]
            })
                .overrideTemplate(AdscripcionResponsableMuiDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AdscripcionResponsableMuiDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AdscripcionResponsableMuiService);
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

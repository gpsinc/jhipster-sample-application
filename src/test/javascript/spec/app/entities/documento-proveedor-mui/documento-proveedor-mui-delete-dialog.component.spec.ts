/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { DocumentoProveedorMuiDeleteDialogComponent } from 'app/entities/documento-proveedor-mui/documento-proveedor-mui-delete-dialog.component';
import { DocumentoProveedorMuiService } from 'app/entities/documento-proveedor-mui/documento-proveedor-mui.service';

describe('Component Tests', () => {
    describe('DocumentoProveedorMui Management Delete Component', () => {
        let comp: DocumentoProveedorMuiDeleteDialogComponent;
        let fixture: ComponentFixture<DocumentoProveedorMuiDeleteDialogComponent>;
        let service: DocumentoProveedorMuiService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [DocumentoProveedorMuiDeleteDialogComponent]
            })
                .overrideTemplate(DocumentoProveedorMuiDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DocumentoProveedorMuiDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DocumentoProveedorMuiService);
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

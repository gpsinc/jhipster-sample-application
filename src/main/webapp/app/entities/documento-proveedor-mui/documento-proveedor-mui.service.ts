import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDocumentoProveedorMui } from 'app/shared/model/documento-proveedor-mui.model';

type EntityResponseType = HttpResponse<IDocumentoProveedorMui>;
type EntityArrayResponseType = HttpResponse<IDocumentoProveedorMui[]>;

@Injectable({ providedIn: 'root' })
export class DocumentoProveedorMuiService {
    private resourceUrl = SERVER_API_URL + 'api/documento-proveedors';

    constructor(private http: HttpClient) {}

    create(documentoProveedor: IDocumentoProveedorMui): Observable<EntityResponseType> {
        return this.http.post<IDocumentoProveedorMui>(this.resourceUrl, documentoProveedor, { observe: 'response' });
    }

    update(documentoProveedor: IDocumentoProveedorMui): Observable<EntityResponseType> {
        return this.http.put<IDocumentoProveedorMui>(this.resourceUrl, documentoProveedor, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IDocumentoProveedorMui>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IDocumentoProveedorMui[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}

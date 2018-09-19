import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IProveedorMui } from 'app/shared/model/proveedor-mui.model';

type EntityResponseType = HttpResponse<IProveedorMui>;
type EntityArrayResponseType = HttpResponse<IProveedorMui[]>;

@Injectable({ providedIn: 'root' })
export class ProveedorMuiService {
    private resourceUrl = SERVER_API_URL + 'api/proveedors';

    constructor(private http: HttpClient) {}

    create(proveedor: IProveedorMui): Observable<EntityResponseType> {
        return this.http.post<IProveedorMui>(this.resourceUrl, proveedor, { observe: 'response' });
    }

    update(proveedor: IProveedorMui): Observable<EntityResponseType> {
        return this.http.put<IProveedorMui>(this.resourceUrl, proveedor, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IProveedorMui>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IProveedorMui[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}

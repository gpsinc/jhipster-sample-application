import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDireccionMui } from 'app/shared/model/direccion-mui.model';

type EntityResponseType = HttpResponse<IDireccionMui>;
type EntityArrayResponseType = HttpResponse<IDireccionMui[]>;

@Injectable({ providedIn: 'root' })
export class DireccionMuiService {
    private resourceUrl = SERVER_API_URL + 'api/direccions';

    constructor(private http: HttpClient) {}

    create(direccion: IDireccionMui): Observable<EntityResponseType> {
        return this.http.post<IDireccionMui>(this.resourceUrl, direccion, { observe: 'response' });
    }

    update(direccion: IDireccionMui): Observable<EntityResponseType> {
        return this.http.put<IDireccionMui>(this.resourceUrl, direccion, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IDireccionMui>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IDireccionMui[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}

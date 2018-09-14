import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAdscripcionMui } from 'app/shared/model/adscripcion-mui.model';

type EntityResponseType = HttpResponse<IAdscripcionMui>;
type EntityArrayResponseType = HttpResponse<IAdscripcionMui[]>;

@Injectable({ providedIn: 'root' })
export class AdscripcionMuiService {
    private resourceUrl = SERVER_API_URL + 'api/adscripcions';

    constructor(private http: HttpClient) {}

    create(adscripcion: IAdscripcionMui): Observable<EntityResponseType> {
        return this.http.post<IAdscripcionMui>(this.resourceUrl, adscripcion, { observe: 'response' });
    }

    update(adscripcion: IAdscripcionMui): Observable<EntityResponseType> {
        return this.http.put<IAdscripcionMui>(this.resourceUrl, adscripcion, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAdscripcionMui>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAdscripcionMui[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}

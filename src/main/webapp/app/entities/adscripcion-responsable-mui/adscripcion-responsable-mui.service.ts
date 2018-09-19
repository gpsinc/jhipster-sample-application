import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAdscripcionResponsableMui } from 'app/shared/model/adscripcion-responsable-mui.model';

type EntityResponseType = HttpResponse<IAdscripcionResponsableMui>;
type EntityArrayResponseType = HttpResponse<IAdscripcionResponsableMui[]>;

@Injectable({ providedIn: 'root' })
export class AdscripcionResponsableMuiService {
    private resourceUrl = SERVER_API_URL + 'api/adscripcion-responsables';

    constructor(private http: HttpClient) {}

    create(adscripcionResponsable: IAdscripcionResponsableMui): Observable<EntityResponseType> {
        return this.http.post<IAdscripcionResponsableMui>(this.resourceUrl, adscripcionResponsable, { observe: 'response' });
    }

    update(adscripcionResponsable: IAdscripcionResponsableMui): Observable<EntityResponseType> {
        return this.http.put<IAdscripcionResponsableMui>(this.resourceUrl, adscripcionResponsable, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAdscripcionResponsableMui>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAdscripcionResponsableMui[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}

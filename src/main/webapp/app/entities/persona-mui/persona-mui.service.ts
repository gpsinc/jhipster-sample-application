import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPersonaMui } from 'app/shared/model/persona-mui.model';

type EntityResponseType = HttpResponse<IPersonaMui>;
type EntityArrayResponseType = HttpResponse<IPersonaMui[]>;

@Injectable({ providedIn: 'root' })
export class PersonaMuiService {
    private resourceUrl = SERVER_API_URL + 'api/personas';

    constructor(private http: HttpClient) {}

    create(persona: IPersonaMui): Observable<EntityResponseType> {
        return this.http.post<IPersonaMui>(this.resourceUrl, persona, { observe: 'response' });
    }

    update(persona: IPersonaMui): Observable<EntityResponseType> {
        return this.http.put<IPersonaMui>(this.resourceUrl, persona, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPersonaMui>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPersonaMui[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}

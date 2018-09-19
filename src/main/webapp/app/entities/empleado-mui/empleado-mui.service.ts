import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEmpleadoMui } from 'app/shared/model/empleado-mui.model';

type EntityResponseType = HttpResponse<IEmpleadoMui>;
type EntityArrayResponseType = HttpResponse<IEmpleadoMui[]>;

@Injectable({ providedIn: 'root' })
export class EmpleadoMuiService {
    private resourceUrl = SERVER_API_URL + 'api/empleados';

    constructor(private http: HttpClient) {}

    create(empleado: IEmpleadoMui): Observable<EntityResponseType> {
        return this.http.post<IEmpleadoMui>(this.resourceUrl, empleado, { observe: 'response' });
    }

    update(empleado: IEmpleadoMui): Observable<EntityResponseType> {
        return this.http.put<IEmpleadoMui>(this.resourceUrl, empleado, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IEmpleadoMui>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEmpleadoMui[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}

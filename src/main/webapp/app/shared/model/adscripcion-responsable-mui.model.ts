import { IEmpleadoMui } from 'app/shared/model//empleado-mui.model';
import { IAdscripcionMui } from 'app/shared/model//adscripcion-mui.model';

export interface IAdscripcionResponsableMui {
    id?: number;
    proveedorId?: number;
    responsables?: IEmpleadoMui[];
    adscripcions?: IAdscripcionMui[];
}

export class AdscripcionResponsableMui implements IAdscripcionResponsableMui {
    constructor(
        public id?: number,
        public proveedorId?: number,
        public responsables?: IEmpleadoMui[],
        public adscripcions?: IAdscripcionMui[]
    ) {}
}

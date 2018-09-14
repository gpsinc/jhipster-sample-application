import { IAdscripcionMui } from 'app/shared/model//adscripcion-mui.model';

export interface IEmpleadoMui {
    id?: number;
    proveedorId?: number;
    usuarioId?: number;
    personaId?: number;
    adscripciones?: IAdscripcionMui[];
}

export class EmpleadoMui implements IEmpleadoMui {
    constructor(
        public id?: number,
        public proveedorId?: number,
        public usuarioId?: number,
        public personaId?: number,
        public adscripciones?: IAdscripcionMui[]
    ) {}
}

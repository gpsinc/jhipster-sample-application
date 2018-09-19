import { IAdscripcionMui } from 'app/shared/model//adscripcion-mui.model';

export interface IEmpleadoMui {
    id?: number;
    usuarioId?: number;
    personaId?: number;
    adscripciones?: IAdscripcionMui[];
    adscripcionResponsableId?: number;
}

export class EmpleadoMui implements IEmpleadoMui {
    constructor(
        public id?: number,
        public usuarioId?: number,
        public personaId?: number,
        public adscripciones?: IAdscripcionMui[],
        public adscripcionResponsableId?: number
    ) {}
}

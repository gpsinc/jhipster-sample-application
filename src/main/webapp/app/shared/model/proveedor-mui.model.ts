import { IDocumentoProveedorMui } from 'app/shared/model//documento-proveedor-mui.model';
import { IEmpleadoMui } from 'app/shared/model//empleado-mui.model';
import { IAdscripcionMui } from 'app/shared/model//adscripcion-mui.model';

export interface IProveedorMui {
    id?: number;
    direccionId?: number;
    personaId?: number;
    usuarioId?: number;
    documentos?: IDocumentoProveedorMui[];
    responsables?: IEmpleadoMui[];
    adscripcions?: IAdscripcionMui[];
    adscripciones?: IAdscripcionMui[];
}

export class ProveedorMui implements IProveedorMui {
    constructor(
        public id?: number,
        public direccionId?: number,
        public personaId?: number,
        public usuarioId?: number,
        public documentos?: IDocumentoProveedorMui[],
        public responsables?: IEmpleadoMui[],
        public adscripcions?: IAdscripcionMui[],
        public adscripciones?: IAdscripcionMui[]
    ) {}
}

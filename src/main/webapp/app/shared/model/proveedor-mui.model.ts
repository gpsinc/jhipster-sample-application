import { IDocumentoProveedorMui } from 'app/shared/model//documento-proveedor-mui.model';
import { IAdscripcionResponsableMui } from 'app/shared/model//adscripcion-responsable-mui.model';
import { IAdscripcionMui } from 'app/shared/model//adscripcion-mui.model';

export interface IProveedorMui {
    id?: number;
    direccionId?: number;
    personaId?: number;
    usuarioId?: number;
    documentos?: IDocumentoProveedorMui[];
    adscripciones?: IAdscripcionResponsableMui[];
    adscripciones?: IAdscripcionMui[];
}

export class ProveedorMui implements IProveedorMui {
    constructor(
        public id?: number,
        public direccionId?: number,
        public personaId?: number,
        public usuarioId?: number,
        public documentos?: IDocumentoProveedorMui[],
        public adscripciones?: IAdscripcionResponsableMui[],
        public adscripciones?: IAdscripcionMui[]
    ) {}
}

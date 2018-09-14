export interface IDireccionMui {
    id?: number;
    calle?: string;
    numero?: string;
    colonia?: string;
    cp?: string;
    municipio?: string;
    estado?: string;
}

export class DireccionMui implements IDireccionMui {
    constructor(
        public id?: number,
        public calle?: string,
        public numero?: string,
        public colonia?: string,
        public cp?: string,
        public municipio?: string,
        public estado?: string
    ) {}
}

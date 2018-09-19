export interface IDocumentoProveedorMui {
    id?: number;
    nombre?: string;
    extension?: string;
    tipo?: string;
    contenidoContentType?: string;
    contenido?: any;
    proveedorId?: number;
}

export class DocumentoProveedorMui implements IDocumentoProveedorMui {
    constructor(
        public id?: number,
        public nombre?: string,
        public extension?: string,
        public tipo?: string,
        public contenidoContentType?: string,
        public contenido?: any,
        public proveedorId?: number
    ) {}
}

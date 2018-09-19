import { IEmpleadoMui } from 'app/shared/model//empleado-mui.model';
import { IProveedorMui } from 'app/shared/model//proveedor-mui.model';

export interface IAdscripcionMui {
    id?: number;
    nombre?: string;
    clave?: string;
    empleados?: IEmpleadoMui[];
    proveedors?: IProveedorMui[];
    adscripcionResponsableId?: number;
}

export class AdscripcionMui implements IAdscripcionMui {
    constructor(
        public id?: number,
        public nombre?: string,
        public clave?: string,
        public empleados?: IEmpleadoMui[],
        public proveedors?: IProveedorMui[],
        public adscripcionResponsableId?: number
    ) {}
}

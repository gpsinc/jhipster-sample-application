import { IEmpleadoMui } from 'app/shared/model//empleado-mui.model';
import { IProveedorMui } from 'app/shared/model//proveedor-mui.model';

export interface IAdscripcionMui {
    id?: number;
    nombre?: string;
    clave?: string;
    proveedorId?: number;
    empleados?: IEmpleadoMui[];
    proveedors?: IProveedorMui[];
}

export class AdscripcionMui implements IAdscripcionMui {
    constructor(
        public id?: number,
        public nombre?: string,
        public clave?: string,
        public proveedorId?: number,
        public empleados?: IEmpleadoMui[],
        public proveedors?: IProveedorMui[]
    ) {}
}

export interface IPersonaMui {
    id?: number;
    nombre?: string;
    apellido1?: string;
    apellido2?: string;
    curp?: string;
    rfc?: string;
}

export class PersonaMui implements IPersonaMui {
    constructor(
        public id?: number,
        public nombre?: string,
        public apellido1?: string,
        public apellido2?: string,
        public curp?: string,
        public rfc?: string
    ) {}
}

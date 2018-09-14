import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { JhipsterSampleApplicationPersonaMuiModule } from './persona-mui/persona-mui.module';
import { JhipsterSampleApplicationDireccionMuiModule } from './direccion-mui/direccion-mui.module';
import { JhipsterSampleApplicationDocumentoProveedorMuiModule } from './documento-proveedor-mui/documento-proveedor-mui.module';
import { JhipsterSampleApplicationProveedorMuiModule } from './proveedor-mui/proveedor-mui.module';
import { JhipsterSampleApplicationAdscripcionMuiModule } from './adscripcion-mui/adscripcion-mui.module';
import { JhipsterSampleApplicationEmpleadoMuiModule } from './empleado-mui/empleado-mui.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        JhipsterSampleApplicationPersonaMuiModule,
        JhipsterSampleApplicationDireccionMuiModule,
        JhipsterSampleApplicationDocumentoProveedorMuiModule,
        JhipsterSampleApplicationProveedorMuiModule,
        JhipsterSampleApplicationAdscripcionMuiModule,
        JhipsterSampleApplicationEmpleadoMuiModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationEntityModule {}

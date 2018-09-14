package gps.mx.poc.start.jhipster.sample.service.mapper;

import gps.mx.poc.start.jhipster.sample.domain.*;
import gps.mx.poc.start.jhipster.sample.service.dto.DocumentoProveedorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DocumentoProveedor and its DTO DocumentoProveedorDTO.
 */
@Mapper(componentModel = "spring", uses = {ProveedorMapper.class})
public interface DocumentoProveedorMapper extends EntityMapper<DocumentoProveedorDTO, DocumentoProveedor> {

    @Mapping(source = "proveedor.id", target = "proveedorId")
    DocumentoProveedorDTO toDto(DocumentoProveedor documentoProveedor);

    @Mapping(source = "proveedorId", target = "proveedor")
    DocumentoProveedor toEntity(DocumentoProveedorDTO documentoProveedorDTO);

    default DocumentoProveedor fromId(Long id) {
        if (id == null) {
            return null;
        }
        DocumentoProveedor documentoProveedor = new DocumentoProveedor();
        documentoProveedor.setId(id);
        return documentoProveedor;
    }
}

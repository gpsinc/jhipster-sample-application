package gps.mx.poc.start.jhipster.sample.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the DocumentoProveedor entity.
 */
public class DocumentoProveedorDTO implements Serializable {

    private Long id;

    private String nombre;

    private String extension;

    private String tipo;

    @Lob
    private byte[] contenido;
    private String contenidoContentType;

    private Long proveedorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    public String getContenidoContentType() {
        return contenidoContentType;
    }

    public void setContenidoContentType(String contenidoContentType) {
        this.contenidoContentType = contenidoContentType;
    }

    public Long getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Long proveedorId) {
        this.proveedorId = proveedorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DocumentoProveedorDTO documentoProveedorDTO = (DocumentoProveedorDTO) o;
        if (documentoProveedorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), documentoProveedorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DocumentoProveedorDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", extension='" + getExtension() + "'" +
            ", tipo='" + getTipo() + "'" +
            ", contenido='" + getContenido() + "'" +
            ", proveedor=" + getProveedorId() +
            "}";
    }
}

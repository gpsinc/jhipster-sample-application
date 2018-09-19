package gps.mx.poc.start.jhipster.sample.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AdscripcionResponsable entity.
 */
public class AdscripcionResponsableDTO implements Serializable {

    private Long id;

    private Long proveedorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

        AdscripcionResponsableDTO adscripcionResponsableDTO = (AdscripcionResponsableDTO) o;
        if (adscripcionResponsableDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adscripcionResponsableDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdscripcionResponsableDTO{" +
            "id=" + getId() +
            ", proveedor=" + getProveedorId() +
            "}";
    }
}

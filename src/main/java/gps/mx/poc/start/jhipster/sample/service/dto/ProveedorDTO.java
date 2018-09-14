package gps.mx.poc.start.jhipster.sample.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Proveedor entity.
 */
public class ProveedorDTO implements Serializable {

    private Long id;

    private Long direccionId;

    private Long personaId;

    private Long usuarioId;

    private Set<AdscripcionDTO> adscripciones = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDireccionId() {
        return direccionId;
    }

    public void setDireccionId(Long direccionId) {
        this.direccionId = direccionId;
    }

    public Long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long userId) {
        this.usuarioId = userId;
    }

    public Set<AdscripcionDTO> getAdscripciones() {
        return adscripciones;
    }

    public void setAdscripciones(Set<AdscripcionDTO> adscripcions) {
        this.adscripciones = adscripcions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProveedorDTO proveedorDTO = (ProveedorDTO) o;
        if (proveedorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proveedorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProveedorDTO{" +
            "id=" + getId() +
            ", direccion=" + getDireccionId() +
            ", persona=" + getPersonaId() +
            ", usuario=" + getUsuarioId() +
            "}";
    }
}

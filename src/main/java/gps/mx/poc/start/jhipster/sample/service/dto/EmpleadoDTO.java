package gps.mx.poc.start.jhipster.sample.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Empleado entity.
 */
public class EmpleadoDTO implements Serializable {

    private Long id;

    private Long proveedorId;

    private Long usuarioId;

    private Long personaId;

    private Set<AdscripcionDTO> adscripciones = new HashSet<>();

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

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long userId) {
        this.usuarioId = userId;
    }

    public Long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
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

        EmpleadoDTO empleadoDTO = (EmpleadoDTO) o;
        if (empleadoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), empleadoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EmpleadoDTO{" +
            "id=" + getId() +
            ", proveedor=" + getProveedorId() +
            ", usuario=" + getUsuarioId() +
            ", persona=" + getPersonaId() +
            "}";
    }
}

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

    private Long usuarioId;

    private Long personaId;

    private Set<AdscripcionDTO> adscripciones = new HashSet<>();

    private Long adscripcionResponsableId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getAdscripcionResponsableId() {
        return adscripcionResponsableId;
    }

    public void setAdscripcionResponsableId(Long adscripcionResponsableId) {
        this.adscripcionResponsableId = adscripcionResponsableId;
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
            ", usuario=" + getUsuarioId() +
            ", persona=" + getPersonaId() +
            ", adscripcionResponsable=" + getAdscripcionResponsableId() +
            "}";
    }
}

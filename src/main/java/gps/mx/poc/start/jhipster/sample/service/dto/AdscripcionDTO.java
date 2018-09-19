package gps.mx.poc.start.jhipster.sample.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Adscripcion entity.
 */
public class AdscripcionDTO implements Serializable {

    private Long id;

    private String nombre;

    private String clave;

    private Set<EmpleadoDTO> empleados = new HashSet<>();

    private Set<ProveedorDTO> proveedors = new HashSet<>();

    private Long adscripcionResponsableId;

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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Set<EmpleadoDTO> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Set<EmpleadoDTO> empleados) {
        this.empleados = empleados;
    }

    public Set<ProveedorDTO> getProveedors() {
        return proveedors;
    }

    public void setProveedors(Set<ProveedorDTO> proveedors) {
        this.proveedors = proveedors;
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

        AdscripcionDTO adscripcionDTO = (AdscripcionDTO) o;
        if (adscripcionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adscripcionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdscripcionDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", clave='" + getClave() + "'" +
            ", adscripcionResponsable=" + getAdscripcionResponsableId() +
            "}";
    }
}

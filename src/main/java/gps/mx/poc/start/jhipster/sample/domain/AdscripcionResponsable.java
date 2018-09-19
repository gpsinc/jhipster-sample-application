package gps.mx.poc.start.jhipster.sample.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A AdscripcionResponsable.
 */
@Entity
@Table(name = "adscripcion_responsable")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AdscripcionResponsable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties("adscripciones")
    private Proveedor proveedor;

    @OneToMany(mappedBy = "adscripcionResponsable")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Empleado> responsables = new HashSet<>();

    @OneToMany(mappedBy = "adscripcionResponsable")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Adscripcion> adscripcions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public AdscripcionResponsable proveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
        return this;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Set<Empleado> getResponsables() {
        return responsables;
    }

    public AdscripcionResponsable responsables(Set<Empleado> empleados) {
        this.responsables = empleados;
        return this;
    }

    public AdscripcionResponsable addResponsable(Empleado empleado) {
        this.responsables.add(empleado);
        empleado.setAdscripcionResponsable(this);
        return this;
    }

    public AdscripcionResponsable removeResponsable(Empleado empleado) {
        this.responsables.remove(empleado);
        empleado.setAdscripcionResponsable(null);
        return this;
    }

    public void setResponsables(Set<Empleado> empleados) {
        this.responsables = empleados;
    }

    public Set<Adscripcion> getAdscripcions() {
        return adscripcions;
    }

    public AdscripcionResponsable adscripcions(Set<Adscripcion> adscripcions) {
        this.adscripcions = adscripcions;
        return this;
    }

    public AdscripcionResponsable addAdscripcion(Adscripcion adscripcion) {
        this.adscripcions.add(adscripcion);
        adscripcion.setAdscripcionResponsable(this);
        return this;
    }

    public AdscripcionResponsable removeAdscripcion(Adscripcion adscripcion) {
        this.adscripcions.remove(adscripcion);
        adscripcion.setAdscripcionResponsable(null);
        return this;
    }

    public void setAdscripcions(Set<Adscripcion> adscripcions) {
        this.adscripcions = adscripcions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AdscripcionResponsable adscripcionResponsable = (AdscripcionResponsable) o;
        if (adscripcionResponsable.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adscripcionResponsable.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdscripcionResponsable{" +
            "id=" + getId() +
            "}";
    }
}

package gps.mx.poc.start.jhipster.sample.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Adscripcion.
 */
@Entity
@Table(name = "adscripcion")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Adscripcion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "clave")
    private String clave;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "adscripcion_empleados",
               joinColumns = @JoinColumn(name = "adscripcions_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "empleados_id", referencedColumnName = "id"))
    private Set<Empleado> empleados = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "adscripcion_proveedor",
               joinColumns = @JoinColumn(name = "adscripcions_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "proveedors_id", referencedColumnName = "id"))
    private Set<Proveedor> proveedors = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("adscripcions")
    private AdscripcionResponsable adscripcionResponsable;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Adscripcion nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public Adscripcion clave(String clave) {
        this.clave = clave;
        return this;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Set<Empleado> getEmpleados() {
        return empleados;
    }

    public Adscripcion empleados(Set<Empleado> empleados) {
        this.empleados = empleados;
        return this;
    }

    public Adscripcion addEmpleados(Empleado empleado) {
        this.empleados.add(empleado);
        return this;
    }

    public Adscripcion removeEmpleados(Empleado empleado) {
        this.empleados.remove(empleado);
        return this;
    }

    public void setEmpleados(Set<Empleado> empleados) {
        this.empleados = empleados;
    }

    public Set<Proveedor> getProveedors() {
        return proveedors;
    }

    public Adscripcion proveedors(Set<Proveedor> proveedors) {
        this.proveedors = proveedors;
        return this;
    }

    public Adscripcion addProveedor(Proveedor proveedor) {
        this.proveedors.add(proveedor);
        return this;
    }

    public Adscripcion removeProveedor(Proveedor proveedor) {
        this.proveedors.remove(proveedor);
        return this;
    }

    public void setProveedors(Set<Proveedor> proveedors) {
        this.proveedors = proveedors;
    }

    public AdscripcionResponsable getAdscripcionResponsable() {
        return adscripcionResponsable;
    }

    public Adscripcion adscripcionResponsable(AdscripcionResponsable adscripcionResponsable) {
        this.adscripcionResponsable = adscripcionResponsable;
        return this;
    }

    public void setAdscripcionResponsable(AdscripcionResponsable adscripcionResponsable) {
        this.adscripcionResponsable = adscripcionResponsable;
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
        Adscripcion adscripcion = (Adscripcion) o;
        if (adscripcion.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adscripcion.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Adscripcion{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", clave='" + getClave() + "'" +
            "}";
    }
}

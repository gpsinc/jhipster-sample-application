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
 * A Empleado.
 */
@Entity
@Table(name = "empleado")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties("responsables")
    private Proveedor proveedor;

    @OneToOne
    @JoinColumn(unique = true)
    private User usuario;

    @OneToOne
    @JoinColumn(unique = true)
    private Persona persona;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "empleado_adscripciones",
               joinColumns = @JoinColumn(name = "empleados_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "adscripciones_id", referencedColumnName = "id"))
    private Set<Adscripcion> adscripciones = new HashSet<>();

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

    public Empleado proveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
        return this;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public User getUsuario() {
        return usuario;
    }

    public Empleado usuario(User user) {
        this.usuario = user;
        return this;
    }

    public void setUsuario(User user) {
        this.usuario = user;
    }

    public Persona getPersona() {
        return persona;
    }

    public Empleado persona(Persona persona) {
        this.persona = persona;
        return this;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Set<Adscripcion> getAdscripciones() {
        return adscripciones;
    }

    public Empleado adscripciones(Set<Adscripcion> adscripcions) {
        this.adscripciones = adscripcions;
        return this;
    }

    public Empleado addAdscripciones(Adscripcion adscripcion) {
        this.adscripciones.add(adscripcion);
        return this;
    }

    public Empleado removeAdscripciones(Adscripcion adscripcion) {
        this.adscripciones.remove(adscripcion);
        return this;
    }

    public void setAdscripciones(Set<Adscripcion> adscripcions) {
        this.adscripciones = adscripcions;
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
        Empleado empleado = (Empleado) o;
        if (empleado.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), empleado.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Empleado{" +
            "id=" + getId() +
            "}";
    }
}

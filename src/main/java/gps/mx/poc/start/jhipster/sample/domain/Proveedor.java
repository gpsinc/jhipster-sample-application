package gps.mx.poc.start.jhipster.sample.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Proveedor.
 */
@Entity
@Table(name = "proveedor")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Proveedor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(unique = true)
    private Direccion direccion;

    @OneToOne
    @JoinColumn(unique = true)
    private Persona persona;

    @OneToOne
    @JoinColumn(unique = true)
    private User usuario;

    @OneToMany(mappedBy = "proveedor")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DocumentoProveedor> documentos = new HashSet<>();

    @OneToMany(mappedBy = "proveedor")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AdscripcionResponsable> adscripciones = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "proveedor_adscripciones",
               joinColumns = @JoinColumn(name = "proveedors_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "adscripciones_id", referencedColumnName = "id"))
    private Set<Adscripcion> adscripciones = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public Proveedor direccion(Direccion direccion) {
        this.direccion = direccion;
        return this;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Persona getPersona() {
        return persona;
    }

    public Proveedor persona(Persona persona) {
        this.persona = persona;
        return this;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public User getUsuario() {
        return usuario;
    }

    public Proveedor usuario(User user) {
        this.usuario = user;
        return this;
    }

    public void setUsuario(User user) {
        this.usuario = user;
    }

    public Set<DocumentoProveedor> getDocumentos() {
        return documentos;
    }

    public Proveedor documentos(Set<DocumentoProveedor> documentoProveedors) {
        this.documentos = documentoProveedors;
        return this;
    }

    public Proveedor addDocumentos(DocumentoProveedor documentoProveedor) {
        this.documentos.add(documentoProveedor);
        documentoProveedor.setProveedor(this);
        return this;
    }

    public Proveedor removeDocumentos(DocumentoProveedor documentoProveedor) {
        this.documentos.remove(documentoProveedor);
        documentoProveedor.setProveedor(null);
        return this;
    }

    public void setDocumentos(Set<DocumentoProveedor> documentoProveedors) {
        this.documentos = documentoProveedors;
    }

    public Set<AdscripcionResponsable> getAdscripciones() {
        return adscripciones;
    }

    public Proveedor adscripciones(Set<AdscripcionResponsable> adscripcionResponsables) {
        this.adscripciones = adscripcionResponsables;
        return this;
    }

    public Proveedor addAdscripciones(AdscripcionResponsable adscripcionResponsable) {
        this.adscripciones.add(adscripcionResponsable);
        adscripcionResponsable.setProveedor(this);
        return this;
    }

    public Proveedor removeAdscripciones(AdscripcionResponsable adscripcionResponsable) {
        this.adscripciones.remove(adscripcionResponsable);
        adscripcionResponsable.setProveedor(null);
        return this;
    }

    public void setAdscripciones(Set<AdscripcionResponsable> adscripcionResponsables) {
        this.adscripciones = adscripcionResponsables;
    }

    public Set<Adscripcion> getAdscripciones() {
        return adscripciones;
    }

    public Proveedor adscripciones(Set<Adscripcion> adscripcions) {
        this.adscripciones = adscripcions;
        return this;
    }

    public Proveedor addAdscripciones(Adscripcion adscripcion) {
        this.adscripciones.add(adscripcion);
        return this;
    }

    public Proveedor removeAdscripciones(Adscripcion adscripcion) {
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
        Proveedor proveedor = (Proveedor) o;
        if (proveedor.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proveedor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Proveedor{" +
            "id=" + getId() +
            "}";
    }
}

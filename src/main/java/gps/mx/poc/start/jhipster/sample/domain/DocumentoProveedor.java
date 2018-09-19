package gps.mx.poc.start.jhipster.sample.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DocumentoProveedor.
 */
@Entity
@Table(name = "documento_proveedor")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DocumentoProveedor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "extension")
    private String extension;

    @Column(name = "tipo")
    private String tipo;

    @Lob
    @Column(name = "contenido")
    private byte[] contenido;

    @Column(name = "contenido_content_type")
    private String contenidoContentType;

    @ManyToOne
    @JsonIgnoreProperties("documentos")
    private Proveedor proveedor;

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

    public DocumentoProveedor nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getExtension() {
        return extension;
    }

    public DocumentoProveedor extension(String extension) {
        this.extension = extension;
        return this;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getTipo() {
        return tipo;
    }

    public DocumentoProveedor tipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public DocumentoProveedor contenido(byte[] contenido) {
        this.contenido = contenido;
        return this;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    public String getContenidoContentType() {
        return contenidoContentType;
    }

    public DocumentoProveedor contenidoContentType(String contenidoContentType) {
        this.contenidoContentType = contenidoContentType;
        return this;
    }

    public void setContenidoContentType(String contenidoContentType) {
        this.contenidoContentType = contenidoContentType;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public DocumentoProveedor proveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
        return this;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
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
        DocumentoProveedor documentoProveedor = (DocumentoProveedor) o;
        if (documentoProveedor.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), documentoProveedor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DocumentoProveedor{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", extension='" + getExtension() + "'" +
            ", tipo='" + getTipo() + "'" +
            ", contenido='" + getContenido() + "'" +
            ", contenidoContentType='" + getContenidoContentType() + "'" +
            "}";
    }
}

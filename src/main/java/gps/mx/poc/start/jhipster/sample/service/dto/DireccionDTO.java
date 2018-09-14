package gps.mx.poc.start.jhipster.sample.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Direccion entity.
 */
public class DireccionDTO implements Serializable {

    private Long id;

    private String calle;

    private String numero;

    private String colonia;

    private String cp;

    private String municipio;

    private String estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DireccionDTO direccionDTO = (DireccionDTO) o;
        if (direccionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), direccionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DireccionDTO{" +
            "id=" + getId() +
            ", calle='" + getCalle() + "'" +
            ", numero='" + getNumero() + "'" +
            ", colonia='" + getColonia() + "'" +
            ", cp='" + getCp() + "'" +
            ", municipio='" + getMunicipio() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}

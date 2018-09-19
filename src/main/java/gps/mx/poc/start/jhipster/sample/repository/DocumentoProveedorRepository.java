package gps.mx.poc.start.jhipster.sample.repository;

import gps.mx.poc.start.jhipster.sample.domain.DocumentoProveedor;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DocumentoProveedor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DocumentoProveedorRepository extends JpaRepository<DocumentoProveedor, Long> {

}

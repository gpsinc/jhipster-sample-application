package gps.mx.poc.start.jhipster.sample.repository;

import gps.mx.poc.start.jhipster.sample.domain.Proveedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Proveedor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    @Query(value = "select distinct proveedor from Proveedor proveedor left join fetch proveedor.adscripciones",
        countQuery = "select count(distinct proveedor) from Proveedor proveedor")
    Page<Proveedor> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct proveedor from Proveedor proveedor left join fetch proveedor.adscripciones")
    List<Proveedor> findAllWithEagerRelationships();

    @Query("select proveedor from Proveedor proveedor left join fetch proveedor.adscripciones where proveedor.id =:id")
    Optional<Proveedor> findOneWithEagerRelationships(@Param("id") Long id);

}

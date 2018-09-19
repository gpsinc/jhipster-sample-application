package gps.mx.poc.start.jhipster.sample.repository;

import gps.mx.poc.start.jhipster.sample.domain.Adscripcion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Adscripcion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdscripcionRepository extends JpaRepository<Adscripcion, Long> {

    @Query(value = "select distinct adscripcion from Adscripcion adscripcion left join fetch adscripcion.empleados left join fetch adscripcion.proveedors",
        countQuery = "select count(distinct adscripcion) from Adscripcion adscripcion")
    Page<Adscripcion> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct adscripcion from Adscripcion adscripcion left join fetch adscripcion.empleados left join fetch adscripcion.proveedors")
    List<Adscripcion> findAllWithEagerRelationships();

    @Query("select adscripcion from Adscripcion adscripcion left join fetch adscripcion.empleados left join fetch adscripcion.proveedors where adscripcion.id =:id")
    Optional<Adscripcion> findOneWithEagerRelationships(@Param("id") Long id);

}

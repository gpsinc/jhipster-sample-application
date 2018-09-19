package gps.mx.poc.start.jhipster.sample.repository;

import gps.mx.poc.start.jhipster.sample.domain.Empleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Empleado entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    @Query(value = "select distinct empleado from Empleado empleado left join fetch empleado.adscripciones",
        countQuery = "select count(distinct empleado) from Empleado empleado")
    Page<Empleado> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct empleado from Empleado empleado left join fetch empleado.adscripciones")
    List<Empleado> findAllWithEagerRelationships();

    @Query("select empleado from Empleado empleado left join fetch empleado.adscripciones where empleado.id =:id")
    Optional<Empleado> findOneWithEagerRelationships(@Param("id") Long id);

}

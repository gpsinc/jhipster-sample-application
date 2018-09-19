package gps.mx.poc.start.jhipster.sample.repository;

import gps.mx.poc.start.jhipster.sample.domain.AdscripcionResponsable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AdscripcionResponsable entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdscripcionResponsableRepository extends JpaRepository<AdscripcionResponsable, Long> {

}

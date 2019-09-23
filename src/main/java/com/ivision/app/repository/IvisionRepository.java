package com.ivision.app.repository;

import com.ivision.app.domain.Ivision;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Ivision entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IvisionRepository extends JpaRepository<Ivision, Long> {

}

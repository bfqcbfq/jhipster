package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Ivision;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Ivision entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IvisionRepository extends JpaRepository<Ivision, Long> {

}

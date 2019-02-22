package com.fairy.models.logic.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fairy.models.dto.jpa.FairyBaseRoute;

public interface BaseRouteModelJpa extends JpaRepository<FairyBaseRoute,Long> ,CrudRepository<FairyBaseRoute,Long> {
	
	@Query("from FairyBaseRoute where target=:target")
	Optional<FairyBaseRoute> findRouteByTarget(@Param("target") String target);
}

package com.fairy.models.logic.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fairy.models.dto.jpa.FairyGrantRoute;

public interface GrantRouteModelJpa extends JpaRepository<FairyGrantRoute,Long> ,CrudRepository<FairyGrantRoute,Long>{
	@Query("from FairyGrantRole where roleId = :roleId and routeId=:routeId")
	Optional<FairyGrantRoute> findGrantRoleByRouteId(@Param("routeId") Long routeId,@Param("roleId") Long roleId);
}

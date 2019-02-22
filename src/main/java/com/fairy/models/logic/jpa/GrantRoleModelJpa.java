package com.fairy.models.logic.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fairy.models.dto.jpa.FairyGrantRole;

public interface GrantRoleModelJpa extends JpaRepository<FairyGrantRole,Long> ,CrudRepository<FairyGrantRole,Long>  {
   @Query(value = "from FairyGrantRole where userId = :userId")
   List<FairyGrantRole> findByUserId(@Param("userId") Long userId);

}


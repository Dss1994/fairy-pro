package com.fairy.models.logic.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fairy.models.dto.jpa.FairyBaseSession;

public interface SessionModelJpa extends JpaRepository<FairyBaseSession,Long> ,CrudRepository<FairyBaseSession,Long> {
	@Modifying
	@Query("delete from FairyBaseSession where sessionCode=:sessionCode")
	void deleteBySessionCode(@Param("sessionCode") String sessionCode);
	@Query("from FairyBaseSession where sessionCode=:sessionCode")
	Optional<FairyBaseSession> findBySessionCode(@Param("sessionCode") String sessionCode);
	@Query("from FairyBaseSession where userId=:userId")
	Optional<FairyBaseSession> findByUserId(@Param("userId") Long userId);
}

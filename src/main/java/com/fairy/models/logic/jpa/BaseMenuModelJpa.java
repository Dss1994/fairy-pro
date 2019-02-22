package com.fairy.models.logic.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fairy.models.dto.jpa.FairyBaseMenu;

public interface BaseMenuModelJpa extends JpaRepository<FairyBaseMenu,Long> ,CrudRepository<FairyBaseMenu,Long>  {
	@Query("select m from FairyBaseMenu m "
			+ "left join FairyGrantMenu r "
			+ "on m.id = r.menuId "
			+ "where m.parentId=:parentId "
			+ "and r.roleId=:roleId order by m.compositor asc")
	List<FairyBaseMenu> findAccessibleMenu(@Param("parentId") Long parentId,@Param("roleId") Long roleId);
}

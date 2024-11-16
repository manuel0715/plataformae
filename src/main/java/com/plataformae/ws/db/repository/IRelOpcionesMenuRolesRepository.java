package com.plataformae.ws.db.repository;

import com.plataformae.ws.db.entity.MenuOpciones;
import com.plataformae.ws.db.entity.RelOpcionesMenuRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IRelOpcionesMenuRolesRepository extends JpaRepository<RelOpcionesMenuRoles, Integer> {
    @Query("SELECT r.menuOpciones FROM RelOpcionesMenuRoles r WHERE r.rol.id IN (:roleIds)")
    List<MenuOpciones> findMenuOpcionesByRoleIds(@Param("roleIds") List<Integer> roleIds);
}

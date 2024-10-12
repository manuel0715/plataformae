package com.plataformae.ws.service.impl;

import com.plataformae.ws.db.entity.MenuOpciones;
import com.plataformae.ws.db.entity.MenuPadre;
import com.plataformae.ws.db.repository.IMenuPadreRepository;
import com.plataformae.ws.db.repository.IRelOpcionesMenuRolesRepository;
import com.plataformae.ws.dto.MenuOpcionesDTO;
import com.plataformae.ws.dto.MenuPadreDTO;
import com.plataformae.ws.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RolServiceImpl implements IRolService {

    @Autowired
    private IRelOpcionesMenuRolesRepository opcionesRolesRepository;

    @Autowired
    private IMenuPadreRepository menuPadreRepository;


    @Override
    @Transactional
    public List<MenuPadreDTO> getMenuByRoles(List<Integer> roleIds) {

        List<MenuOpciones> opciones = opcionesRolesRepository.findMenuOpcionesByRoleIds(roleIds);

        List<MenuPadre> menusPadres = menuPadreRepository.findAll();
        List<MenuPadreDTO> menuDTOList = new ArrayList<>();
        for (MenuPadre padre : menusPadres) {
            List<MenuOpcionesDTO> opcionesDTO = opciones.stream()
                    .filter(opcion -> Objects.equals(opcion.getMenuPadre().getId(), padre.getId()))
                    .map(opcion -> new MenuOpcionesDTO(opcion.getId(), opcion.getNombreOpcion(), opcion.getPathOpcion(), opcion.getIcono(), opcion.getOrden()))
                    .toList();

            if (!opcionesDTO.isEmpty()) {
                menuDTOList.add(new MenuPadreDTO(padre.getId(), padre.getIcono(), padre.getOrden(), padre.getNombrePadre(), opcionesDTO));
            }
        }
        return menuDTOList;

    }
}

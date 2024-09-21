package com.plataformae.ws.service;

import com.plataformae.ws.dto.MenuPadreDTO;

import java.util.List;

public interface IRolService {

    public List<MenuPadreDTO> getMenuByRoles(List<Integer> roleIds);
}

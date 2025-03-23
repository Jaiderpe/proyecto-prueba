package com.proyectojr.electricalsupplies.domain.service;

import com.proyectojr.electricalsupplies.domain.model.Role;
import com.proyectojr.electricalsupplies.domain.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

// Contiene la lógica de negocio relacionada con roles.
public class RoleDomainService {
    private final RoleRepository roleRepository;

    public RoleDomainService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Obtiene todos los roles
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Busca un rol por su ID
    public Optional<Role> getRoleById(int roleId) {
        return roleRepository.findById(roleId);
    }

    // Busca un rol por su nombre
    public Optional<Role> getRoleByName(String roleName) {
        return roleRepository.findByName(roleName);
    }

    // Agrega un nuevo rol
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    // Actualiza un rol existente
    public void updateRole(Role role) {
        roleRepository.update(role);
    }

    // Elimina un rol por su ID
    public void deleteRole(int roleId) {
        roleRepository.delete(roleId);
    }
}
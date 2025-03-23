package com.proyectojr.electricalsupplies.application;

import com.proyectojr.electricalsupplies.domain.model.Role;
import com.proyectojr.electricalsupplies.domain.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(int idRole) {
        Optional<Role> role = roleRepository.findById(idRole);
        return role.orElse(null);
    }

    public void addRole(Role role) {
        roleRepository.save(role);
    }

    public void updateRole(Role role) {
        roleRepository.update(role);
    }

    public void deleteRole(int idRole) {
        roleRepository.delete(idRole);
    }
}
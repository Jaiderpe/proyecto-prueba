
package com.proyectojr.electricalsupplies.domain.repository;

import com.proyectojr.electricalsupplies.domain.model.Role;

import java.util.List;
import java.util.Optional;

// Define los métodos necesarios para acceder a los datos de roles.
public interface RoleRepository {
    List<Role> findAll();                      // Obtener todos los roles
    Optional<Role> findById(int roleId);       // Buscar un rol por ID
    Optional<Role> findByName(String roleName); // Buscar un rol por nombre
    void save(Role role);                      // Guardar un nuevo rol
    void update(Role role);                    // Actualizar un rol existente
    void delete(int roleId);                   // Eliminar un rol por ID
}
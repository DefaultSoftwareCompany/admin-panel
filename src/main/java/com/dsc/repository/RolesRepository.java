package com.dsc.repository;

import com.dsc.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
    public Roles getByRoleId(Short roleId);

    public Roles getByRole(String role);
}

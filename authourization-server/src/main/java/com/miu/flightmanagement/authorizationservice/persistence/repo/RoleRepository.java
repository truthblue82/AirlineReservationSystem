package com.miu.flightmanagement.authorizationservice.persistence.repo;

import com.miu.flightmanagement.authorizationservice.persistence.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

    @Override
    void delete(Role role);
}

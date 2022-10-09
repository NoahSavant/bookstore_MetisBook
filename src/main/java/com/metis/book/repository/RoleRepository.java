package com.metis.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metis.book.model.user.Role;
import com.metis.book.model.user.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(RoleName name);

}

package com.manish.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manish.blog.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}

package com.klef.ms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.ms.entity.Role;
import com.klef.ms.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> 
{
  // from the User where email=?1 and password=?2
  Optional<User> findByEmailAndPassword(String email, String password);

  Optional<User> findByEmail(String email);
  
  List<User> findByRole(Role role);
}

package com.klef.ms.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import com.klef.ms.entity.Role;
import com.klef.ms.entity.User;
import com.klef.ms.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest 
{
    @Autowired
    private UserRepository repository;

    @Test
    void testFindAllUsers() 
    {
        List<User> users = repository.findAll();
        assertNotNull(users);
        assertFalse(users.isEmpty());
        System.out.println("Total Users: " + users.size());
    }

    @Test
    void testFindUserById() 
    {
        Optional<User> user = repository.findById(14L);
        assertTrue(user.isPresent());
        assertEquals(14L, user.get().getId());
        System.out.println("User ID: " + user.get().getId());
        System.out.println("Email: " + user.get().getEmail());
    }

    @Test
    void testFindUserByEmail() 
    {
        Optional<User> user = repository.findByEmail("admin@gmail.com");
        assertTrue(user.isPresent());
        assertEquals("admin@gmail.com", user.get().getEmail());
        System.out.println("Email: " + user.get().getEmail());
        System.out.println("Role: " + user.get().getRole());
    }

    @Test
    void testFindUserByInvalidEmail() 
    {
        Optional<User> user = repository.findByEmail("notfound@gmail.com");
        assertTrue(user.isPresent());
    }

    @Test
    void testDatabaseConnection() 
    {
        long count = repository.count();
        assertTrue(count >= 0);
        System.out.println("Total Users in Database: " + count);
    }

    @Test
    void testUserDetails() 
    {
        Optional<User> user = repository.findById(14L);
        assertTrue(user.isPresent());
        User foundUser = user.get();
        assertNotNull(foundUser.getName());
        assertNotNull(foundUser.getEmail());
        assertNotNull(foundUser.getPassword());
        assertNotNull(foundUser.getContact());
        assertNotNull(foundUser.getRole());
        System.out.println("Name: " + foundUser.getName());
        System.out.println("Email: " + foundUser.getEmail());
        System.out.println("Role: " + foundUser.getRole());
    }

    @Test
    void testUserRole() 
    {
        Optional<User> user = repository.findById(14L);
        assertTrue(user.isPresent());
        assertEquals("MANAGER", user.get().getRole().name());
        System.out.println("Role: " + user.get().getRole().name());
    }

    @Test
    void testTotalUserCount() 
    {
        long count = repository.count();
        assertEquals(6L, count);
    }
    
    @Test
    void testUserRoles()
    {
    	List<User> users = repository.findByRole(Role.USER);
    	assertNotNull(users);
    	assertEquals(2,users.size());
    }
    
    
    
}

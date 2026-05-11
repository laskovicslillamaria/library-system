package com.nye.progkorny.library_system.service.service;

import com.nye.progkorny.library_system.model.User;
import com.nye.progkorny.library_system.repository.UserRepository;
import com.nye.progkorny.library_system.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    @Test
    void shouldReturnAllUsers() {
        User user = new User();
        user.setName("Test");

        when(repository.findAll()).thenReturn(List.of(user));

        List<User> result = service.getAllUsers();

        assertEquals(1, result.size());
        assertEquals("Test", result.get(0).getName());
    }
}

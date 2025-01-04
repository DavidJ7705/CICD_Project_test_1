package ie.atu.processauth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private MainClient mainClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void signUpTest() {
        String result = authService.signUp("Test Name", "testUser", "test@example.com", "password", 1);
        verify(personRepository, times(1)).save(any(Person.class));
        assertEquals("User signed up successfully!", result);
    }

    @Test
    void loginTest() {
        Person mockPerson = new Person("Test Name", "testUser", "test@example.com", "correctPassword", 1);
        when(personRepository.findByUserName("testUser")).thenReturn(Optional.of(mockPerson));

        String result = authService.login("testUser", "correctPassword");

        assertEquals("Login successful! Welcome, testUser! Name: Test Name, Email: test@example.com", result);
        assertEquals("testUser", authService.getSignedUsername());
        assertEquals("Test Name", authService.getSignedName());
        assertEquals("test@example.com", authService.getSignedEmail());
        assertEquals(1, authService.getCourseIdByUsername());
    }

    @Test
    void testInvalidUser() {
        when(personRepository.findByUserName("unknownUser")).thenReturn(Optional.empty());
        String result = authService.login("unknownUser", "anyPassword");
        assertEquals("User not found!", result);
    }

    @Test
    void testInvalidPassword() {
        Person mockPerson = new Person("Test Name", "testUser", "test@example.com", "correctPassword", 1);
        when(personRepository.findByUserName("testUser")).thenReturn(Optional.of(mockPerson));
        String result = authService.login("testUser", "wrongPassword");
        assertEquals("Invalid password!", result);
    }

    @Test
    void isModeratorTest() {
    }
}
```java
// src/main/java/com/example/demo/DemoApplication.java

package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

// src/main/java/com/example/demo/controller/AuthController.java

package com.example.demo.controller;

import com.example.demo.model.LoginRequest;
import com.example.demo.model.LoginResponse;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;

    /**
     * Endpoint for user login
     * @param loginRequest containing username and password
     * @return A response entity containing login response
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = authService.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }
}

// src/main/java/com/example/demo/model/LoginRequest.java

package com.example.demo.model;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    
    @NotBlank(message = "Username is mandatory")
    private String username;
    
    @NotBlank(message = "Password is mandatory")
    private String password;

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

// src/main/java/com/example/demo/model/LoginResponse.java

package com.example.demo.model;

public class LoginResponse {
    
    private boolean success;
    private String message;

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

// src/main/java/com/example/demo/service/AuthService.java

package com.example.demo.service;

import com.example.demo.model.LoginRequest;
import com.example.demo.model.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    /**
     * Service method to handle login request
     * @param loginRequest containing username and password
     * @return login response indicating success or failure
     */
    public LoginResponse login(LoginRequest loginRequest) {
        LoginResponse loginResponse = new LoginResponse();

        // Simulate authentication logic
        if ("user".equals(loginRequest.getUsername()) && "password".equals(loginRequest.getPassword())) {
            loginResponse.setSuccess(true);
            loginResponse.setMessage("Login successful");
        } else {
            loginResponse.setSuccess(false);
            loginResponse.setMessage("Invalid username or password");
        }

        return loginResponse;
    }
}
```
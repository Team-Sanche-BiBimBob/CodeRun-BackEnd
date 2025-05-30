package com.sanchae.coderun.auth;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/signup")
    public String signup() {
        return "u signed up";
    }

    @PostMapping("/signin")
    public String signin() {
        return "u signed in";
    }

    @PostMapping("/reissue")
    public String reissue() {
        return "u reissued";

    }
    @PostMapping("/signout")
    public String signout() {
        return "u signed out";
    }

    @DeleteMapping("/withdraw")
    public String withdraw() {
        return "withdraw";
    }

    @PatchMapping("/user/{id}")
    public String updateUser(@PathVariable("id") String id) {
        return "updateuser";
    }

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable("id") String id) {
        return "getuser";
    }
}

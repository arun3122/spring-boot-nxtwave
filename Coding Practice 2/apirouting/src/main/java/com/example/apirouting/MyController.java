/*
 * You can use the following import statements
 * 
 * import org.springframework.web.bind.annotation.GetMapping;
 * import org.springframework.web.bind.annotation.RestController;
 * 
 */

// Write your code here.
package com.example.apirouting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class MyController{
    
    @GetMapping("/")
    public String homePage() {
        return "Home Page";
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "About Page";
    }
}
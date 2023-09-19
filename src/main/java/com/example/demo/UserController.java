package com.example.demo;

import com.google.gson.Gson;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
 
    private List<User> allUsers;
        
    @GetMapping("getUsers")
    public List<User> findAllUsers() 
    {
        return allUsers;
    }
    @GetMapping("/{id}")
    public ResponseEntity <String> findUserById(@PathVariable(value = "id") long id) 
    {
        for (int i = 0; i < allUsers.size(); i++) 
        {
            if(allUsers.get(i).getId()==id)
            {
                Gson gson = new Gson();
                String jsonString = gson.toJson(allUsers.get(i));
                return ResponseEntity.ok().body(jsonString);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not Found !");
    }

    @PostMapping   
    public ResponseEntity<String> saveUser(@RequestBody User user) 
    {
        if(allUsers==null)
        {
            allUsers=new ArrayList<>();
        }
        allUsers.add(user);
        return ResponseEntity.ok().body("User saved");
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "id") long id)
    {
        for (int i = allUsers.size() - 1; i >= 0; i--) 
        {
            if (allUsers.get(i).getId()==id) 
            {
              allUsers.remove(i);
              return ResponseEntity.ok().body("User deleted !") ;
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not Found !");
    }

}
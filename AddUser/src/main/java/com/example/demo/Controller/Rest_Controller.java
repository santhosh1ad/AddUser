package com.example.demo.Controller;

import com.example.demo.Table.Users;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Rest_Controller {

	@Autowired
	JpaRepo repo;
	
    @GetMapping("/home")
    public String Home() {
        return "Welcome Home";
    }

    @GetMapping("/showUsers")
    public String Users() {
        return "";
    }

    @PostMapping(value = "/AddUser", consumes = "application/json")
    public String AddUser(@RequestBody Users obj) {
    	repo.save(obj);
    	
        return "User" + obj.getName() + "Age" + obj.getAge() + "added Successfully";
    }

    @PutMapping("/Update")
    public String UpdateUser(@PathVariable int id, @RequestBody Users name) {
    	 Users existingUser = repo.findById(id).orElse(null);

         if (existingUser != null) {
             existingUser.setName(name.getName());
             existingUser.setAge(name.getAge());
             repo.save(existingUser);
             return "User updated successfully";
         } else {
             return "User not found";
         }
    }

    @DeleteMapping("/User/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        Optional<Users> user = repo.findById(id);
        if (user.isPresent()) {
            repo.deleteById(id);
            return ResponseEntity.ok("User with ID " + id + " deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

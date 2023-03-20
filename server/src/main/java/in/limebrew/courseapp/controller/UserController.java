package in.limebrew.courseapp.controller;

import in.limebrew.courseapp.entity.User;
import in.limebrew.courseapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() throws ExecutionException, InterruptedException {
        System.out.println("All user info: ");
        userService.getAllUsers();
        return new ResponseEntity<>("All users", HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> createUser(@RequestBody User user) throws ExecutionException, InterruptedException {

        System.out.println("User requestbody: ");
        System.out.println(user);
        //? Add user in the firestore
        String updatedTime = userService.createUser(user);
        System.out.println("Updated time: " + updatedTime);
        return new ResponseEntity<>("Created User", HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") String id, @RequestBody User user) throws ExecutionException, InterruptedException {
        System.out.println("User requestbody: ");
        System.out.println(user);
        //? Update user in the firestore
        String updatedTime = userService.updateUser(id, user);
        System.out.println("Updated time: " + updatedTime);
        return new ResponseEntity<>("Updated User", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id) throws ExecutionException, InterruptedException {
        //? Delete user from the database
        String deletedTime = userService.deleteUser(id);
        System.out.println("Updated time: " + deletedTime);
        return new ResponseEntity<>("Updated User", HttpStatus.CREATED);
    }
}

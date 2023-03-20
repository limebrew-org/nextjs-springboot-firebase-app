package in.limebrew.courseapp.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import in.limebrew.courseapp.service.FirebaseService;
import in.limebrew.courseapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    FirebaseService firebaseService;

    @Autowired
    UserService userService;

    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> userEntity = new HashMap<>();

        System.out.println("Token: "+ token);
        try {
            FirebaseToken decodedToken = firebaseService.verifyToken(token);
            userService.getAllUsers();
            System.out.println("decoded token: "+decodedToken);
            System.out.println("User email: "+decodedToken.getEmail());
            System.out.println("User id: "+decodedToken.getUid());
            System.out.println("User name: "+decodedToken.getName());
            System.out.println("User email verified: "+decodedToken.isEmailVerified());

            userEntity.put("id",decodedToken.getUid());
            userEntity.put("name", decodedToken.getName());
            userEntity.put("email", decodedToken.getEmail());
            userEntity.put("status", "Authenticated");

            response.put("status", 200);
            response.put("message","JWT verified");
            response.put("data",userEntity);

            return new ResponseEntity<>(response, HttpStatus.OK);

//            // Token is valid, do something here
//            return ResponseEntity.ok("Authenticated");
        } catch (FirebaseAuthException | ExecutionException | InterruptedException e) {
            // Token is invalid or expired, return an error
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }
}

package in.limebrew.courseapp.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import in.limebrew.courseapp.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    FirebaseService firebaseService;

    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        System.out.println("Token: "+ token);
        try {
            FirebaseToken decodedToken = firebaseService.verifyToken(token);
            System.out.println("decoded token: "+decodedToken);
            System.out.println("User email: "+decodedToken.getEmail());
            System.out.println("User id: "+decodedToken.getUid());
            System.out.println("User name: "+decodedToken.getName());
            System.out.println("User email verified: "+decodedToken.isEmailVerified());

            // Token is valid, do something here
            return ResponseEntity.ok("Authenticated");
        } catch (FirebaseAuthException e) {
            // Token is invalid or expired, return an error
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }
}

package in.limebrew.courseapp.controller;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import in.limebrew.courseapp.entity.User;
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

    @PostMapping("/user")
    public ResponseEntity<?> verifyUser(@RequestHeader("Authorization") String authHeader){
        try {
            //? Extract the token
            String token = authHeader.substring(7);

            //? Verify JWT
            FirebaseToken decodedToken = firebaseService.verifyToken(token);

            //? Grab the payload
            User user = new User(
                    decodedToken.getUid(),
                    decodedToken.getName(),
                    decodedToken.getEmail(),
                    ""
            );

            //? Find user by userId in Firestore
            DocumentSnapshot userFoundEntity = userService.getUserById(user.getId());
            System.out.println("Get User By Id response: ");
            System.out.println(userFoundEntity.getData());


            //? If not then add user in firestore
//            userService.createUser(user);

            return new ResponseEntity<>("Authenticated", HttpStatus.OK);
        }
        catch (FirebaseAuthException | ExecutionException | InterruptedException e) {
            return new ResponseEntity<>("Unauthenticated!! Invalid token", HttpStatus.UNAUTHORIZED);
        }
    }

//    @PostMapping("/verify")
//    public ResponseEntity<?> verifyJWT(@RequestHeader("Authorization") String authHeader){
//        String token = authHeader.substring(7);
//        Map<String, Object> response = new HashMap<>();
//        Map<String, Object> userEntity = new HashMap<>();
//
//        System.out.println("Token: "+ token);
//        try {
//            FirebaseToken decodedToken = firebaseService.verifyToken(token);
//            userService.getAllUsers();
//            System.out.println("decoded token: "+decodedToken);
//            System.out.println("User email: "+decodedToken.getEmail());
//            System.out.println("User id: "+decodedToken.getUid());
//            System.out.println("User name: "+decodedToken.getName());
//            System.out.println("User email verified: "+decodedToken.isEmailVerified());
//
//            userEntity.put("id",decodedToken.getUid());
//            userEntity.put("name", decodedToken.getName());
//            userEntity.put("email", decodedToken.getEmail());
//            userEntity.put("status", "Authenticated");
//
//            response.put("status", 200);
//            response.put("message","JWT verified");
//            response.put("data",userEntity);
//
//            return new ResponseEntity<>(response, HttpStatus.OK);
//
////            // Token is valid, do something here
////            return ResponseEntity.ok("Authenticated");
//        } catch (FirebaseAuthException | ExecutionException | InterruptedException e) {
//            // Token is invalid or expired, return an error
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
//        }
//    }
}

//package com.cg.financesecurity.controller;
//
//
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.cg.financesecurity.Entity.User;
//import com.cg.financesecurity.Exception.UsernameNotFoundException;
//import com.cg.financesecurity.repository.UserRepo;
//
//@RestController
//public class UserController {
//
//    @Autowired
//    private UserRepo userRepo;
//
//    @GetMapping("/users")
//    public ResponseEntity<?> getLoggedInUser()
//    {
//    	Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
//    	if(authentication!= null && authentication.isAuthenticated())
//    	{
//    		String username= authentication.getName();
//    	
//    	User user = userRepo.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
//    	return ResponseEntity.ok(user);
//    	}
//    	return ResponseEntity.status(401).body("User not authenticated!");
//    	
//  }
////    @GetMapping("/users")
////    public ResponseEntity<?> getLoggedInUser() {
////        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////
////        if (authentication != null && authentication.isAuthenticated()) {
////            String username = authentication.getName();
////
////            User user = userRepo.findByEmail(username);
////            if (user == null) {
////                throw new UsernameNotFoundException("User not found with email: " + username);
////            }
////
////            return ResponseEntity.ok(user);
////        }
////        
////        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
////    }
//
//  
//    }

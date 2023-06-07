package com.example.demo.regusercontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.regUsersRepo.RegUserRepo;
import com.example.demo.regusersmodel.RegUser;

 

import java.nio.charset.StandardCharsets;
import java.util.*;
@RestController
@CrossOrigin("http://localhost:3000")
public class usercontroller {

    @Autowired
    private RegUserRepo repo;

    @PostMapping("/reguser")
    public RegUser postRegUser(@RequestBody RegUser reguser) {
        Base64.Encoder encoder= Base64.getEncoder();
        String encrytedstring=encoder.encodeToString(reguser.getPassword().getBytes(StandardCharsets.UTF_8));
        reguser.setPassword(encrytedstring);
        return repo.save(reguser);
    }

    @GetMapping("/getresponse/{email}/{pass}")
    public String getResponse(@PathVariable("email") String email , @PathVariable("pass") String pass) {
        List<RegUser> temp=repo.findByEmail(email); 
        if(temp.size()==0) return "wrong mail";
        Base64.Encoder encoder= Base64.getEncoder();
        String encrytedpass=encoder.encodeToString(pass.getBytes(StandardCharsets.UTF_8));
        List<RegUser> arr=repo.findByEmailAndPassword(email, encrytedpass);
        if(arr.size()==0) return "wrong pass";
        else return "ok";
    }

 

}

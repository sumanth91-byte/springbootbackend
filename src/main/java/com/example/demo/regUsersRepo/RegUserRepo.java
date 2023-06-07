package com.example.demo.regUsersRepo;





import java.util.List;

 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 

import com.example.demo.regusersmodel.RegUser;

 

@Repository
public interface RegUserRepo extends JpaRepository<RegUser, Long>{

    List<RegUser> findByEmailAndPassword(String email,String password);

    List<RegUser> findByEmail(String email);

 

}
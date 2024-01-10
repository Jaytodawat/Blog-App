package com.example.blogapp;

import com.example.blogapp.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogAppApplicationTests {

    @Autowired
    private UserRepository userRepository;



    @Test
    void contextLoads() {
    }

    @Test
    void repoTest(){
        String className = userRepository.getClass().getName();
        String packageName = userRepository.getClass().getPackageName();

        System.out.println(className);
        System.out.println(packageName);
    }

}

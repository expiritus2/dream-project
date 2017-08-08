package com.dreamproject;

import com.dreamproject.dao.RoleDao;
import com.dreamproject.entity.ImageObject;
import com.dreamproject.entity.TargetObject;
import com.dreamproject.entity.TypeObject;
import com.dreamproject.entity.User;
import com.dreamproject.entity.security.Role;
import com.dreamproject.entity.security.UserRole;
import com.dreamproject.service.ImageObjectService;
import com.dreamproject.service.TargetObjectService;
import com.dreamproject.service.TypeObjectService;
import com.dreamproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootApplication
public class DreamProjectApplication implements CommandLineRunner {

    @Autowired
    private RoleDao roleDao;


    @Autowired
    private UserService userService;

    @Autowired
    private TargetObjectService targetObjectService;

    @Autowired
    private TypeObjectService typeObjectService;

    @Autowired
    private ImageObjectService imageObjectService;


    public static void main(String[] args) {
        SpringApplication.run(DreamProjectApplication.class, args);
    }


    @Override
    public void run(String... strings) throws Exception {


        Role role1 = new Role();
        role1.setName("ROLE_ADMIN");
        roleDao.save(role1);

        Role role2 = new Role();
        role2.setName("ROLE_USER");
        roleDao.save(role2);

        User user1 = new User();
        user1.setFirstName("Michail");
        user1.setLastName("Drazdovich");
        user1.setUsername("mdrazdovich@blazemeter.com");
        user1.setPassword("210280");
        Set<UserRole> userRoles1 = new HashSet<>();
        userRoles1.add(new UserRole(user1, roleDao.findByName("ROLE_ADMIN")));
        userService.createUser(user1, userRoles1);

        User user2 = new User();
        user2.setFirstName("Galina");
        user2.setLastName("Drazdovich");
        user2.setUsername("galinka.minsk@gmail.com");
        user2.setPassword("210280");
        Set<UserRole> userRoles2 = new HashSet<>();
        userRoles2.add(new UserRole(user2, roleDao.findByName("ROLE_USER")));
        userService.createUser(user2, userRoles2);

        User user3 = new User();
        user3.setFirstName("Test");
        user3.setLastName("User");
        user3.setUsername("test.user@gmail.com");
        user3.setPassword("210280");
        Set<UserRole> userRoles3 = new HashSet<>();
        userRoles3.add(new UserRole(user3, roleDao.findByName("ROLE_USER")));
        userService.createUser(user3, userRoles3);

        TypeObject typeObject1 = new TypeObject("First Marker");
        typeObject1.setNameRu("Первый Маркер");
        typeObjectService.save(typeObject1);

        TypeObject typeObject2 = new TypeObject("Second Marker");
        typeObject2.setNameRu("Второй Маркер");
        typeObjectService.save(typeObject2);

        TypeObject typeObject3 = new TypeObject("Third Marker");
        typeObject3.setNameRu("Третий Маркер");
        typeObjectService.save(typeObject3);

        TypeObject typeObject4 = new TypeObject("Fourth Marker");
        typeObject4.setNameRu("Четвертый Маркер");
        typeObjectService.save(typeObject4);


        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
        cal.setTime(sdf.parse("2017-08-09 23:01"));
        TargetObject targetObject1 = new TargetObject(typeObject1, 53.844627797824955, 27.627525329589844, "comment1", user2, cal);
        targetObjectService.save(targetObject1);
        ImageObject imageObject1 = new ImageObject("file1OfOne.txt", targetObject1);
        imageObjectService.save(imageObject1);
        ImageObject imageObject2 = new ImageObject("file2OfOne.txt", targetObject1);
        imageObjectService.save(imageObject2);
        ImageObject imageObject3 = new ImageObject("file3OfOne.txt", targetObject1);
        imageObjectService.save(imageObject3);


        TargetObject targetObject2 = new TargetObject(typeObject2, 53.861638751487305, 27.6361083984375, "comment2", user1, cal);
        targetObjectService.save(targetObject2);
        ImageObject imageObject4 = new ImageObject("file1OfTwo.txt", targetObject2);
        imageObjectService.save(imageObject4);
        ImageObject imageObject5 = new ImageObject("file2OfTwo.txt", targetObject2);
        imageObjectService.save(imageObject5);
        ImageObject imageObject6 = new ImageObject("file3OfTwo.txt", targetObject2);
        imageObjectService.save(imageObject6);


        TargetObject targetObject3 = new TargetObject(typeObject3, 53.8577916408477, 27.660484313964844, "comment3", user3, cal);
        targetObjectService.save(targetObject3);
        ImageObject imageObject10 = new ImageObject("file1OfThree.txt", targetObject3);
        imageObjectService.save(imageObject10);
        ImageObject imageObject11 = new ImageObject("file2OfThree.txt", targetObject3);
        imageObjectService.save(imageObject11);
        ImageObject imageObject12 = new ImageObject("file3OfThree.txt", targetObject3);
        imageObjectService.save(imageObject12);


        TargetObject targetObject4 = new TargetObject(typeObject4, 53.84705835738316, 27.691383361816406, "comment4", user3, cal);
        targetObjectService.save(targetObject4);
        ImageObject imageObject13 = new ImageObject("file1OfFour.txt", targetObject4);
        imageObjectService.save(imageObject13);
        ImageObject imageObject14 = new ImageObject("file2OfFour.txt", targetObject4);
        imageObjectService.save(imageObject14);
        ImageObject imageObject15 = new ImageObject("file3OfFour.txt", targetObject4);
        imageObjectService.save(imageObject15);
    }
}

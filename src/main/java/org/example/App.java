package org.example;

import org.example.config.MyConfig;
import org.example.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        System.out.println( "Hello World!" );

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = context.getBean("communication", Communication.class);
        List<User> allUsers = communication.getUsers();

        System.out.println(allUsers);

//        User tmpUser = new User(3L, "James", "Brown", (byte) 3);
//        System.out.println(tmpUser);

        System.out.println(communication.addUser(new User(3L, "James", "Brown", (byte) 23)) +
                    communication.editUser(new User(3L, "Thomas", "Shelby", (byte) 23)) +
                    communication.deleteUser(new User(3L, "Thomas", "Shelby", (byte) 23),3L));
    }
}

package org.example;

import jakarta.transaction.Transactional;
import org.example.conf.JavaConfig;
import org.example.dto.GoodDTO;
import org.example.dto.OrderDTO;
import org.example.dto.UserDTO;
import org.example.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);
        OrderService orderService = applicationContext.getBean(OrderServiceImpl.class);
        UserService userService = applicationContext.getBean(UserServiceImpl.class);
        GoodService goodService = applicationContext.getBean(GoodServiceImpl.class);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter command: ");
            String command = scanner.nextLine();
            processCommand(command, orderService, userService, goodService);
        }

    }
    private static void processCommand(String command, OrderService orderService, UserService userService, GoodService goodService) {
        String[] parts = command.split(":");
        if (parts.length < 2) {
            System.out.println("Invalid command format");
            return;
        }
        String commandName = parts[0];
        String[] parameters = parts[1].split(",");

        switch (commandName) {
            case "ADD_USER":
                if (parameters.length != 4) {
                    System.out.println("Invalid parameters for ADD_USER command");
                    break;
                }
                String firstName = parameters[0];
                String lastName = parameters[1];
                String birthday = parameters[2];
                String email = parameters[3];
                userService.addNewUser(UserDTO.builder()
                                .firstName(firstName)
                                .lastName(lastName)
                                .birthday(birthday)
                                .email(email)
                        .build());
                break;
            case "VIEW_USERS":
                List<String> usersList = new ArrayList<>(userService.viewUsers());
                for(String user : usersList){
                    System.out.println(user);
                }
                break;
            case "ADD_GOOD":
                if (parameters.length != 1) {
                    System.out.println("Invalid parameters for ADD_USER command");
                    break;
                }
                String goodName = parameters[0];
                goodService.addNewGood(GoodDTO.builder()
                        .goodName(goodName)
                        .build());
                break;
            case "VIEW_GOODS":
                List<String> goodList = new ArrayList<>(goodService.viewGoods());
                for(String good : goodList){
                    System.out.println(good);
                }
                break;
            case "ADD_ORDER":
                if (parameters.length != 3) {
                    System.out.println("Invalid parameters for ADD_USER command");
                    break;
                }
                int userId = Integer.parseInt(parameters[0]);
                int goodId = Integer.parseInt(parameters[1]);
                String createDate = parameters[2];
                orderService.addNewOrder(OrderDTO.builder()
                                .userId(userId)
                                .goodId(goodId)
                                .createDate(createDate)
                        .build());
                break;
            case "VIEW_ORDERS":
                List<String> ordersList = new ArrayList<>(orderService.viewOrders());
                for(String order : ordersList){
                    System.out.println(order);
                }
                break;
            default:
                System.out.println("Unknown command");
        }
    }

}
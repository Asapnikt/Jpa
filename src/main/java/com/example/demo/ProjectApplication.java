package com.example.demo;

import com.example.demo.entity.*;
import com.example.demo.service.*;
import net.bytebuddy.utility.RandomString;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@SpringBootApplication
public class ProjectApplication {

    private static AddressService addressService;
    private static EventsService eventsService;
    private static OrderService orderService;
    private static UserService userService;
    private static ServicesService service;

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(ProjectApplication.class, args);

        addressService = applicationContext.getBean(AddressService.class);
        eventsService = applicationContext.getBean(EventsService.class);
        orderService = applicationContext.getBean(OrderService.class);
        userService = applicationContext.getBean(UserService.class);
        service = applicationContext.getBean(ServicesService.class);
//            generateDataForEvent();
//        generateAddress();
//        generateServiceInfo();
//          generateUsersInfo();
//        generateOrderInfo();
//        compareLazyVsEager();
//        getDataByGroup();
//        getPaginationData();
    }

    private void oneToOneTableOperation() {
        Events events = Events.builder()
                .description("Праздник, который символизирует начала года")
                .name("Новый год")
                .finishDate(new Calendar.Builder().setDate(2022, 0, 1).build())
                .startDate(new Calendar.Builder().setDate(2021, 11, 31).build())
                .build();

        //save
        eventsService.saveEvents(events);
        //edit
        events.getFinishDate().set(Calendar.YEAR, 2023);
        eventsService.editEvents(events);
        //get
        eventsService.getAllEvents();
        //delete
        eventsService.deleteEvents(events);
    }

    private static void compareLazyVsEager() {
        //Lazy vs Eager
        var startTime = System.currentTimeMillis();
        orderService.getAllEvents();
        var endTime = System.currentTimeMillis();
        System.out.println("execution time: " + (endTime - startTime));
    }

    private static void getDataByGroup() {
//        var group = orderService.getAllEvents().stream()
//                .collect(Collectors.groupingBy(order -> order.getUser().getName(), Collectors.counting()));
//
                orderService.getValueByGrouping();
    }

    private static void getPaginationData() {
        Pageable pageParam = PageRequest.of(1, 5);
        var info = addressService.getAddressWithPagination(pageParam);
        info.get().forEach(address -> System.out.println(address.getCity() + ' ' + address.getCountry() + ' ' + address.getHouseNumber()));
    }

    private static void generateDataForEvent() {
        int i = 0;
        LocalDate start = LocalDate.of(1989, Month.OCTOBER, 14);
        LocalDate end = LocalDate.now();
        while (i != 15) {
            LocalDate random = between(start, end);
            Events event = Events.builder()
                    .description("Праздник " + i)
                    .name("Дни рождения " + random)
                    .startDate(new Calendar.Builder().setDate(random.getYear(), random.getMonth().getValue(), random.getDayOfMonth()).build())
                    .finishDate((new Calendar.Builder().setDate(random.getYear(), random.getMonth().getValue(), random.getDayOfMonth()).build())).build();
            eventsService.saveEvents(event);
            i++;
        }
    }

    private static void generateAddress() {
        int i = 0;
        var minValue = userService.getMinUserId();
        while (i != 100) {
            var user = userService.getUser(minValue++);
            var address = Address.builder()
                    .city(getRandomString())
                    .country(getRandomString())
                    .houseNumber(Integer.toString(i))
                    .street(getRandomString())
                    .user(user)
                    .build();

            addressService.saveAddress(address);
            i++;
        }
    }

    private static void generateUsersInfo() {
        int i = 0;
        Random random = new Random();
        while (i != 100) {
            var user = User.builder()
                    .gender(random.nextInt(2))
                    .name(getRandomString())
                    .surname(getRandomString())
                    .age(random.nextInt(100))
                    .build();
            userService.saveUser(user);
            i++;
        }
    }

    private static void generateServiceInfo() {
        Random random = new Random();
        int i = 0;
        while (i != 100) {
            var serviceInfo = Service.builder()
                    .cost(random.nextFloat())
                    .name(getRandomString())
                    .description(getRandomString())
                    .build();
            service.save(serviceInfo);
            i++;
        }
    }

    private static void generateOrderInfo() {
        int i = 0;
        Random random = new Random();

        LocalDate start = LocalDate.of(2021, Month.OCTOBER, 14);
        LocalDate end = LocalDate.now();
        var minUser = userService.getMinUserId();
        var maxUser = userService.getCount() + minUser - 1;
        var minService = service.getMinService();
        var maxService = service.getCount() + minService - 1;
        while (i != 100000) {
            var randomServiceValue = random.nextInt((maxService - minService) + minService);
            var randomUserValue = random.nextInt((maxUser - minUser) + minUser);
            randomServiceValue = Math.max(randomServiceValue, minService);
            randomUserValue = Math.max(randomUserValue, minUser);
            var user = userService.getUser(randomUserValue);
            var serviceInfo = service.getService(randomServiceValue);
            var order = Order.builder()
                    .executionDate(Date.valueOf(between(start, end)))
                    .user(user)
                    .service(serviceInfo)
                    .build();
            orderService.saveEvents(order);
            i++;
        }
    }

    private static LocalDate between(LocalDate startInclusive, LocalDate endExclusive) {
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomDay);
    }

    private static String getRandomString() {
        int length = 10;
        return RandomString.make(length);
    }
}

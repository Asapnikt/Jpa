package com.example.demo;

import com.example.demo.entity.Order;
import com.example.demo.repository.AddressJpaRepository;
import com.example.demo.repository.OrderJpaRepository;
import com.example.demo.service.AddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.stream.Collectors;

@SpringBootTest
public class Tests {
    private AddressJpaRepository addressJpaRepository;
    private OrderJpaRepository orderJpaRepository;
    private AddressService addressService;

    @Autowired
    public void setAddressJpaRepository(AddressJpaRepository addressJpaRepository) {
        this.addressJpaRepository = addressJpaRepository;
    }

    @Autowired
    public void setOrderJpaRepository(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    @Autowired
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }

    @Test()
    public void findAllTest() {
        long startTime = System.currentTimeMillis();
        var addressList = addressJpaRepository.findAll();
        long resultTime = System.currentTimeMillis() - startTime;

        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("Find all");
        System.out.printf("Time execution: %s sec. %s msec; Counts elements %s%n", resultTime / 1000, resultTime % 1000, addressList.size());
        System.out.println("-----------------------------------------------------------------------------------");
    }

    @Test()
    public void findByName() {
        long startTime = System.currentTimeMillis();
        var address = addressJpaRepository.findByCity("ZIYR7hte3F");
        long resultTime = System.currentTimeMillis() - startTime;

        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("Find by name");
        System.out.printf("Parametrs:  id = %s, name = %s" + "\n", address.getId(), address.getCity());
        System.out.printf("Time execution: %s sec. %s msec;  " + "\n", resultTime / 1000, resultTime % 1000);
        System.out.println("-----------------------------------------------------------------------------------");
    }

    @Test()
    public void findByUserId() {
        long startTime = System.currentTimeMillis();
        var address = addressJpaRepository.findByUser_Id(110111);
        long resultTime = System.currentTimeMillis() - startTime;

        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("Find by user Id");
        System.out.printf("Parametrs:  id = %s" + "\n", address.getId());
        System.out.printf("Time execution: %s sec. %s msec;  " + "\n", resultTime / 1000, resultTime % 1000);
        System.out.println("-----------------------------------------------------------------------------------");
    }

    @Test()
    public void findAllOrderTest() {
        long startTime = System.currentTimeMillis();
        var orders = orderJpaRepository.findAll();
        long resultTime = System.currentTimeMillis() - startTime;

        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("Find all");
        System.out.printf("Time execution: %s sec. %s msec; Counts elements %s%n", resultTime / 1000, resultTime % 1000, orders.size());
        System.out.println("-----------------------------------------------------------------------------------");
    }

    @Test()
    public void findByUserId_1() {
        long startTime = System.currentTimeMillis();
        orderJpaRepository.findByUser_Id(110111);
        long resultTime = System.currentTimeMillis() - startTime;

        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("Find by name");
        System.out.printf("Time execution: %s sec. %s msec;  " + "\n", resultTime / 1000, resultTime % 1000);
        System.out.println("-----------------------------------------------------------------------------------");
    }

    @Test
    public void findFromTableWithManyLinks() {
        var order = orderJpaRepository.findById(132951L).orElseThrow();

        System.out.println("------------------------------------------------------");
        System.out.printf("Params Order %s, %s", order.getId(), order.getExecutionDate());
        System.out.println();
        System.out.printf("Params Service %s, %s", order.getService().getId(), order.getService().getName());
        System.out.println();
        System.out.printf("Params User %s, %s", order.getUser().getId(), order.getUser().getName());
        System.out.println();
        System.out.println("------------------------------------------------------");
    }

    @Test
    public void findFromTableWithOtherLinksTypes() {
        var order = orderJpaRepository.findById(132951L).orElseThrow();

        System.out.println("------------------------------------------------------");
        System.out.printf("Params Order %s, %s", order.getId(), order.getExecutionDate());
        System.out.println();
        System.out.printf("Params Service %s, %s", order.getService().getId(), order.getService().getName());
        System.out.println();
        System.out.printf("Params User %s, %s", order.getUser().getId(), order.getUser().getName());
        System.out.println();
        System.out.printf("Params Address %s, %s", order.getUser().getAddress().getId(), order.getUser().getAddress().getCity());
        System.out.println();
        System.out.println("------------------------------------------------------");
    }

    @Test
    public void grouppingByJPA() {
        long startTime = System.currentTimeMillis();
        var orders = orderJpaRepository.findByGrouping();
        long resultTime = System.currentTimeMillis() - startTime;
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.printf("Find by groupping %s" + "\n", orders.size());
        System.out.printf("Time execution: %s sec. %s msec;  " + "\n", resultTime / 1000, resultTime % 1000);
        orders.forEach(x -> System.out.printf("key %s: value %s" + "\n", x[0], x[1]));
        System.out.println("-----------------------------------------------------------------------------------");
    }

    @Test
    public void grouppingByStreamApi() {
        long startTime = System.currentTimeMillis();
        var orders = orderJpaRepository.findAll();
        var grouppingOrders = orders.stream()
                .collect(Collectors.groupingBy(Order::getExecutionDate, Collectors.counting()));
        long resultTime = System.currentTimeMillis() - startTime;

        System.out.println("-----------------------------------------------------------------------------------");
        System.out.printf("Find by groupping %s" + "\n", orders.size());
        System.out.printf("Time execution: %s sec. %s msec;  " + "\n", resultTime / 1000, resultTime % 1000);
        grouppingOrders.forEach((key, value) -> System.out.printf("key %s: value %s" + "\n", key, value));
        System.out.println("-----------------------------------------------------------------------------------");
    }

    @Test
    public void testPaggination() {
        var page = 1;
        var count = 10;
        Pageable pageParam = PageRequest.of(page, count, Sort.Direction.DESC, "id");
        var info = addressService.getAddressWithPagination(pageParam);
        System.out.printf("Page %s, count on page %s" + "\n", page, count);
        info.get().forEach(address -> System.out.println("City name:" + address.getCity() + ";"));
    }
}

package it.dawit.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import it.dawit.order.model.Order;
import it.dawit.order.model.MessageWrapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

/**
 * Created by dawit on 11/12/2016.
 */
@RestController
public class OrderController {

    @HystrixCommand
    @PreAuthorize("hasAuthority('FOO_READ')")
    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET, produces = "application/json")
    public MessageWrapper getOrder(@PathVariable int id) throws ExecutionException, InterruptedException {
        return new MessageWrapper<>(new Order(id, "First order"), "");
    }

    @HystrixCommand
    @PreAuthorize("hasAuthority('FOO_WRITE')")
    @RequestMapping(value = "/order", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public MessageWrapper CreateOrder(@RequestBody Order order) throws ExecutionException, InterruptedException {
        return new MessageWrapper<>(order, "Order created");
    }

}

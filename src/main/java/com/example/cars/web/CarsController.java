package com.example.cars.web;

import com.example.cars.domain.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController()
@RequestMapping("/cars")
public class CarsController {

    private Map<Integer, Car> cars = new HashMap<>();
    private AtomicInteger nextId = new AtomicInteger(0);

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Car createCar(@RequestBody Car car) {
        log.info("create car {}", car);
        car.setId(nextId.incrementAndGet());
        cars.put(car.getId(), car);
        return car;
    }

    @GetMapping()
    public Collection<Car> getCars() {
        log.info("get cars");
        return cars.values();
    }

    @PutMapping("/{id}")
    public void updateCar(@PathVariable Integer id, @RequestBody Car car) {
        log.info("update car {} car {}", id, car);
        if (cars.containsKey(id)) {
            cars.put(id, car);
        } else {
            throw new IllegalArgumentException(String.format("Updated failed. Invalid id %s", id));
        }
    }

    @GetMapping("/{id}")
    public Car getCar(@PathVariable Integer id) {
        log.info("get car {}", id);
        if (cars.containsKey(id)) {
            return cars.get(id);
        } else {
            throw new IllegalArgumentException(String.format("Updated failed. Invalid id %s", id));
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCar(@PathVariable Integer id) {
        log.info("delete car {}", id);
        if (cars.containsKey(id)) {
            cars.remove(id);
        } else {
            throw new IllegalArgumentException(String.format("Delete failed. Invalid id %s", id));
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private void badRequest(IllegalArgumentException ex) {
    }

}

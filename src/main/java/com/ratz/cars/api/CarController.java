package com.ratz.cars.api;

import com.ratz.cars.domain.Car;
import com.ratz.cars.domain.CarService;
import com.ratz.cars.domain.dto.CarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {


  @Autowired
  private CarService service;


  @GetMapping()
  public ResponseEntity get() {
    List<CarDTO> cars = service.getCars();
    return ResponseEntity.ok(cars);
  }

  @GetMapping("/{id}")
  public ResponseEntity get(@PathVariable("id") Long id) {
    Optional<CarDTO> car = service.getCarById(id);

    return car
        .map(c -> ResponseEntity.ok(car))
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/type/{type}")
  public ResponseEntity getCarsByType(@PathVariable("type") String type) {
    List<CarDTO> cars = service.getCarsByType(type);
    return cars.isEmpty() ?
        ResponseEntity.noContent().build() :
        ResponseEntity.ok(cars);
  }

  @PostMapping
  public ResponseEntity post(@RequestBody Car car) {

    try {
      CarDTO c = service.insert(car);

      URI location = getUri(c);
      return ResponseEntity.created(location).build();

    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }

  }

  private URI getUri(CarDTO c) {
    return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(c.getId()).toUri();
  }

  @PutMapping("/{id}")
  public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Car car) {

    CarDTO c = service.update(car, id);

    return c != null ?
        ResponseEntity.ok(c) :
        ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable("id") Long id) {

    service.delete(id);
    return ResponseEntity.ok().build();
  }
}

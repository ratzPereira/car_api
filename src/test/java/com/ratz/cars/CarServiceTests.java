package com.ratz.cars;

import com.ratz.cars.domain.Car;
import com.ratz.cars.domain.CarService;
import com.ratz.cars.domain.dto.CarDTO;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarServiceTests {

    @Autowired
    private CarService service;

    @Test
    @DisplayName("Should save one car to database")
    public void saveCarTest() {

        Car car = new Car();
        car.setName("Ferrari");
        car.setType("Sport");

        CarDTO carDTO = service.insert(car);
        assertNotNull(carDTO);

        Long id = carDTO.getId();
        assertNotNull(id);

        Optional<CarDTO> optionalCarDTO = service.getCarById(id);

        assertNotNull(optionalCarDTO);
        assertNotNull(optionalCarDTO.get().getId());
        assertThat(optionalCarDTO.get().getName()).isEqualTo("Ferrari");
        assertThat(optionalCarDTO.get().getType()).isEqualTo("Sport");

        service.delete(id);

        assertFalse(service.getCarById(id).isPresent());
    }

    @Test
    @DisplayName("Should get List of cars")
    public void getListOfCarsTest() {

        List<CarDTO> carDTOS = service.getCars();
        assertEquals(30, carDTOS.size());
    }

    @Test
    @DisplayName("Should get List of cars per type")
    public void getListOfCarsByTypeTest() {

        assertEquals(10,service.getCarsByType("classicos").size());
        assertEquals(10,service.getCarsByType("esportivos").size());
        assertEquals(10,service.getCarsByType("luxo").size());

        assertEquals(0, service.getCarsByType("x").size());
    }
}

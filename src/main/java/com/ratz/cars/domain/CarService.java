package com.ratz.cars.domain;

import com.ratz.cars.domain.dto.CarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepository rep;

    public List<CarDTO> getCars() {
        List<CarDTO> list = rep.findAll().stream().map(CarDTO::create).collect(Collectors.toList());
        return list;
    }

    public Optional<CarDTO> getCarById(Long id) {
        Optional<Car> car = rep.findById(id);
        return car.map(CarDTO::create);
    }

    public List<CarDTO> getCarsByType(String type) {
        return rep.findByType(type).stream().map(CarDTO::create).collect(Collectors.toList());
    }

    public CarDTO insert(Car car) {
        Assert.isNull(car.getId(),"Could not insert the car");

        return CarDTO.create(rep.save(car));
    }

    public CarDTO update(Car car, Long id) {
        Assert.notNull(id,"Could not update");

        // get car
        Optional<Car> optional = rep.findById(id);
        if(optional.isPresent()) {
            Car db = optional.get();

            db.setName(car.getName());
            db.setType(car.getType());
            System.out.println("Car id " + db.getId());

            rep.save(db);

            return CarDTO.create(db);
        } else {
            return null;
        }
    }

    public boolean delete(Long id) {

        return getCarById(id).map(c -> {
            rep.deleteById(id);
            return true;
        }).orElse(false);
    }
}

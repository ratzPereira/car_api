package com.ratz.cars;

import com.ratz.cars.domain.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarApplicationTests {

    @Autowired
    private CarService service;

    @Test
    public void contextLoads() {

//        Carro c = new Carro();
//        c.setNome("Novo carro");
//        c.setTipo("esportivos");
//
//        service.save(c);
//
//        Long id = c.getId();
//
//        assertNotNull(id);
    }

}

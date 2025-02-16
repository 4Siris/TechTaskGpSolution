package org.example.repositories;

import org.example.models.Address;
import org.example.models.ArrivalTime;
import org.example.models.Contacts;
import org.example.models.Hotel;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
class HotelRepositoryTest {
    @Autowired
    private HotelRepository hotelRepository;

    @BeforeEach
    public void setUp(){
        hotelRepository.save(Hotel.builder()
                .name("DoubleTree by Hilton Minsk")
                .brand("Hilton")
                .address(Address.builder()
                        .houseNumber(9)
                        .street("Pobediteley Avenue")
                        .city("Minsk")
                        .county("Belarus")
                        .postCode("220004")
                        .build())
                .contacts(Contacts.builder()
                        .phone("+375 17 309-80-00")
                        .email("doubletreeminsk.info@hilton.com")
                        .build())
                .arrivalTime(ArrivalTime.builder()
                        .checkIn("14:00")
                        .checkOut("12:00").build())
                .amenities(List.of(new String[]{"Free parking",
                        "Free WiFi",
                        "Non-smoking rooms",
                        "Concierge",
                        "On-site restaurant",
                        "Fitness center",
                        "Pet-friendly rooms",
                        "Room service",
                        "Business center",
                        "Meeting rooms"})).build());
        hotelRepository.save(Hotel.builder()
                .name("DoubleTree by Hilton Minsk")
                .brand("Hilton")
                .address(Address.builder()
                        .houseNumber(9)
                        .street("Gaspacho Avenue")
                        .city("Leba")
                        .county("Poland")
                        .postCode("220004")
                        .build())
                .contacts(Contacts.builder()
                        .phone("+375 17 309-80-00")
                        .email("doubletreeminsk.info@hilton.com")
                        .build())
                .arrivalTime(ArrivalTime.builder()
                        .checkIn("14:00")
                        .checkOut("12:00").build())
                .amenities(List.of(new String[]{"Free parking",
                        "Pet-friendly rooms",
                        "Room service",
                        "Business center",
                        "Meeting rooms"})).build());
        hotelRepository.save(Hotel.builder()
                .name("DoubleTree by Hilton Minsk")
                .brand("Beverly")
                .address(Address.builder()
                        .houseNumber(27)
                        .street("Pobediteley Avenue")
                        .city("Grodno")
                        .county("Belarus")
                        .postCode("220004")
                        .build())
                .contacts(Contacts.builder()
                        .phone("+375 17 309-80-00")
                        .email("doubletreeminsk.info@hilton.com")
                        .build())
                .arrivalTime(ArrivalTime.builder()
                        .checkIn("14:00")
                        .checkOut("12:00").build())
                .amenities(List.of(new String[]{"Free parking",
                        "Free WiFi",
                        "Non-smoking rooms",
                        "Concierge",
                        "On-site restaurant",
                        "Fitness center",
                        "Meeting rooms"})).build());
    }
    @AfterEach
    public void cleanUp(){
        hotelRepository.deleteAll();
    }

    @Test
    public void findByIds(){
        Assertions.assertEquals(hotelRepository.findById(1L).get().getName(),"DoubleTree by Hilton Minsk");
        Assertions.assertEquals(hotelRepository.findById(2L).get().getAddress().getStreet(),"Gaspacho Avenue");
        Assertions.assertEquals(hotelRepository.findById(3L).get().getAddress().getHouseNumber(),27);
    }
}
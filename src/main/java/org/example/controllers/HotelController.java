package org.example.controllers;

import org.example.models.Hotel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.models.dtos.HotelProfile;
import org.example.models.dtos.HotelProfileDetailed;
import org.example.services.HotelManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Tag(name = "main_controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/property-view")
public class HotelController {
    @Autowired
    private HotelManagerService hotelManagerService;

    @Operation(
            summary = "(1)Получение списка всех отелей с их краткой информацией",
            description = "Берёт все отели из базы данных и возвращает список их простых профилей"
    )
    @GetMapping("/hotels")
    private ResponseEntity<List<HotelProfile>> getAll(){
        return new ResponseEntity<>(hotelManagerService.getAll(), HttpStatusCode.valueOf(200));
    }

    @Operation(
            summary = "(2)Получение расширенной информации по конктретному отелю",
            description = "Получает id нужного отеля, берёт его из базы данных и возвращает его детальный профиль"
    )
    @GetMapping("/hotels/{id}")
    private ResponseEntity<HotelProfileDetailed> getById(@PathVariable long id){
        return new ResponseEntity<>(hotelManagerService.getById(id),HttpStatusCode.valueOf(200));
    }

    @Operation(
            summary = "(3)Поиск получение списка всех отелей с их краткой информацией по параметрам",
            description = "Получает фильтр для требуемых отелей, ищет из базы данных подходящие отели и возвращает список их простых профилей"
    )
    @GetMapping("/search")
    private ResponseEntity<List<HotelProfile>> gatAllFiltered(@RequestParam (required = false) String name,
                                                              @RequestParam (required = false) String brand,
                                                              @RequestParam (required = false) String city,
                                                              @RequestParam (required = false) String county,
                                                              @RequestParam (required = false) List<String> amenities) {
        return new ResponseEntity<>(hotelManagerService.getAllFiltered(name,brand,city,county,amenities),HttpStatusCode.valueOf(200));
    }

    @Operation(
            summary = "(4)Создание нового отеля",
            description = "Получает объект типа Hotel, сохраняет его и возвращает его простой профиль"
    )
    @PostMapping("/hotels")
    private ResponseEntity<HotelProfile> save(@RequestBody Hotel newHotel){
        return new ResponseEntity<>(hotelManagerService.save(newHotel),HttpStatusCode.valueOf(200));
    }

    @Operation(
            summary = "(5)Добавление списка amenities к отелю",
            description = "Получает список удобств отеля и id отеля которому надо их добавить"
    )
    @PostMapping("/hotels/{id}")
    private ResponseEntity<String> addAmenities(@PathVariable long id,
                                                @RequestBody List<String> amenities){
        hotelManagerService.addAmenitiesToHotel(id,amenities);
        return new ResponseEntity<>("OK",HttpStatusCode.valueOf(200));
    }

    @Operation(
            summary = "(6)Получение колличества отелей сгруппированных по каждому значению указанного параметра",
            description = "Получает параметр по которому надо сгруппировать все отели в базе данных, берёт кол-во отелей по сгруппиорованному признаку"
    )
    @GetMapping("/histogram/{param}")
    private ResponseEntity<HashMap<String,Long>> groupByParam(@PathVariable String param){
        return new ResponseEntity(hotelManagerService.groupByParam(param),HttpStatusCode.valueOf(200));
    }
}

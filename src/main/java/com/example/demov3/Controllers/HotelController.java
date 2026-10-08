package com.example.demov3.Controllers;

import com.example.demov3.Entities.Hotel;
import com.example.demov3.Services.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hoteles")
public class HotelController {
    private final HotelService service;

    public HotelController(HotelService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Hotel> crear(@RequestBody Hotel hotel) {
        return ResponseEntity.status(201).body(service.crear(hotel));
    }

    @GetMapping
    public List<Hotel> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> actualizar(@PathVariable Long id, @RequestBody Hotel hotel) {
        return service.actualizar(id, hotel).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return service.eliminar(id) ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}

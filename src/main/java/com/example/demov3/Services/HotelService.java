package com.example.demov3.Services;

import com.example.demov3.Entities.Hotel;
import com.example.demov3.Repositories.HotelRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    private final HotelRepository repository;

    public HotelService(HotelRepository repository) {
        this.repository = repository;
    }

    public Hotel crear(Hotel datos) {
        Hotel hotel = new Hotel();
        copiarDatos(datos, hotel);
        return repository.save(hotel);
    }

    public List<Hotel> listar() { return repository.findAll(); }

    public Optional<Hotel> buscarPorId(Long id) { return repository.findById(id); }

    public Optional<Hotel> actualizar(Long id, Hotel datos) {
        return repository.findById(id).map(hotel -> {
            copiarDatos(datos, hotel);
            return repository.save(hotel);
        });
    }

    public boolean eliminar(Long id) {
        if (!repository.existsById(id)) { return false; }
        repository.deleteById(id);
        return true;
    }

    private void copiarDatos(Hotel origen, Hotel destino) {
        destino.setNombre(origen.getNombre());
        destino.setCiudad(origen.getCiudad());
        destino.setCategoria(origen.getCategoria());
        destino.setHabitaciones(origen.getHabitaciones());
    }
}

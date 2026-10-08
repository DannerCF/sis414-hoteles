package com.example.demov3;

import com.example.demov3.Entities.Hotel;
import com.example.demov3.Repositories.HotelRepository;
import com.example.demov3.Services.HotelService;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HotelServiceTests {
    private final HotelRepository repository = mock(HotelRepository.class);
    private final HotelService service = new HotelService(repository);

    private Hotel datos() {
        Hotel hotel = new Hotel();
        hotel.setNombre("F-001");
        hotel.setCiudad("Ana");
        hotel.setCategoria(4);
        hotel.setHabitaciones(10);
        return hotel;
    }

    @Test
    void crearGeneraUnaEntidadNuevaSinUsarElIdDelCiudad() {
        Hotel entrada = datos();
        entrada.setId(99L);
        when(repository.save(any(Hotel.class))).thenAnswer(call -> {
            Hotel nueva = call.getArgument(0);
            assertNull(nueva.getId());
            nueva.setId(1L);
            return nueva;
        });
        Hotel creada = service.crear(entrada);
        assertEquals(1L, creada.getId());
        assertEquals("F-001", creada.getNombre());
        assertEquals("Ana", creada.getCiudad());
        assertEquals(4, creada.getCategoria());
        assertEquals(10, creada.getHabitaciones());
    }

    @Test
    void actualizarConservaElIdDeLaRuta() {
        Hotel existente = datos();
        existente.setId(1L);
        Hotel entrada = datos();
        entrada.setId(99L);
        entrada.setCiudad("Luis");
        when(repository.findById(1L)).thenReturn(Optional.of(existente));
        when(repository.save(existente)).thenReturn(existente);
        Hotel actualizada = service.actualizar(1L, entrada).orElseThrow();
        assertEquals(1L, actualizada.getId());
        assertEquals("Luis", actualizada.getCiudad());
        verify(repository).save(existente);
    }

    @Test
    void actualizarAusenteNoCreaOtraHotel() {
        when(repository.findById(8L)).thenReturn(Optional.empty());
        assertTrue(service.actualizar(8L, datos()).isEmpty());
        verify(repository, never()).save(any());
    }

    @Test
    void eliminarSoloBorraCuandoExiste() {
        when(repository.existsById(1L)).thenReturn(true);
        assertTrue(service.eliminar(1L));
        verify(repository).deleteById(1L);
        assertFalse(service.eliminar(8L));
        verify(repository, never()).deleteById(8L);
    }
}

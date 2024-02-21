package com.example.demo;

import com.example.demo.dto.ClienteRequest;
import com.example.demo.dto.ClienteResponse;
import com.example.demo.entities.Cliente;
import com.example.demo.helpers.FormatUtil;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteServiceTests {
    @Autowired
    private ClienteService clienteService;

    @MockBean
    ClienteRepository clienteRepository;

    @Test
    public void testGetClientes() {
        Cliente clienteMock = Cliente.builder().id(UUID.fromString("30ce4894-0f97-497d-af2b-05be251d3f56"))
                .nombre("Juan")
                .apellidoPaterno("Alcazar")
                .apellidoMaterno("Bautista")
                .created(LocalDateTime.now())
                .activo(true)
                .build();
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(clienteMock);

        when(clienteRepository.findAll()).thenReturn(clientes);

        List<ClienteResponse> clienteResponses = clienteService.list();
        assertEquals(clientes.size(), clienteResponses.size());
            for (int i = 0; i < clienteResponses.size(); i++) {
                assertEquals(clienteResponses.get(i).getId(), clientes.get(i).getId().toString());
                assertEquals(clienteResponses.get(i).getNombreCompleto(), FormatUtil.formatearNombre(clientes.get(i).getNombre(),
                        clientes.get(i).getApellidoPaterno(), clientes.get(i).getApellidoMaterno()));
            }
    }

    @Test
    public void testCreateCliente() {
        Cliente clienteMock = Cliente.builder().id(UUID.fromString("30ce4894-0f97-497d-af2b-05be251d3f56"))
                .nombre("Juan")
                .apellidoPaterno("Alcazar")
                .apellidoMaterno("Bautista")
                .created(LocalDateTime.now())
                .activo(true)
                .build();

        ClienteRequest clienteRequest = ClienteRequest.builder().nombre("Juan")
                .apellidoPaterno("Alcazar")
                .apellidoMaterno("Bautista")
                .build();

        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteMock);
        clienteService.create(clienteRequest);
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    public void testUpdateCliente() {
        Cliente clienteMock = Cliente.builder().id(UUID.fromString("30ce4894-0f97-497d-af2b-05be251d3f56"))
                .nombre("Juan")
                .apellidoPaterno("Alcazar")
                .apellidoMaterno("Bautista")
                .created(LocalDateTime.now())
                .activo(true)
                .build();

        ClienteRequest clienteRequest = ClienteRequest.builder().nombre("Juan")
                .apellidoPaterno("Alcazar")
                .apellidoMaterno("Bautista")
                .build();

        String id = "30ce4894-0f97-497d-af2b-05be251d3f56";
        when(clienteRepository.findById(UUID.fromString(id))).thenReturn(Optional.of(clienteMock));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteMock);
        clienteService.update(id, clienteRequest);
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }
}

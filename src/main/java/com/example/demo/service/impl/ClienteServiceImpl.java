package com.example.demo.service.impl;

import com.example.demo.dto.ClienteRequest;
import com.example.demo.dto.ClienteResponse;
import com.example.demo.entities.Cliente;
import com.example.demo.exceptions.RecursoNoEncontradoException;
import com.example.demo.helpers.FormatUtil;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.service.ClienteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteServiceImpl implements ClienteService {
    ClienteRepository clienteRepository;

    @Autowired
    ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<ClienteResponse> list() {
        return clienteRepository.findAll()
                .stream().map(cliente -> new ClienteResponse(
                        cliente.getId().toString(),
                        FormatUtil.formatearNombre(
                                cliente.getNombre(),
                                cliente.getApellidoPaterno(),
                                cliente.getApellidoMaterno())
                )).toList();
    }

    @Transactional
    @Override
    public void create(ClienteRequest cliente) {
        Cliente clienteEntity = Cliente.builder().nombre(cliente.getNombre())
                .apellidoPaterno(cliente.getApellidoPaterno())
                .apellidoMaterno(cliente.getApellidoMaterno())
                .created(LocalDateTime.now())
                .activo(true)
                .build();
        clienteRepository.save(clienteEntity);
    }

    @Transactional
    @Override
    public void update(String id, ClienteRequest cliente) {
        Optional<Cliente> clienteEntity = clienteRepository.findById(UUID.fromString(id));
        if (!clienteEntity.isPresent()) {
            throw new RecursoNoEncontradoException("Usuario no encontrado con el ID: " + id);
        }
        clienteEntity.map(clienteEntity1 -> {
            clienteEntity1.setNombre(cliente.getNombre());
            clienteEntity1.setApellidoPaterno(cliente.getApellidoPaterno());
            clienteEntity1.setApellidoMaterno(cliente.getApellidoMaterno());
            return clienteRepository.save(clienteEntity1);
        });
    }


}

package com.example.demo.service;

import com.example.demo.dto.ClienteRequest;
import com.example.demo.dto.ClienteResponse;
import com.example.demo.entities.Cliente;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteService {

    List<ClienteResponse> list();

    void create(ClienteRequest cliente);

    void update(String id, ClienteRequest cliente);
}

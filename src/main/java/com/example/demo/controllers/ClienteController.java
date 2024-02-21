package com.example.demo.controllers;

import com.example.demo.dto.ClienteRequest;
import com.example.demo.dto.ClienteResponse;
import com.example.demo.entities.Cliente;
import com.example.demo.exceptions.RecursoNoEncontradoException;
import com.example.demo.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api")
@Validated
public class ClienteController {
    private ClienteService clienteService;

    @Autowired
    ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @GetMapping(value = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list() {
        List<ClienteResponse> body = clienteService.list();
        return ResponseEntity.ok(body);
    }

    @PostMapping(value = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@Valid @RequestBody ClienteRequest cliente) {
        clienteService.create(cliente);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/clientes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestBody ClienteRequest cliente) {
        try {
            clienteService.update(id, cliente);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

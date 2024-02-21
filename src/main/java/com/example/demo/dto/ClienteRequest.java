package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteRequest {
    @NotBlank(message = "Nombre no puede estar vacío")
    @NotNull(message = "Nombre no puede ser nulo")
    private String nombre;

    @NotBlank(message = "Apellido paterno no puede estar vacío")
    @NotNull(message = "Apellido paterno no puede ser nulo")
    private String apellidoPaterno;
    private String apellidoMaterno;
}

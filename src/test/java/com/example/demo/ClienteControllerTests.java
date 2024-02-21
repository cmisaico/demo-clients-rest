package com.example.demo;

import com.example.demo.dto.ClienteResponse;
import com.example.demo.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ClienteService clienteService;
	@Test
	public void testCreateCliente() throws Exception {
		mockMvc.perform(post("/api/clientes")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"nombre\":\"Juan\",\"apellidoPaterno\":\"Alcazar\",\"apellidoMaterno\":\"Bautista\"}"))
				.andExpect(status().isCreated());
	}

	@Test
	public void testGetAllClientes() throws Exception {

		ClienteResponse clienteMock = ClienteResponse.builder().id("30ce4894-0f97-497d-af2b-05be251d3f56")
				.nombreCompleto("Juan Alcazar Bautista")
				.build();
		List<ClienteResponse> clientes = new ArrayList<>();
		clientes.add(clienteMock);

		when(clienteService.list()).thenReturn(clientes);

		mockMvc.perform(get("/api/clientes"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value("30ce4894-0f97-497d-af2b-05be251d3f56"))
				.andExpect(jsonPath("$[0].nombreCompleto").value("Juan Alcazar Bautista"));
	}

	@Test
	public void testUpdateCliente() throws Exception {
		mockMvc.perform(put("/api/clientes/30ce4894-0f97-497d-af2b-05be251d3f56")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"nombre\":\"Maria\",\"apellidoPaterno\":\"Prado\",\"apellidoMaterno\":\"Bautista\"}"))
				.andExpect(status().isCreated());
	}

}

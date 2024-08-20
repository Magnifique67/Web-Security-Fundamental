package com.Lab1.Web_Security_Fundamental.service;

import com.Lab1.Web_Security_Fundamental.dto.InputDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputServiceTest {

    private InputService inputService;

    @BeforeEach
    void setUp() {
        inputService = new InputService();
    }

    @Test
    void testCreateInput() {
        InputDTO inputDTO = new InputDTO();
        inputDTO.setName("Test Name");
        inputDTO.setEmail("test@example.com");

        InputDTO createdInput = inputService.createInput(inputDTO);

        assertNotNull(createdInput.getId());
        assertEquals("Test Name", createdInput.getName());
        assertEquals("test@example.com", createdInput.getEmail());
    }

    @Test
    void testGetInput() {
        InputDTO inputDTO = new InputDTO();
        inputDTO.setName("Test Name");
        inputDTO.setEmail("test@example.com");

        InputDTO createdInput = inputService.createInput(inputDTO);

        assertTrue(inputService.getInput(createdInput.getId()).isPresent());
        assertEquals("test@example.com", inputService.getInput(createdInput.getId()).get().getEmail());
    }

    @Test
    void testUpdateInput() {
        InputDTO inputDTO = new InputDTO();
        inputDTO.setName("Original Name");
        inputDTO.setEmail("original@example.com");
        InputDTO createdInput = inputService.createInput(inputDTO);

        InputDTO updateDTO = new InputDTO();
        updateDTO.setName("Updated Name");
        updateDTO.setEmail("updated@example.com");
        InputDTO updatedInput = inputService.updateInput(createdInput.getId(), updateDTO);

        assertEquals("Updated Name", updatedInput.getName());
        assertEquals("updated@example.com", updatedInput.getEmail());
    }

    @Test
    void testDeleteInput() {
        InputDTO inputDTO = new InputDTO();
        inputDTO.setName("Test Name");
        inputDTO.setEmail("test@example.com");

        InputDTO createdInput = inputService.createInput(inputDTO);

        inputService.deleteInput(createdInput.getId());

        assertFalse(inputService.getInput(createdInput.getId()).isPresent());
    }
}

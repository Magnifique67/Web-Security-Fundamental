package com.Lab1.Web_Security_Fundamental.controller;

import org.owasp.encoder.Encode;
import com.Lab1.Web_Security_Fundamental.dto.InputDTO;
import com.Lab1.Web_Security_Fundamental.service.InputService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/api/inputs")
@Validated
public class InputController {

    private final InputService inputService;

    public InputController(InputService inputService) {
        this.inputService = inputService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getInput(@PathVariable @NotNull @Min(1) Long id) {
        try {
            Optional<InputDTO> inputDTO = inputService.getInput(id);
            if (inputDTO.isPresent()) {
                // Encode user input before returning it in the response
                String safeHtml = Encode.forHtml(inputDTO.get().getName());
                return ResponseEntity.ok(safeHtml);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<InputDTO> createInput(@RequestBody @Valid InputDTO inputDTO) {
        try {
            InputDTO createdInput = inputService.createInput(inputDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdInput);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<InputDTO> updateInput(@PathVariable @NotNull @Min(1) Long id, @Valid @RequestBody InputDTO inputDTO) {
        try {
            InputDTO updatedInput = inputService.updateInput(id, inputDTO);
            return ResponseEntity.ok(updatedInput);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteInput(@PathVariable @NotNull @Min(1) Long id) {
        try {
            inputService.deleteInput(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

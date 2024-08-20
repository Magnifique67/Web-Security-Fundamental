package com.Lab1.Web_Security_Fundamental.service;
import com.Lab1.Web_Security_Fundamental.dto.InputDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class InputService {

    private final Map<Long, InputDTO> storage = new HashMap<>();
    private long currentId = 1;

    public Optional<InputDTO> getInput(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public InputDTO createInput(InputDTO inputDTO) {
        inputDTO.setId(currentId++);
        storage.put(inputDTO.getId(), inputDTO);
        return inputDTO;
    }

    public InputDTO updateInput(Long id, InputDTO inputDTO) {
        if (storage.containsKey(id)) {
            inputDTO.setId(id);
            storage.put(id, inputDTO);
            return inputDTO;
        }
        throw new IllegalArgumentException("Input not found");
    }

    public void deleteInput(Long id) {
        storage.remove(id);
    }
}
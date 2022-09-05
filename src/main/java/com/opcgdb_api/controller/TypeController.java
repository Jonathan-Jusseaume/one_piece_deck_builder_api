package com.opcgdb_api.controller;

import com.opcgdb_api.dto.Type;
import com.opcgdb_api.service.TypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/types")
@RequiredArgsConstructor
public class TypeController {

    private final TypeService typeService;

    @Operation(summary = "Get the list of all the different types")
    @GetMapping
    public List<Type> list(
            @Parameter(description = "Code of the language of the response")
            @RequestParam(name = "language", defaultValue = "en") String languageCode) {
        return typeService.list(languageCode);
    }

}

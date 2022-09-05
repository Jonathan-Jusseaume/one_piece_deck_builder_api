package com.opcgdb_api.controller;

import com.opcgdb_api.dto.Color;
import com.opcgdb_api.service.ColorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/colors")
@RequiredArgsConstructor
public class ColorController {

    private final ColorService colorService;

    @Operation(summary = "Get the list of all the different colors")
    @GetMapping
    public List<Color> list(
            @Parameter(description = "Code of the language of the response")
            @RequestParam(name = "language", defaultValue = "en") String languageCode) {
        return colorService.list(languageCode);
    }

}

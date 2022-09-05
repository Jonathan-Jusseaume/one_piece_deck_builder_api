package com.opcgdb_api.controller;

import com.opcgdb_api.dto.Attribute;
import com.opcgdb_api.service.AttributeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/attributes")
@RequiredArgsConstructor
public class AttributeController {

    private final AttributeService attributeService;

    @Operation(summary = "Get the list of all the different attributes")
    @GetMapping
    public List<Attribute> list(@Parameter(description = "Code of the language of the response")
                                @RequestParam(name = "language", defaultValue = "en") String languageCode) {
        return attributeService.list(languageCode);
    }

}

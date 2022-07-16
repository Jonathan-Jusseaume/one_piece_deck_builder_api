package com.opcgdb_api.controller;

import com.opcgdb_api.dto.Attribute;
import com.opcgdb_api.service.AttributeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/attributes")
@RequiredArgsConstructor
public class AttributeController {

    private final AttributeService attributeService;

    @GetMapping
    public List<Attribute> list(@RequestParam(name = "language", defaultValue = "en") String languageCode) {
        return attributeService.list(languageCode);
    }

}

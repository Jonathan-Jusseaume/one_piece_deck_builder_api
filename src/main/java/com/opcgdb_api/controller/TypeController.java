package com.opcgdb_api.controller;

import com.opcgdb_api.dto.Attribute;
import com.opcgdb_api.dto.Type;
import com.opcgdb_api.service.AttributeService;
import com.opcgdb_api.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/types")
@RequiredArgsConstructor
public class TypeController {

    private final TypeService typeService;

    @GetMapping
    public List<Type> list(@RequestParam(name = "language", defaultValue = "en") String languageCode) {
        return typeService.list(languageCode);
    }

}

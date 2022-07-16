package com.opcgdb_api.controller;

import com.opcgdb_api.dto.Attribute;
import com.opcgdb_api.dto.Color;
import com.opcgdb_api.service.AttributeService;
import com.opcgdb_api.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/colors")
@RequiredArgsConstructor
public class ColorController {

    private final ColorService colorService;

    @GetMapping
    public List<Color> list(@RequestParam(name = "language", defaultValue = "en") String languageCode) {
        return colorService.list(languageCode);
    }

}

package com.opcgdb_api.controller;

import com.opcgdb_api.dto.Attribute;
import com.opcgdb_api.dto.Tag;
import com.opcgdb_api.service.AttributeService;
import com.opcgdb_api.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping
    public List<Tag> list(@RequestParam(name = "language", defaultValue = "en") String languageCode) {
        return tagService.list(languageCode);
    }

}

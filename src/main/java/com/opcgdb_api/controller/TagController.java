package com.opcgdb_api.controller;

import com.opcgdb_api.dto.Tag;
import com.opcgdb_api.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @Operation(summary = "Get the list of all the different tags")
    @GetMapping
    public List<Tag> list(
            @Parameter(description = "Code of the language of the response")
            @RequestParam(name = "language", defaultValue = "en") String languageCode) {
        return tagService.list(languageCode);
    }

}

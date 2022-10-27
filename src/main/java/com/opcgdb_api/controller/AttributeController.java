package com.opcgdb_api.controller;

import com.opcgdb_api.config.LanguageResolver;
import com.opcgdb_api.dto.Attribute;
import com.opcgdb_api.service.AttributeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/attributes")
@RequiredArgsConstructor
public class AttributeController {

    private final AttributeService attributeService;

    private final LanguageResolver languageResolver;

    @Operation(summary = "Get the list of all the different attributes")
    @GetMapping
    public List<Attribute> list(HttpServletRequest request) {
        return attributeService.list(languageResolver.resolveLocale(request).getLanguage());
    }

    @Operation(summary = "Get the Attribute with the specified ID")
    @GetMapping("{attributeId}")
    public Attribute read(@PathVariable(name = "attributeId") Long attributeId,
                          HttpServletRequest request) {
        Attribute attribute = attributeService.read(attributeId,
                languageResolver.resolveLocale(request).getLanguage());
        if (attribute == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attribute with the specified ID not found");
        }
        return attribute;
    }

}

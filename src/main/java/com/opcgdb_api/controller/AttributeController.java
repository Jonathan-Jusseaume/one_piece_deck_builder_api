package com.opcgdb_api.controller;

import com.opcgdb_api.config.LanguageResolver;
import com.opcgdb_api.dto.Attribute;
import com.opcgdb_api.service.AttributeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

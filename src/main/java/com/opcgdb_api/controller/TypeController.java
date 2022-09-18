package com.opcgdb_api.controller;

import com.opcgdb_api.config.LanguageResolver;
import com.opcgdb_api.dto.Type;
import com.opcgdb_api.service.TypeService;
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
@RequestMapping("/api/types")
@RequiredArgsConstructor
public class TypeController {

    private final TypeService typeService;

    private final LanguageResolver languageResolver;

    @Operation(summary = "Get the list of all the different types")
    @GetMapping
    public List<Type> list(HttpServletRequest request) {
        return typeService.list(languageResolver.resolveLocale(request).getLanguage());
    }

}

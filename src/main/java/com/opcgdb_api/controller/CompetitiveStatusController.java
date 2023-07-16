package com.opcgdb_api.controller;

import com.opcgdb_api.config.LanguageResolver;
import com.opcgdb_api.dto.CompetitiveStatus;
import com.opcgdb_api.service.CompetitiveStatusService;
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
@RequestMapping("/competitive-status")
@RequiredArgsConstructor
public class CompetitiveStatusController {

    private final CompetitiveStatusService competitiveStatusService;

    private final LanguageResolver languageResolver;

    @Operation(summary = "Get the list of all the different competitive status")
    @GetMapping
    public List<CompetitiveStatus> list(HttpServletRequest request) {
        return competitiveStatusService.list(languageResolver.resolveLocale(request).getLanguage());
    }

}

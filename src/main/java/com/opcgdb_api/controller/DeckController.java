package com.opcgdb_api.controller;

import com.opcgdb_api.config.LanguageResolver;
import com.opcgdb_api.config.UserResolver;
import com.opcgdb_api.dto.Deck;
import com.opcgdb_api.dto.User;
import com.opcgdb_api.service.DeckService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/decks")
@RequiredArgsConstructor
public class DeckController {

    private final UserResolver userResolver;

    private final LanguageResolver languageResolver;

    private final DeckService deckService;

    @Operation(summary = "List decks matching filters")
    @GetMapping
    public Page<Deck> list(
            @PageableDefault(size = 25)
            @SortDefault(sort = "creationDate", direction = Sort.Direction.DESC)
                    Pageable pageable,
            @RequestParam(required = false)
            @Parameter(description = "Mail of the creator of the deck")
                    String mail,
            HttpServletRequest request) {
        return deckService.list(
                pageable, mail, languageResolver.resolveLocale(request).getLanguage()
        );
    }

    @Operation(summary = "Read the deck with the ID in the path")
    @GetMapping("{id}")
    public Deck read(
            @Parameter(description = "ID of the deck")
            @PathVariable UUID id,
            HttpServletRequest request) throws ResponseStatusException {
        return deckService.read(id, languageResolver.resolveLocale(request).getLanguage());
    }


    @Operation(summary = "Create a deck for the User Authenticated")
    @PostMapping
    public Deck create(@RequestBody Deck deck, HttpServletRequest request) throws ResponseStatusException {
        User connectedUser = userResolver.resolveUserFromRequest(request);
        if (connectedUser == null || connectedUser.getMail() == null
                || connectedUser.getMail().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token invalid");
        }
        deck.setUser(connectedUser);
        return deckService.create(deck, languageResolver.resolveLocale(request).getLanguage());
    }

}

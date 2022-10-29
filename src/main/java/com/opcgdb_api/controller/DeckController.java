package com.opcgdb_api.controller;

import com.opcgdb_api.config.LanguageResolver;
import com.opcgdb_api.config.UserResolver;
import com.opcgdb_api.dto.Deck;
import com.opcgdb_api.dto.User;
import com.opcgdb_api.service.DeckService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.InvalidParameterException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/decks")
@RequiredArgsConstructor
public class DeckController {

    private final UserResolver userResolver;

    private final LanguageResolver languageResolver;

    private final DeckService deckService;

    @Operation(summary = "Create a deck for the User Authenticated")
    @PostMapping
    public Deck create(@RequestBody Deck deck, HttpServletRequest request) throws InvalidParameterException {
        User connectedUser = userResolver.resolveUserFromRequest(request);
        if (connectedUser == null || connectedUser.getMail() == null
                || connectedUser.getMail().isEmpty()) {
            throw new InvalidParameterException("Token invalid");
        }
        deck.setUser(connectedUser);
        return deckService.create(deck, languageResolver.resolveLocale(request).getLanguage());
    }

}

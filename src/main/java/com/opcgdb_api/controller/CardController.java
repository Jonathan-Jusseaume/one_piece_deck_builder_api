package com.opcgdb_api.controller;

import com.opcgdb_api.dto.Card;
import com.opcgdb_api.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping
    public List<Card> list(@RequestParam(name = "language", defaultValue = "en") String languageCode) {
        return cardService.list(languageCode);
    }

}

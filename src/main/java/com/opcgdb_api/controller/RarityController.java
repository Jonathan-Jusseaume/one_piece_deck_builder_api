package com.opcgdb_api.controller;

import com.opcgdb_api.dto.Attribute;
import com.opcgdb_api.dto.Rarity;
import com.opcgdb_api.service.AttributeService;
import com.opcgdb_api.service.RarityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/rarities")
@RequiredArgsConstructor
public class RarityController {

    private final RarityService rarityService;

    @GetMapping
    public List<Rarity> list() {
        return rarityService.list();
    }

}

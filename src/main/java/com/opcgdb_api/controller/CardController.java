package com.opcgdb_api.controller;

import com.opcgdb_api.config.LanguageResolver;
import com.opcgdb_api.dto.Card;
import com.opcgdb_api.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    private final LanguageResolver languageResolver;

    @Operation(summary = "Get a page of the card list matching different criteria")
    @GetMapping
    public Page<Card> list(
            @PageableDefault(size = 25)
            @SortDefault(sort = "id", direction = Sort.Direction.ASC)
                    Pageable pageable,
            @RequestParam(required = false, name = "typeId")
            @Parameter(name = "typeId",
                    description = "Type Id of the card. You can put multiple values")
                    Set<Long> typesId,
            @RequestParam(required = false, name = "colorId")
            @Parameter(name = "colorId",
                    description = "Color Id of the card. You can put multiple values")
                    Set<Long> colorId,
            @RequestParam(required = false, name = "tagId")
            @Parameter(name = "tagId",
                    description = "Tag Id of the card. You can put multiple values")
                    Set<Long> tagsId,
            @RequestParam(required = false, name = "rarityId")
            @Parameter(name = "rarityId",
                    description = "Rarity Id of the card. You can put multiple values")
                    Set<Long> raritiesId,
            @RequestParam(required = false, name = "productId")
            @Parameter(name = "productId",
                    description = "Product Id of the card. You can put multiple values")
                    Set<String> productsId,
            @RequestParam(required = false, name = "cost")
            @Parameter(name = "costs",
                    description = "Cost value of the card. You can put multiple values")
                    Set<Integer> costs,
            @RequestParam(required = false, name = "power")
            @Parameter(name = "power",
                    description = "Power value of the card. You can put multiple values")
                    Set<Integer> powers,
            @RequestParam(required = false)
            @Parameter(name = "keyword",
                    description = "Keywords which are in the card name or the card description. You can prefix them with \"!\" " +
                            "in order to search cards which don't have this word.")
                    String keyword,
            HttpServletRequest request) {
        return cardService.list(pageable,
                typesId,
                colorId,
                tagsId,
                raritiesId,
                productsId,
                costs,
                powers,
                keyword,
                languageResolver.resolveLocale(request).getLanguage());
    }


}

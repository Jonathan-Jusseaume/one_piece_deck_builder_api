package com.opcgdb_api.controller;

import com.opcgdb_api.dto.Card;
import com.opcgdb_api.model.CardFilter;
import com.opcgdb_api.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/card/image")
@RequiredArgsConstructor
public class ImageController {

    @GetMapping(value = "{name}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] read(@PathVariable String name) throws IOException {
        ClassPathResource imgFile = new ClassPathResource("static/card/" + name);
        return StreamUtils.copyToByteArray(imgFile.getInputStream());
    }



}

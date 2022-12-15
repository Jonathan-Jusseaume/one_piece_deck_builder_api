package com.opcgdb_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cards/image")
@RequiredArgsConstructor
public class ImageController {

    @Operation(summary = "Get the image which has the name in the path")
    @GetMapping(value = "{name}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] read(@Parameter(description = "Name of the image")
                       @PathVariable String name) {
        ClassPathResource imgFile = new ClassPathResource("static/card/" + name);
        try {
            return StreamUtils.copyToByteArray(imgFile.getInputStream());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image with this name not found");
        }
    }


}

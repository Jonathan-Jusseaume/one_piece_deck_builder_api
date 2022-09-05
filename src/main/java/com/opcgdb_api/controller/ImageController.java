package com.opcgdb_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/cards/image")
@RequiredArgsConstructor
public class ImageController {

    @Operation(summary = "Get the image which has the name in the path")
    @GetMapping(value = "{name}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] read(@Parameter(description = "Name of the image")
                       @PathVariable String name) throws IOException {
        ClassPathResource imgFile = new ClassPathResource("static/card/" + name);
        return StreamUtils.copyToByteArray(imgFile.getInputStream());
    }


}

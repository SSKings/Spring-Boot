package com.sskings.text_to_speec.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sskings.text_to_speec.service.PortableNarrationService;

@RestController
@RequestMapping("/api/tts")
public class TextToSpeechController {

    private final PortableNarrationService portableNarrationService;
   
    public TextToSpeechController(PortableNarrationService portableNarrationService) {
        this.portableNarrationService = portableNarrationService;
    }

    @PostMapping(value = "/synthesize", produces = "audio/mpeg")
    public ResponseEntity<byte[]> synthesize(@RequestBody SynthesisRequest request) {
        byte[] audio = portableNarrationService.createPortableNarration(request.text());
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType("audio/mpeg"))
            .header("Content-Disposition", "attachment; filename=\"speech.mp3\"")
            .body(audio);
    }

    record SynthesisRequest(String text) {}
}
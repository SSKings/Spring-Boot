package com.sskings.text_to_speec.service;

import org.springframework.ai.audio.tts.TextToSpeechModel;
import org.springframework.ai.audio.tts.TextToSpeechOptions;
import org.springframework.ai.audio.tts.TextToSpeechPrompt;
import org.springframework.ai.audio.tts.TextToSpeechResponse;
import org.springframework.stereotype.Service;

@Service
public class PortableNarrationService {

    private final TextToSpeechModel textToSpeechModel;

    public PortableNarrationService(TextToSpeechModel textToSpeechModel) {
        this.textToSpeechModel = textToSpeechModel;
    }

    public byte[] createPortableNarration(String text) {

        TextToSpeechOptions options = TextToSpeechOptions.builder()
            .voice("alloy")
            .build();

        TextToSpeechPrompt prompt = new TextToSpeechPrompt(text, options);

        TextToSpeechResponse response = textToSpeechModel.call(prompt);

        return response.getResult().getOutput();
    }
}


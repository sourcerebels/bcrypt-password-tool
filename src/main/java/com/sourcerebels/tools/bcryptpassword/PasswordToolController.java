package com.sourcerebels.tools.bcryptpassword;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PasswordToolController {

    private static Logger logger = LoggerFactory.getLogger(PasswordToolController.class);

    private final PasswordEncoder passwordEncoder;

    public PasswordToolController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/encode")
    public String encode(@RequestParam String textToEncode, ModelMap modelMap) {
        logger.info("encode. plain: {}", textToEncode);
        String encodedText = passwordEncoder.encode(textToEncode);
        modelMap.put("textToEncode", textToEncode);
        modelMap.put("encodedText", encodedText);
        logger.info("encode. encodedText: {}", encodedText);
        return "index";
    }

    @PostMapping("/decode")
    public String decode(@RequestParam String encodedText, @RequestParam String rawText, ModelMap modelMap) {
        logger.info("decode. encoded: {} plain: {}", encodedText, rawText);
        boolean matches = passwordEncoder.matches(rawText, encodedText);
        modelMap.put("encodedText", encodedText);
        modelMap.put("rawText", rawText);
        modelMap.put("matches", matches);
        logger.info("decode. matches: {}", matches);
        return "index";
    }
}

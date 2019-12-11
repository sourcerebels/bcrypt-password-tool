package com.sourcerebels.tools.bcryptpassword;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.NoSuchAlgorithmException;

@Controller
public class PasswordToolController {

    private static final String BCRYPT = "bcrypt";

    private static Logger logger = LoggerFactory.getLogger(PasswordToolController.class);

    private final PasswordEncoder passwordEncoder;

    public PasswordToolController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String index(ModelMap modelMap) {
        logger.info("index");
        modelMap.put("encodingAlgorithm", BCRYPT);
        modelMap.put("decodingAlgorithm", BCRYPT);
        return "index";
    }

    @PostMapping("/encode")
    public String encode(@RequestParam String textToEncode, @RequestParam String encodingAlgorithm, ModelMap modelMap)
        throws NoSuchAlgorithmException {
        logger.info("encode. plain: {} algorithm: {}", textToEncode, encodingAlgorithm);
        String encodedText = BCRYPT.equals(encodingAlgorithm) ?
            bcryptEncode(textToEncode) :
            shaEncode(textToEncode, encodingAlgorithm);
        modelMap.put("encodingAlgorithm", encodingAlgorithm);
        modelMap.put("decodingAlgorithm", encodingAlgorithm);
        modelMap.put("textToEncode", textToEncode);
        modelMap.put("encodedText", encodedText);
        logger.info("encode. encodedText: {}", encodedText);
        return "index";
    }

    @PostMapping("/decode")
    public String decode(@RequestParam String encodedText, @RequestParam String rawText, @RequestParam String decodingAlgorithm, ModelMap modelMap)
        throws NoSuchAlgorithmException {
        logger.info("decode. encoded: {} plain: {} algorithm: {}", encodedText, rawText, decodingAlgorithm);
        boolean matches = BCRYPT.equals(decodingAlgorithm) ?
            bcryptMatches(encodedText, rawText) :
            shaMatches(encodedText, rawText, decodingAlgorithm);
        modelMap.put("encodingAlgorithm", decodingAlgorithm);
        modelMap.put("decodingAlgorithm", decodingAlgorithm);
        modelMap.put("encodedText", encodedText);
        modelMap.put("rawText", rawText);
        modelMap.put("matches", matches);
        logger.info("decode. matches: {}", matches);
        return "index";
    }


    private String shaEncode(String textToEncode, String algorithm) throws NoSuchAlgorithmException {
        return ShaUtility.make(textToEncode, algorithm);
    }

    private boolean shaMatches(String encodedText, String rawText, String algorithm) throws NoSuchAlgorithmException {
        return ShaUtility.make(rawText, algorithm).equals(encodedText);
    }


    private String bcryptEncode(String textToEncode) {
        return passwordEncoder.encode(textToEncode);
    }

    private boolean bcryptMatches(String encodedText, String rawText) {
        return passwordEncoder.matches(rawText, encodedText);
    }

}

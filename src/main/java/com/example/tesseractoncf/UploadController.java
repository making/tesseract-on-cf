package com.example.tesseractoncf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
    private final Ocr ocr;

    public UploadController(Ocr ocr) {
        this.ocr = ocr;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/")
    public String ocr(@RequestParam MultipartFile file, Model model) {
        String string = this.ocr.read(file);
        model.addAttribute("string", string);
        return "index";
    }
}

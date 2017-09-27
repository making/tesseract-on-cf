package com.example.tesseractoncf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
public class UploadController {
    private final TmpFile tmpFile;
    private final Ocr ocr;

    public UploadController(TmpFile tmpFile, Ocr ocr) {
        this.tmpFile = tmpFile;
        this.ocr = ocr;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/")
    public String ocr(@RequestParam MultipartFile file, Model model) {
        File tmp = this.tmpFile.write(file).asFile();
        String string = this.ocr.read(tmp);
        model.addAttribute("string", string);
        return "index";
    }
}

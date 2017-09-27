package com.example.tesseractoncf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;

@Component
@RequestScope
public class TmpFile {
    private static final Logger log = LoggerFactory.getLogger(TmpFile.class);
    private final File file;

    public TmpFile() {
        try {
            this.file = File.createTempFile("tesseract-", ".tmp");
            log.debug("Created {}.", this.file);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }


    public TmpFile write(MultipartFile multipartFile) {
        try {
            multipartFile.transferTo(this.file);
            return this;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public File asFile() {
        return this.file;
    }

    @PreDestroy
    void cleanup() {
        if (this.file.delete()) {
            log.debug("Deleted {}.", this.file);
        } else {
            log.debug("Failed to delete {}.", this.file);
        }
    }


}

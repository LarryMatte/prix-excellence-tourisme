package com.arcbees.bourseje.server.services;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.google.common.io.Files;

public class FilesService {
    private static final String CODES_FILE = "codes.txt";

    private List<String> voteCodes;

    public List<String> getVoteCodes() {
        if (voteCodes == null) {
            try {
                File f = new File(getClass().getClassLoader().getResource(CODES_FILE).toURI());
                voteCodes = Files.readLines(f, Charset.defaultCharset());
            } catch (URISyntaxException | IOException | NullPointerException e) {
                voteCodes = new ArrayList<>();
            }
        }

        return voteCodes;
    }
}

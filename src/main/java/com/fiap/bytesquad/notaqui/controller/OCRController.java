package com.fiap.bytesquad.notaqui.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fiap.bytesquad.notaqui.model.dto.request.OCRRequestDTO;
import com.fiap.bytesquad.notaqui.model.dto.response.OCRResponseDTO;
import com.fiap.bytesquad.notaqui.service.OCRService;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

@RestController
@RequestMapping("/ocr")
@CrossOrigin("*")
@Slf4j
public class OCRController {

    @Autowired private OCRService service;

    @PostMapping("/identificar")
    public ResponseEntity<OCRResponseDTO> identify(@RequestBody OCRRequestDTO requestDTO) throws JsonProcessingException {
        log.info("|| Iniciando ocrController - identificar conteúdo");

        return new ResponseEntity<>(service.identify(requestDTO), HttpStatus.OK);
    }

    @PostMapping("/identificarPdf")
    public ResponseEntity<OCRResponseDTO> identifyPdf(@RequestBody OCRRequestDTO requestDTO) throws JsonProcessingException {
        log.info("|| Iniciando ocrController - identificar conteúdo do PDF");

        byte[] pdfBytes = Base64.getDecoder().decode(requestDTO.getContent());

        try {
            PDDocument document = PDDocument.load(pdfBytes);

            PDFRenderer renderer = new PDFRenderer(document);
            BufferedImage image = renderer.renderImageWithDPI(0, 300);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "png", outputStream);
            byte[] pngBytes = outputStream.toByteArray();

            String base64PNG = Base64.getEncoder().encodeToString(pngBytes);

            OCRRequestDTO ocrRequestDTO = OCRRequestDTO.builder().content(base64PNG).build();

            log.info("|| Foi bao!");

            return new ResponseEntity<>(service.identify(ocrRequestDTO), HttpStatus.OK);
        } catch (Exception e) {
            log.info("|| Deu erro: {}", e.getMessage());
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}

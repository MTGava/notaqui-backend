package com.fiap.bytesquad.notaqui.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OCRRequestDTO implements Serializable {
    private static final long serialVersionUID = -1L;

    @JsonProperty("conteudo")
    private String content;
}

package com.fiap.bytesquad.notaqui.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.bytesquad.notaqui.model.dto.response.cnpja.CNPJResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OCRResponseDTO implements Serializable {
    private static final long serialVersionUID = -1L;

    @JsonProperty("valor")
    private BigDecimal value;

    @JsonProperty("infoPj")
    private CNPJResponseDTO cnpjResponseDTO;
}

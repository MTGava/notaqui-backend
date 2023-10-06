package com.fiap.bytesquad.notaqui.model.dto.response.ibge;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.bytesquad.notaqui.model.dto.response.cnpja.CNPJaNatureResponseDTO;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class IbgeCnaeResponseDTO implements Serializable {
    private static final long serialVersionUID = -1L;

    @JsonProperty("descricao")
    private String description;
}

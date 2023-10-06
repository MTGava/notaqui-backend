package com.fiap.bytesquad.notaqui.model.dto.response.cnpja;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class CNPJaMainActivityResponseDTO implements Serializable {
    private static final long serialVersionUID = -1L;

    private Integer id;
    private String text;
}

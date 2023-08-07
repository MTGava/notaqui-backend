package com.fiap.bytesquad.notaqui.model.dto.response.cnpja;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CNPJResponseDTO implements Serializable {
    private static final long serialVersionUID = -1L;

    @JsonProperty("cnpj")
    private String cnpj;

    @JsonProperty("razaoSocial")
    private String corporateName;

    @JsonProperty("naturezaJuridica")
    private String legalNature;

    @JsonProperty("tipoEmpresa")
    private String corporateType;
}

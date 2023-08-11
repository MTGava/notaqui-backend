package com.fiap.bytesquad.notaqui.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.bytesquad.notaqui.model.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO implements Serializable {
    private static final long serialVersionUID = -1L;

    @CNPJ
    @Size(max = 20, message = "Limite máximo de caracteres ultrapassado")
    @NotNull(message = "CNPJ precisa ser preenchido!")
    @JsonProperty(value = "cnpj")
    private String cnpj;


    @Size(max = 100, message = "Limite máximo de caracteres ultrapassado")
    @NotNull(message = "Razão social precisa ser preenchida!")
    @JsonProperty(value = "razaoSocial")
    private String corporateName;

    @Size(max = 100, message = "Limite máximo de caracteres ultrapassado")
    @NotNull(message = "Natureza jurídica precisa ser preenchida!")
    @JsonProperty("naturezaJuridica")
    private String legalNature;

    @Size(max = 400, message = "Limite máximo de caracteres ultrapassado")
    @NotNull(message = "Tipo de empresa precisa ser preenchida!")
    @JsonProperty("tipoEmpresa")
    private String corporateType;

    public CompanyDTO(Company company) {
        this.cnpj = company.getCnpj();
        this.corporateName = company.getCorporateName();
        this.legalNature = company.getLegalNature();
        this.corporateType = company.getCorporateType();
    }
}

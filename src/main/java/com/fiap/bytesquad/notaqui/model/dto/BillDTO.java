package com.fiap.bytesquad.notaqui.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.bytesquad.notaqui.model.Bill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillDTO implements Serializable {
    private static final long serialVersionUID = -1L;

    @CNPJ
    @NotNull(message = "CNPJ precisa ser preenchido!")
    @JsonProperty("cnpj")
    private String cnpj;

    @JsonProperty("valor")
    @NotNull(message = "Valor precisa ser preenchido!")
    private BigDecimal value;

    @NotNull(message = "Chave de acesso precisa ser preenchida!")
    @JsonProperty("chaveAcesso")
    private String accessKey;


    @Size(max = 10, message = "Limite máximo de caracteres ultrapassado")
    @NotNull(message = "Matrícula precisa ser preenchida!")
    @JsonProperty("matricula")
    private String login;

    @JsonProperty("anexo")
    private AttatchmentDTO attatchment;

    public BillDTO(Bill bill) {
        AttatchmentDTO attatchmentDTO = new AttatchmentDTO();
        attatchmentDTO.setArchive(bill.getArchive());
        attatchmentDTO.setExtension(bill.getExtension());

        this.cnpj = bill.getCnpj();
        this.value = bill.getValue();
        this.accessKey = bill.getAccessKey();
        this.attatchment = attatchmentDTO;
        this.login = bill.getUser().getLogin();
    }
}

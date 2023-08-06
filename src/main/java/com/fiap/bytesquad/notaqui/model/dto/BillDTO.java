package com.fiap.bytesquad.notaqui.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.bytesquad.notaqui.model.Bill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

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
    @JsonProperty("cnpj")
    private String cnpj;

    @JsonProperty("valor")
    private BigDecimal value;

    @JsonProperty("chaveAcesso")
    private Integer accessKey;


    @JsonProperty("anexo")
    private AttatchmentDTO attatchment;

//    public BillDTO(Bill bill) {
//        this.cnpj = bill.getCnpj();
//        this.value = bill.getValue();
//        this.accessKey = bill.getAccessKey();
//    }
}

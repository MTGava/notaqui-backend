package com.fiap.bytesquad.notaqui.model.dto;

import com.fiap.bytesquad.notaqui.model.Bill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillDTO implements Serializable {
    private static final long serialVersionUID = -1L;

    private String cnpj;

    private BigDecimal value;

    private Integer accessKey;

    public BillDTO(Bill bill) {
        this.cnpj = bill.getCnpj();
        this.value = bill.getValue();
        this.accessKey = bill.getAccessKey();
    }
}

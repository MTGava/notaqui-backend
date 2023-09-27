package com.fiap.bytesquad.notaqui.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.bytesquad.notaqui.model.Qwerty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QwertyDTO implements Serializable {
    private static final long serialVersionUID = -1L;

    @JsonProperty("idAnexo")
    private Integer id;

    @JsonProperty("nome")
    private String name;

    @JsonProperty("conteudo")
    private String content;

    @JsonProperty("dataEntrada")
    private Date date;

    public QwertyDTO(Qwerty qwerty) {
        this.id = qwerty.getId();
        this.content = qwerty.getContent();
        this.date = qwerty.getDate();
        this.name = qwerty.getName();
    }
}

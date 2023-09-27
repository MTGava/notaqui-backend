package com.fiap.bytesquad.notaqui.model.dto.request.vision;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VisionRequestDTO implements Serializable {
    private static final long serialVersionUID = -1L;

    private List<RequestObjectDTO> requests;
}

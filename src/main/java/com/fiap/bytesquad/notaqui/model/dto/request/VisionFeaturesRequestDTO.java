package com.fiap.bytesquad.notaqui.model.dto.request;

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
public class VisionFeaturesRequestDTO implements Serializable {
    private static final long serialVersionUID = -1L;

    private String type = "TEXT_DETECTION";
}

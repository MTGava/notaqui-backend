package com.fiap.bytesquad.notaqui.model.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fiap.bytesquad.notaqui.model.dto.request.VisionFeaturesRequestDTO;
import com.fiap.bytesquad.notaqui.model.dto.request.VisionImageRequestDTO;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseObjectDTO implements Serializable {
    private static final long serialVersionUID = -1L;

    private VisionTextAnnotationResponseDTO fullTextAnnotation;
}

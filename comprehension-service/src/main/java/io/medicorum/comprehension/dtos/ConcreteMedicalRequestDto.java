package io.medicorum.comprehension.dtos;

import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConcreteMedicalRequestDto {

    private String patientId;
    private String doctorId;
    private String discussionId;
    private String messageText;
    private Boolean allowVisibility;

}

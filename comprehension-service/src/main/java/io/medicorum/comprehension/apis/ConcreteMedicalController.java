package io.medicorum.comprehension.apis;

import io.medicorum.comprehension.dtos.ConcreteMedicalRequestDto;
import io.medicorum.comprehension.dtos.ConcreteMedicalResponseDto;
import io.medicorum.comprehension.mappers.ConcreteMedicalDataMapper;
import io.medicorum.comprehension.services.ConcreteMedicalDataService;
import io.medicorum.comprehension.services.MedicalInformationsExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/medicaldata")
public class ConcreteMedicalController {
    @Autowired
    MedicalInformationsExtractor medicalInformationsExtractor;
    @Autowired
    ConcreteMedicalDataService concreteMedicalDataService;
    @PostMapping(value = "/concretedata", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ConcreteMedicalResponseDto extractData(@RequestBody ConcreteMedicalRequestDto concreteMedicalRequestDto) {
        medicalInformationsExtractor.createNewComprehensionContext(concreteMedicalRequestDto);
        return ConcreteMedicalDataMapper.mapToConcreteMedicalDataDto(concreteMedicalDataService.retreiveMedicalData(concreteMedicalRequestDto.getDiscussionId()));
    }
}

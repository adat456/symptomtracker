package symptomtracker.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import symptomtracker.dtos.CreateInstanceDTO;
import symptomtracker.entities.Feature;
import symptomtracker.entities.Instance;
import symptomtracker.entities.Symptom;
import symptomtracker.repos.InstanceRepo;
import symptomtracker.repos.SymptomRepo;

@Service
@RequiredArgsConstructor
public class InstanceService {
    private final SymptomRepo symptomRepo;
    private final InstanceRepo instanceRepo;

//    public Instance create(CreateInstanceDTO d) {
//        Symptom symptom = symptomRepo.findById(d.getSymptomId()).orElseThrow(() -> new EntityNotFoundException("Unable to find symptom"));
//        for (Feature feature : symptom.getFeatures()) {
//
//        }
//    }
 }

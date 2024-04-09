package symptomtracker.entities;

import lombok.Getter;

@Getter
public enum FeatureType {
    SCALE, BOOL, TIME, CHECKBOX, RADIOBTN, FREETEXT;
    // ["1","2","3"]
    // ["Y","N"]
    // ["22:30"]
    // ["Scaly", "Awful"]
    // ["Scaly"]
    // [""]
}

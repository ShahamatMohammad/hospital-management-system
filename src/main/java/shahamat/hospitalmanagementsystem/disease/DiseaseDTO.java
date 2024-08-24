package shahamat.hospitalmanagementsystem.disease;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DiseaseDTO {
    private String name;
    private String description;
    private String severity;
    private List<Long> patientIds;
}

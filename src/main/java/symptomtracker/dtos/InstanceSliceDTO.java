// encapsulates all Instance instances in the current slice
// as well as info needed by the client to obtain the next slice (if it exists)

package symptomtracker.dtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import symptomtracker.entities.Instance;

import java.util.List;

@Data
@RequiredArgsConstructor
public class InstanceSliceDTO {
    private final List<Instance> instances;
    private final Integer nextPageNum;
    private final Integer pageSize;
    private final String sort;
}

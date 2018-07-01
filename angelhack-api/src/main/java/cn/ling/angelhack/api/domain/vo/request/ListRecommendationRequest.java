package cn.ling.angelhack.api.domain.vo.request;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class ListRecommendationRequest implements Serializable {

    private List<Integer> ids;


}

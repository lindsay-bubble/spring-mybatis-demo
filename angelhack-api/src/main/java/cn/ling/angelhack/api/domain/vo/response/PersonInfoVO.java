package cn.ling.angelhack.api.domain.vo.response;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class PersonInfoVO implements Serializable {

    private Integer id;

    private String picPath;

    private List<Integer> scores;


}

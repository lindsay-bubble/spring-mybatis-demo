package cn.ling.angelhack.api.domain.vo.request;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class InsertPersonInfoRequest implements Serializable {

    private Integer id;

    private String picContent;

    private List<Integer> scores;


}

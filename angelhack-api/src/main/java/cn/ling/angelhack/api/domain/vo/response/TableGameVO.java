package cn.ling.angelhack.api.domain.vo.response;

import java.io.Serializable;
import lombok.Data;

@Data
public class TableGameVO implements Serializable {

    private Integer id;

    private String name;

    private String desc;

    private String picUrl;

    private Integer diff;


}

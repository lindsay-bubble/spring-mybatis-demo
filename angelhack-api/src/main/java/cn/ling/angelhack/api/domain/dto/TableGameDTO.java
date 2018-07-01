package cn.ling.angelhack.api.domain.dto;

import java.util.List;
import lombok.Data;

@Data
public class TableGameDTO {

    private Integer id;

    private String name;

    private String desc;

    private String picUrl;

    private Integer minNum;

    private Integer maxNum;

    private List<Integer> scores;
}

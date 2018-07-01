package cn.ling.angelhack.api.service;

import cn.ling.angelhack.api.common.JsonUtils;
import cn.ling.angelhack.api.domain.dto.TableGameDTO;
import cn.ling.angelhack.api.domain.model.PersonInfo;
import cn.ling.angelhack.api.domain.model.PersonInfoExample;
import cn.ling.angelhack.api.domain.model.TableGame;
import cn.ling.angelhack.api.domain.vo.request.InsertPersonInfoRequest;
import cn.ling.angelhack.api.domain.vo.response.PersonInfoVO;
import cn.ling.angelhack.api.domain.vo.response.TableGameVO;
import cn.ling.angelhack.api.mapper.PersonInfoMapper;
import cn.ling.angelhack.api.mapper.TableGameMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TableGameService {

    @Autowired
    private PersonInfoMapper personInfoMapper;

    @Autowired
    private TableGameMapper tableGameMapper;

    @Autowired
    private UploadService uploadService;

    private List<TableGameDTO> tableGames;

    private PersonInfoVO toPersonInfoVO(PersonInfo in) {
        PersonInfoVO out = new PersonInfoVO();
        out.setId(in.getId());
        out.setScores(JsonUtils.fromJson(in.getScores(), new TypeReference<List<Integer>>() {
        }));
        out.setPicPath(in.getPicPath());
        return out;
    }

    public void save(InsertPersonInfoRequest in) {
        // 保存图片
        String picPath = uploadService.savePic(in.getPicContent());
        if (picPath == null) {
            throw new RuntimeException("failed to save pic");
        }
        PersonInfo info = toPersonInfo(in, picPath);
        personInfoMapper.insert(info);
    }

    public List<PersonInfoVO> listAllPerson() {
        List<PersonInfo> origins = personInfoMapper.selectAll();
        return origins.stream().map(this::toPersonInfoVO).collect(Collectors.toList());
    }

    private PersonInfo toPersonInfo(InsertPersonInfoRequest in, String picPath) {
        if (in == null) {
            return null;
        }
        PersonInfo out = new PersonInfo();
        out.setId(in.getId());
        out.setPicPath(picPath);
        out.setScores(JsonUtils.toJson(in.getScores()));
        return out;
    }


    @Autowired
    private void init() {

        List<TableGame> origins = tableGameMapper.selectAll();//todo
        tableGames = origins.stream().map(this::toTableGameDTO).collect(Collectors.toList());
    }

    private TableGameDTO toTableGameDTO(TableGame in) {
        TableGameDTO out = new TableGameDTO();
        out.setId(in.getId());
        out.setName(in.getName());
        out.setDesc(in.getDesc());
        out.setPicUrl(in.getPicUrl());
        out.setMinNum(in.getMinNum());
        out.setMaxNum(in.getMaxNum());
        out.setScores(JsonUtils.fromJson(in.getScores(), new TypeReference<List<Integer>>() {
        }));
        return out;
    }


    public List<TableGameVO> listTableGame(List<Integer> ids) {
        PersonInfoExample example = new PersonInfoExample();
        example.createCriteria().andIdIn(ids);
        List<PersonInfo> origins = personInfoMapper.selectByExample(example);

        List<PersonInfoVO> persons = origins.stream().map(this::toPersonInfoVO).collect(Collectors.toList());
        int size = ids.size();
        List<TableGameDTO> filteredGames = tableGames.stream()
                .filter(game -> size >= game.getMinNum() && size <= game.getMaxNum()).collect(Collectors.toList());
        List<TableGameVO> ret = new ArrayList<>();
        for (TableGameDTO game : filteredGames) {
            int diff = 0;
            List<Integer> gameScores = game.getScores();
            for (PersonInfoVO person : persons) {
                List<Integer> personScores = person.getScores();
                int scoreSize = Math.min(gameScores.size(), personScores.size());
                Integer dev = 0;
                for (int i = 0; i < scoreSize; i++) {
                    Integer curDev = (gameScores.get(i) - personScores.get(i)) * (gameScores.get(i) - personScores.get(i));
                    dev += curDev;
                }
                diff += (int) Math.round(Math.sqrt(dev));
            }
            TableGameVO item = new TableGameVO();
            item.setId(game.getId());
            item.setDesc(game.getDesc());
            item.setName(game.getName());
            item.setPicUrl(game.getPicUrl());
            item.setDiff(diff);
            ret.add(item);
        }
        return ret.stream().sorted(Comparator.comparing(TableGameVO::getDiff)).limit(5).collect(Collectors.toList());
    }
}

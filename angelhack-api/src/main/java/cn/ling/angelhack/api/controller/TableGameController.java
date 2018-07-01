package cn.ling.angelhack.api.controller;

import cn.ling.angelhack.api.domain.vo.request.InsertPersonInfoRequest;
import cn.ling.angelhack.api.domain.vo.request.ListRecommendationRequest;
import cn.ling.angelhack.api.domain.vo.response.BaseResponse;
import cn.ling.angelhack.api.domain.vo.response.PersonInfoVO;
import cn.ling.angelhack.api.domain.vo.response.TableGameVO;
import cn.ling.angelhack.api.service.TableGameService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/angelhack/table/game")
@Slf4j
public class TableGameController {

    @Autowired
    private TableGameService tableGameService;

    @RequestMapping(value = "/person", method = RequestMethod.POST)
    public BaseResponse insert(@RequestBody InsertPersonInfoRequest request) {
        log.info("insert request {}", request);
        tableGameService.save(request);
        return BaseResponse.newSuccResponse().build();
    }

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public BaseResponse listAllPerson() {
        log.info("listAllPerson request");
        List<PersonInfoVO> ret = tableGameService.listAllPerson();
        return BaseResponse.newSuccResponse().result(ret).build();
    }

    @RequestMapping(value = "/recommendation", method = RequestMethod.POST)
    public BaseResponse listRecommendation(ListRecommendationRequest request) {
        log.info("listRecommendation request", request);
        List<TableGameVO> ret = tableGameService.listTableGame(request.getIds());
        return BaseResponse.newSuccResponse().result(ret).build();
    }


}

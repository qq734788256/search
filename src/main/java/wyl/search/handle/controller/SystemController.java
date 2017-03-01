package wyl.search.handle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wyl.search.handle.result.BaseResult;

/**
 * Created by dfsj0317 on 2017/3/1.
 */
@Controller
public class SystemController {

    @RequestMapping("/error")
    @ResponseBody
    public BaseResult error(){
        BaseResult result = new BaseResult();
        result.setC(500400);
        result.setM("服务异常");
        return result;
    }
}

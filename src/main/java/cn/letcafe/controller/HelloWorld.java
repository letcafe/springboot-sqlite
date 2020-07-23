package cn.letcafe.controller;

import cn.letcafe.model.ReqBody;
import cn.letcafe.service.HelloService;
import cn.letcafe.model.HelloModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j(topic = "http")
@RestController
public class HelloWorld {
    private final HelloService helloService;

    @Autowired
    public HelloWorld(HelloService helloService) {
        this.helloService = helloService;

    }

    @RequestMapping("/")
    public String index() {
        return "Hello World";
    }

    @RequestMapping("/list")
    public List<HelloModel> list() {
        List<HelloModel> models = helloService.selectAll();
        logger.info("[models.size()] = " + models.size());
        return models;
    }

    @RequestMapping("/info/{id}")
    public HelloModel getById(@PathVariable(name = "id") Integer id) {
        return helloService.select(id);
    }


    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String post(@RequestBody ReqBody map) {
        String reqInput = "输入的姓名是" + map.getName() + ",电子邮件是:" + map.getEmail();
        helloService.insert(new HelloModel(map.getName(), map.getEmail()));
        return reqInput;
    }

    @RequestMapping("/yida/demo")
    public Map<String, Object> yidaDemo(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "companyName") String companyName,
            @RequestParam(name = "phone") String phone,
            @RequestParam(name = "loginId") String loginId
            ) {
        logger.info("yidaDemo called");
        logger.info("[param] name = {}, companyName = {}, phone = {}, loginId = {}",
                name, companyName, phone, loginId);
        Map<String, Object> res = new HashMap<>();
        res.put("success", true);
        return res;
    }
}

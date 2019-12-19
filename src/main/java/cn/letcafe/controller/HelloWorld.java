package cn.letcafe.controller;

import cn.letcafe.model.ReqBody;
import cn.letcafe.service.HelloService;
import cn.letcafe.model.HelloModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return helloService.selectAll();
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
}

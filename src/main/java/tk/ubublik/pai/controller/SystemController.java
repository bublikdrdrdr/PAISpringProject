package tk.ubublik.pai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.RequireJS;

@RestController("/system")
public class SystemController {

    @GetMapping("/time")
    public long getSystemTime(){
        return System.currentTimeMillis();
    }

    @ResponseBody
    @RequestMapping(value = "/webjarsjs", produces = "application/javascript")
    public String webjarjs() {
        return RequireJS.getSetupJavaScript("/webjars/");
    }
}

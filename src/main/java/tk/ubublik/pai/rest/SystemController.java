package tk.ubublik.pai.rest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.RequireJS;

import java.security.Principal;
import java.util.logging.Logger;

@RestController("/system")
public class SystemController {

    private static Logger logger = Logger.getLogger(SystemController.class.getName());

    @RequestMapping("/time")
    public long getSystemTime(){
        return System.currentTimeMillis();
    }

    @ResponseBody
    @RequestMapping(value = "/webjarsjs", produces = "application/javascript")
    public String webjarjs() {
        return RequireJS.getSetupJavaScript("/webjars/");
    }

    @RequestMapping("/credentials")
    public Object getCredentials(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Authentication info request" + authentication);
        return authentication.getPrincipal();
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }
}

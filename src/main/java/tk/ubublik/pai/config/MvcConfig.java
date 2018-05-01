package tk.ubublik.pai.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private static final String[] viewControllerArray = new String[]{
            "/index", "index",
            "/", "index",
            "/about", "about",
            "/login", "login",
            "/registration", "registration"
    };

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        for (int i = 0; i < viewControllerArray.length - 1; i += 2) {
            registry.addViewController(viewControllerArray[i]).setViewName(viewControllerArray[i + 1]);
        }
    }
}

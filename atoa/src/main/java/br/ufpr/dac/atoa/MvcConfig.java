package br.ufpr.dac.atoa;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/home").setViewName("pages/index");
        registry.addViewController("/gerente").setViewName("gerente/pages/index");
        registry.addViewController("/gerente/pages/atividade").setViewName("gerente/pages/atividade");
        registry.addViewController("/gerente/pages/tipoatividade").setViewName("gerente/pages/tipoatividade");
        registry.addViewController("/gerente/pages/fecharatividade").setViewName("gerente/pages/fecharatividade");
    }

}
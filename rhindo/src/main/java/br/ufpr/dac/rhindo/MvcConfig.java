package br.ufpr.dac.rhindo;

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
        registry.addViewController("/gerente/pages/funcionario").setViewName("gerente/pages/funcionario");
        registry.addViewController("/gerente/pages/departamento").setViewName("gerente/pages/departamento");
        registry.addViewController("/gerente/pages/cargo").setViewName("gerente/pages/cargo");
        registry.addViewController("/gerente/pages/perfilfuncionario").setViewName("gerente/pages/perfilfuncionario");
    }

}
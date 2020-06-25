package io.swagger.configuration;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.MimeMappings;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletCustomizer implements EmbeddedServletContainerCustomizer {



    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {

        MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
        mappings.add("js","application/javascript");
        mappings.add("css","text/css");
        container.setMimeMappings(mappings);
    }

}

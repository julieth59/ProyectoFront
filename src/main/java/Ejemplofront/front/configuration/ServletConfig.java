package Ejemplofront.front.configuration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import servlets.ParamServlet;

@Configuration
public class ServletConfig {
    /**
     * Define un Bean para registrar el Servlet.
     * @return ServletRegistrationBean con el Servlet y el patrón de URL.
     */
    @Bean
    public ServletRegistrationBean<ParamServlet> myServletRegistration() {
        ServletRegistrationBean<ParamServlet> registration =
                new ServletRegistrationBean<>(new ParamServlet(), "/api/mi-servlet/*");

        // Opcional: configurar la prioridad de carga
        registration.setLoadOnStartup(1);

        return registration;
    }

}

package Ejemplofront.front.vistas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeViewController {

    @GetMapping({"/", "/dashboard"})
    public String mostrarDashboard() {
        return "dashboard"; // -> templates/dashboard.html
    }
}

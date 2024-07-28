package pe.ahn.mdpicker;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import pe.ahn.mdpicker.model.md.MdData;
import pe.ahn.mdpicker.service.DataService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MdpickerApplication {

    public static void main(String[] args) { SpringApplication.run(MdpickerApplication.class, args); }

}

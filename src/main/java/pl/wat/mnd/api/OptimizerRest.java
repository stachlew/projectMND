package pl.wat.mnd.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.wat.mnd.logic.OptimizerService;

@RestController
@RequestMapping("/api")
public class OptimizerRest {

    @Autowired
    private OptimizerService optimizerService;

    @GetMapping("/test")
    @ResponseBody public String test(){
        return "OPTYMALIZATOR REST DZIAŁA";
    }
}

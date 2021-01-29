package com.example.transapp_back.a;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@RestController
public class TestController {
    @RequestMapping(value ="/test", method = {RequestMethod.POST})
    @ResponseBody
    public String plus(Model model, @RequestParam Map<String, String> requestParams,UriComponentsBuilder builder){
        try{
            String strLeft = requestParams.get("leftNumber");
            String strRight = requestParams.get("rightNumber");

            int left = Integer.parseInt(strLeft);
            int right =Integer.parseInt(strRight);

            int total = left + right;

            model.addAttribute(total);

        }catch (NumberFormatException | NullPointerException e1){
            e1.printStackTrace();
        }
        URI location = builder.path("/").build().toUri();
        return "redirect"+location.toString();
    }

}

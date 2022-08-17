package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import platform.model.Code;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import platform.service.CodeService;

import java.util.*;

@Controller
public class CodeController {
    @Autowired
    private CodeService codeService;

    @GetMapping(value = "/api/code/{uuid}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Code> getSnippet(@PathVariable UUID uuid){
        Code code = codeService.getCode(uuid);
        if (code.isTimeRestricted()) {
            codeService.updateTimeById(uuid);
            code = codeService.getCode(uuid);
        }

        if  (code.isViewRestricted()) {
            codeService.updateViewById(uuid);
            code = codeService.getCode(uuid);
        }
        return new ResponseEntity<>(code, HttpStatus.OK);
    }

    @GetMapping(value = "/code/{uuid}", produces = "text/html")
    public ModelAndView getHtmlCode(@PathVariable UUID uuid) {
        Map<String, String> model = new HashMap<>();
        Code code = codeService.getCode(uuid);
        model.put("code", code.getCode());
        model.put("date", code.getDate());

         if (code.isTimeRestricted()) {
             codeService.updateTimeById(uuid);
             code = codeService.getCode(uuid);
             model.put("time", String.valueOf(code.getTime()+7));
         }

         if (code.isViewRestricted()) {
             codeService.updateViewById(uuid);
             code = codeService.getCode(uuid);
             model.put("views", String.valueOf(code.getViews()));
         }

        return new ModelAndView("code", model);
    }

    @GetMapping(value = "/code/new", produces = "text/html")
    public ModelAndView getCodeForm() {
        ModelAndView modelAndView = new ModelAndView("codeForm");
        return modelAndView;
    }

    @PostMapping("/api/code/new")
    @ResponseBody
    public ResponseEntity<Map<String, String>> createNewCode(@RequestBody Code codeObject) {
        Code code = codeService.saveCode(codeObject);
        return new ResponseEntity<>(Map.of("id", code.getId().toString()), HttpStatus.OK);
    }

    @GetMapping(value = "/api/code/latest", produces = "application/json")
    @ResponseBody
    public List<Code> latestCodeSnippets() {
        List<Code> codeSnippets = codeService.getLatestSnippets();
        return codeSnippets;
    }

    @GetMapping(value = "/code/latest", produces = "text/html")
    public String latestCodeSnippetsWeb(Model model) {
        List<Code> codeSnippets = codeService.getLatestSnippets();
        model.addAttribute("codes", codeSnippets);
        return "latest";
    }
}

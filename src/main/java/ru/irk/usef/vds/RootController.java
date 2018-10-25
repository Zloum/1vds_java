package ru.irk.usef.vds;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class RootController {

    @GetMapping("/")
    public String root(Model model) {
        HashMap<String, Balance> balances = (HashMap<String, Balance>) VdsHelper.getSpareBalances();
        model.addAttribute("balances", balances);
        return "index";
    }
}

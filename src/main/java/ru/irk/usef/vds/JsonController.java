package ru.irk.usef.vds;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.irk.usef.vds.VdsHelper.getSpares;

@RestController
public class JsonController {
    @RequestMapping("/json")
    public HashMap<String, Integer> getRequests() {
        Map<String, Integer> balances = getSpares()
                .entrySet()
                .parallelStream()
                .filter(item -> item.getValue().getCount() + item.getValue().getArrive() < item.getValue().getMustbe())
                .collect(Collectors.toMap(Map.Entry::getKey, item -> item.getValue().getMustbe() - (item.getValue().getArrive() + item.getValue().getCount()) ));
        return (HashMap<String, Integer>) balances;
    }
}

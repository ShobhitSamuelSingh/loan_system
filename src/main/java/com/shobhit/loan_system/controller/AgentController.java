package com.shobhit.loan_system.controller;

import com.shobhit.loan_system.model.entity.Agent;
import com.shobhit.loan_system.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/v1/agent")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @PostMapping
    public void createAgent(@RequestBody Agent newAgent) {
        agentService.saveEntry(newAgent);
    }

    @GetMapping("/getAll")
    public List<Agent> getAll() { return agentService.getAll(); }
}

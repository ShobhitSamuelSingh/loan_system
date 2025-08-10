package com.shobhit.loan_system.controller;

import com.shobhit.loan_system.model.entity.Agent;
import com.shobhit.loan_system.model.enums.LoanStatus;
import com.shobhit.loan_system.service.AgentService;
import com.shobhit.loan_system.service.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/v1/agent")
public class AgentController {


    @Autowired
    private AgentService agentService;

    @Autowired
    private LoanService loanService;

    @PostMapping
    public void createAgent(@RequestBody Agent newAgent) {
        agentService.saveEntry(newAgent);
    }

    @GetMapping("/getAll")
    public List<Agent> getAll() { return agentService.getAll(); }

    @PutMapping("/{agentId}/loan/{loanId}/decision")
    public ResponseEntity<?> agentDecision(
            @PathVariable Long agentId,
            @PathVariable Long loanId,
            @RequestBody Map<String, String> request) {
        String decision = request.get("decision");
        if(!"APPROVE".equalsIgnoreCase(decision) && !"REJECT".equalsIgnoreCase(decision)) {
            return ResponseEntity.badRequest().body("Invalid Decision. Must be APPROVE or REJECT.");
        }

        LoanStatus loanDecision = LoanStatus.APPLIED;
        if("APPROVE".equalsIgnoreCase(decision)) loanDecision = LoanStatus.APPROVED_BY_AGENT;
        else if("REJECT".equalsIgnoreCase(decision)) loanDecision = LoanStatus.REJECTED_BY_AGENT;

        loanService.setDecision( loanId, loanDecision);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

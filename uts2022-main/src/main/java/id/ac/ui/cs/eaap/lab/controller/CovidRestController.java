package id.ac.ui.cs.eaap.lab.controller;

import id.ac.ui.cs.eaap.lab.model.CovidCaseModel;
import id.ac.ui.cs.eaap.lab.model.FakultasCase;
import id.ac.ui.cs.eaap.lab.service.CovidTrackerService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/covid")
public class CovidRestController {
    CovidTrackerService covidTrackerService;

    @GetMapping("/all")
    public ResponseEntity getAllCovidCases() {
        log.info("api get all covid case");
        return ResponseEntity.ok("");
    }

    // Nomor 7: api
    @GetMapping("/active")
    public ResponseEntity<List<CovidCaseModel>> getActiveCovidCases() {
        log.info("api get all covid cases");
        List<CovidCaseModel> listActiveCases = covidTrackerService.findActiveCases();
        return ResponseEntity.ok(listActiveCases);
    }

    @GetMapping("/statistics")
    public ResponseEntity<List<FakultasCase>> getStatisticsCovidCases() {
        log.info("api statistics covid cases");
        List<FakultasCase> listFakultasCase = covidTrackerService.getJumlahKasusByFakultas();
        return ResponseEntity.ok(listFakultasCase);
    }


}

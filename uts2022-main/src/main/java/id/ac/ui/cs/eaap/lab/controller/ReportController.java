package id.ac.ui.cs.eaap.lab.controller;

import id.ac.ui.cs.eaap.lab.model.CovidCaseModel;
import id.ac.ui.cs.eaap.lab.model.FakultasCase;
import id.ac.ui.cs.eaap.lab.repository.CovidCaseDb;
import id.ac.ui.cs.eaap.lab.service.CovidTrackerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/report")
public class ReportController {

    CovidTrackerService covidTrackerService;

    CovidCaseDb covidCaseDb;

    // Nomor 7: URL
    @GetMapping(value = "/active")
    public String viewActiveCovidCase(Model model) {
        List<CovidCaseModel> ActiveCase = covidTrackerService.findActiveCases();
        model.addAttribute("caseList", ActiveCase);
        return "report/report-active-covid-case";
    }

    // @GetMapping(value = "/statistics")
    // public String viewStatistics(Model model) {
    //     List<CovidCaseModel> ActiveCase = covidTrackerService.findActiveCases();
    //     model.addAttribute("caseList", ActiveCase);
    
    //     int countFasilkom = 0;
    //     int countFK = 0;
    //     int countFT = 0;
    //     int countFisip = 0;
    //     int countFIB = 0;
    
    //     for (CovidCaseModel covidCase : ActiveCase) {
    //         String fakultas = covidCase.getFakultas();
    //         if (fakultas != null) {
    //             // Count occurrences of faculties
    //             if (fakultas.toLowerCase().contains("fasilkom")) {
    //                 countFasilkom++;
    //             }
    //             if (fakultas.toLowerCase().contains("fk")) {
    //                 countFK++;
    //             }
    //             if (fakultas.toLowerCase().contains("ft")) {
    //                 countFT++;
    //             }
    //             if (fakultas.toLowerCase().contains("fisip")) {
    //                 countFisip++;
    //             }
    //             if (fakultas.toLowerCase().contains("fib")) {
    //                 countFIB++;
    //             }
    //         }
    //     }
    
    //     // Add the counts as model attributes
    //     model.addAttribute("countFasilkom", countFasilkom);
    //     model.addAttribute("countFK", countFK);
    //     model.addAttribute("countFT", countFT);
    //     model.addAttribute("countFisip", countFisip);
    //     model.addAttribute("countFIB", countFIB);
    
    //     return "report/report-statistics";
    // }

    @GetMapping(value = "/statistics")
    public String viewStatistics(Model model) {
        List<FakultasCase> listOfFakultasCases = covidTrackerService.getJumlahKasusByFakultas();
        model.addAttribute("caseList",listOfFakultasCases);
        return "report/report-statistics";
    }

}

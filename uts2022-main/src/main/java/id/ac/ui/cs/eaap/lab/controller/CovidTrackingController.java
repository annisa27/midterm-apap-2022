package id.ac.ui.cs.eaap.lab.controller;

import id.ac.ui.cs.eaap.lab.model.CovidCaseModel;
import id.ac.ui.cs.eaap.lab.model.LastContactModel;
import id.ac.ui.cs.eaap.lab.repository.CovidCaseDb;
import id.ac.ui.cs.eaap.lab.repository.LastContactDb;
import id.ac.ui.cs.eaap.lab.service.CovidTrackerService;
import id.ac.ui.cs.eaap.lab.service.LastContactService;
import id.ac.ui.cs.eaap.lab.service.ListService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/covid")
public class CovidTrackingController {


    CovidTrackerService covidTrackerService;


    ListService listService;


    LastContactService lastContactService;


    LastContactDb lastContactDb;


    CovidCaseDb covidCaseDb;

    @GetMapping(value = "/")
    public String getAll(Model model) {
        log.info("access home");
        return "home";
    }


    @GetMapping("/add")
    public String addPatientFormPage(Model model) {

        CovidCaseModel covidCaseModel = new CovidCaseModel();
        // set default value to TODAY
        covidCaseModel.setTanggalGejalaPertama(new java.sql.Date(Calendar.getInstance().getTime().getTime()));

        model.addAttribute("covidCaseModel", covidCaseModel);
        model.addAttribute("listService", listService);

        return "case/form-add-covid-case";
    }

    // Nomor 2
    @PostMapping(value = "/add", params = {"save"})
    public String addPatientSubmitPage(@ModelAttribute CovidCaseModel covidCaseModel, BindingResult result,
                                       RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            redirectAttrs.addFlashAttribute("error", "The error occurred.");
            return "redirect:/covid/add";
        }

        covidTrackerService.add(covidCaseModel);

        redirectAttrs.addFlashAttribute("success",
                String.format("Kasus baru berhasil disimpan sebagai id %d", covidCaseModel.getCaseId()));
        return "redirect:/covid/view-all";
    }

    // Nomor 1
    @GetMapping("/view-all")
    public String viewAllCovidCase(Model model) {
        List<CovidCaseModel> covidCaseModelList = covidTrackerService.findAllCaseModels();
        model.addAttribute("caseList", covidCaseModelList);
        return "case/view-all-covid-case";
    }

    // Nomor 3: Update case
    @GetMapping("/update/{caseId}")
    public String formUpdateCase(
            @PathVariable Long caseId,
            Model model
    ){
        CovidCaseModel covidCaseToUpdate = covidTrackerService.getCovidCaseById(caseId);
        model.addAttribute("covidCaseToUpdate", covidCaseToUpdate);
        model.addAttribute("listService", listService);
        return "case/form-update-case";
    }

    @PostMapping(value = "/update/{caseId}", params = {"update"})
    public String updateCase(@PathVariable Long caseId, @ModelAttribute CovidCaseModel covidCaseToUpdate, BindingResult result,
                                       RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            redirectAttrs.addFlashAttribute("error", "The error occurred.");
            return "redirect:/covid/update/{caseId}";
        }

        var updateCase = covidTrackerService.getCovidCaseById(caseId);
        updateCase.setStatus(covidCaseToUpdate.getStatus());
        covidTrackerService.add(updateCase);

        redirectAttrs.addFlashAttribute("success",
                String.format("Kasus baru berhasil diperbarui sebagai id %d", covidCaseToUpdate.getCaseId()));
        return "redirect:/covid/view-all";
    }

    // Nomor 4: detail last contact
    @GetMapping("/detail/{caseId}")
    public String detailContactForm(
            @PathVariable Long caseId,
            Model model
    ){
        CovidCaseModel covidCaseDetail = covidTrackerService.getCovidCaseById(caseId);
        model.addAttribute("covidCaseDetail", covidCaseDetail);

        LastContactModel lastContact = new LastContactModel();
        model.addAttribute("lastContact", lastContact);

        List<LastContactModel> lastContactAvail = lastContactDb.findAllByCovidCaseModelCaseId(caseId);
        model.addAttribute("lastContactAvail", lastContactAvail);

        model.addAttribute("listService", listService);
        
        return "case/detail-case";
    }

    @PostMapping(value = "/detail/{caseId}", params = {"addContact"})
    public String detailContactSubmit(@PathVariable Long caseId, @ModelAttribute LastContactModel lastContact, BindingResult result,
                                       RedirectAttributes redirectAttrs) {

        var covidCase = covidTrackerService.getCovidCaseById(caseId);
        covidTrackerService.addLastContact(lastContact, covidCase);

        redirectAttrs.addFlashAttribute("success",
                String.format("Kasus baru berhasil diperbarui sebagai id %d", covidCase.getCaseId()));
        return "redirect:/covid/view-all";
    }

    //Nomor 5: Search nama
    @GetMapping("/search")
    public String listBuku(@RequestParam(name = "nama", required = false) String nama, Model model) {
        List<CovidCaseModel> covidCaseModelList;

        // Jika nama tidak kosong, lakukan pencarian berdasarkan judul
        covidCaseModelList = covidCaseDb.findByNamaContainingIgnoreCase(nama);

        model.addAttribute("caseList", covidCaseModelList);
        return "case/view-search";
    }
    
}

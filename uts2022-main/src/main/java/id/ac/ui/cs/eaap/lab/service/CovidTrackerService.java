package id.ac.ui.cs.eaap.lab.service;


import id.ac.ui.cs.eaap.lab.model.CovidCaseModel;
import id.ac.ui.cs.eaap.lab.model.LastContactModel;
import id.ac.ui.cs.eaap.lab.repository.CovidCaseDb;
import id.ac.ui.cs.eaap.lab.repository.LastContactDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CovidTrackerService {
    @Autowired
    CovidCaseDb covidCaseDb;

    @Autowired
    LastContactDb lastContactDb;

    public List<CovidCaseModel> findAllCaseModels() {
        return covidCaseDb.findAll();
    }

    public List<CovidCaseModel> findActiveCases(long caseId) {
        return covidCaseDb.findById(caseId);
    }

    public CovidCaseModel getCovidCaseById(Long caseId) {
        for (CovidCaseModel caseCovid : findAllCaseModels()) {
            if (caseCovid.getCaseId().equals(caseId)) {
                return caseCovid;
            }
        }
        return null;
    }

    public void add(CovidCaseModel covidCaseModel) {
        covidCaseDb.save(covidCaseModel);
    }

    public void addLastContact(LastContactModel lastContactModel, CovidCaseModel covidCase) {
        if (covidCase.getListLastContactModel() == null || covidCase.getListLastContactModel().size() == 0) {
            covidCase.setListLastContactModel(new ArrayList<>());
        }
        
        lastContactModel.setCovidCaseModel(covidCase);
        covidCase.getListLastContactModel().add(lastContactModel);
        covidCaseDb.save(covidCase);
    }

    public void update(CovidCaseModel covidCaseModel) {
    }

}


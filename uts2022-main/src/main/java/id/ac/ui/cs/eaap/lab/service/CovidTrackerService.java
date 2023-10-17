package id.ac.ui.cs.eaap.lab.service;


import id.ac.ui.cs.eaap.lab.model.CovidCaseModel;
import id.ac.ui.cs.eaap.lab.repository.CovidCaseDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CovidTrackerService {
    @Autowired
    CovidCaseDb covidCaseDb;

    public List<CovidCaseModel> findAll() {
        return covidCaseDb.findAll();
    }

    public List<CovidCaseModel> findActiveCases() {
        return new ArrayList<>();
    }

    public void add(CovidCaseModel covidCaseModel) {
    }

    public void update(CovidCaseModel covidCaseModel) {
    }

}


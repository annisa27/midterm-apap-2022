package id.ac.ui.cs.eaap.lab.service;


import id.ac.ui.cs.eaap.lab.model.CovidCaseModel;
import id.ac.ui.cs.eaap.lab.model.FakultasCase;
import id.ac.ui.cs.eaap.lab.model.LastContactModel;
import id.ac.ui.cs.eaap.lab.repository.CovidCaseDb;
import id.ac.ui.cs.eaap.lab.repository.LastContactDb;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CovidTrackerService {
    CovidCaseDb covidCaseDb;

    LastContactDb lastContactDb;

    ListService listService;

    public List<CovidCaseModel> findAllCaseModels() {
        return covidCaseDb.findAll();
    }

    public List<CovidCaseModel> findActiveCases() {
        List<CovidCaseModel> listActiveCases = new ArrayList<>();
        List<CovidCaseModel> listCovidCaseModel = findAllCaseModels();
        for (CovidCaseModel covidCaseModel :
                listCovidCaseModel) {
            if ((covidCaseModel.getStatus().equals("suspek") || covidCaseModel.getStatus().equals("terkonfirmasi")) && covidCaseModel.getJumlahHariSetelahGejalaPertama() < 14) {
                listActiveCases.add(covidCaseModel);
            }
        }
        return listActiveCases;

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

    // Nomor 8 & 9
    public int countByFakultas(String namaFakultas){
        int counter = 0;
        List<CovidCaseModel> listOfActiveCases = findActiveCases();
        for (CovidCaseModel covidCaseModel : listOfActiveCases){
            if (covidCaseModel.getFakultas().equals(namaFakultas)){
                counter++;
            }
        }
        return counter;
    }

    public List<FakultasCase> getJumlahKasusByFakultas(){
        List<FakultasCase> listOfFakultasCase = new ArrayList<>();
        for (String fakultas : listService.getFakultasOptionsList()){
            FakultasCase newFakultasCase = new FakultasCase();
            newFakultasCase.setFakultas(fakultas);
            newFakultasCase.setJumlahKasus(countByFakultas(fakultas));
            listOfFakultasCase.add(newFakultasCase);
        }
        return listOfFakultasCase;
    }

    

}


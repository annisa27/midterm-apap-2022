package id.ac.ui.cs.eaap.lab.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.eaap.lab.model.CovidCaseModel;
import id.ac.ui.cs.eaap.lab.model.LastContactModel;
import id.ac.ui.cs.eaap.lab.repository.LastContactDb;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LastContactService {
    LastContactDb lastContactDb;

    public List<LastContactModel> findAllContact() {
        return lastContactDb.findAll();
    }

    public LastContactModel getLastContactById(Long caseId) {
        for (LastContactModel contact : findAllContact()) {
            if (contact.getCovidCaseModel().getCaseId().equals(caseId)) {
                return contact;
            }
        }
        return null;
    }
}

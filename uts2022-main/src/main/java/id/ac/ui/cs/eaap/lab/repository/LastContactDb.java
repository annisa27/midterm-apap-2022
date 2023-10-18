package id.ac.ui.cs.eaap.lab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.ac.ui.cs.eaap.lab.model.LastContactModel;

@Repository
public interface LastContactDb extends JpaRepository<LastContactModel, Long>{
    List<LastContactModel> findAllByCovidCaseModelCaseId(Long caseId);
}

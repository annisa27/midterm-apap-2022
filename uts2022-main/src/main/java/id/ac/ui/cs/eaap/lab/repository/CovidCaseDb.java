package id.ac.ui.cs.eaap.lab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.ac.ui.cs.eaap.lab.model.CovidCaseModel;

@Repository
public interface CovidCaseDb extends JpaRepository<CovidCaseModel, Long>{
    List<CovidCaseModel> findById(long caseId);
    List<CovidCaseModel> findByNamaContainingIgnoreCase(String nama);
}

package id.ac.ui.cs.eaap.lab.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.ac.ui.cs.eaap.lab.model.CovidCaseModel;

@Repository
public interface CovidCaseDb extends JpaRepository<CovidCaseModel, Long>{
    List<CovidCaseModel> findById(long caseId);
    List<CovidCaseModel> findByNamaContainingIgnoreCase(String nama);
    @Query("SELECT c FROM CovidCaseModel c WHERE (CURRENT_TIMESTAMP - c.tanggalGejalaPertama) / (1000 * 60 * 60 * 24) BETWEEN ?1 AND ?2")
    List<CovidCaseModel> findByJumlahHariSetelahGejalaPertamaBetween(int minDays, int maxDays);
}

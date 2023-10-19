package id.ac.ui.cs.eaap.lab;

import id.ac.ui.cs.eaap.lab.model.CovidCaseModel;
import id.ac.ui.cs.eaap.lab.repository.CovidCaseModelDb;
import id.ac.ui.cs.eaap.lab.service.CovidTrackerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class CovidTrackerServiceTest {
    @Mock
    private CovidCaseModelDb covidCaseModelDb;

    @InjectMocks
    CovidTrackerService covidTrackerService;

    @BeforeEach
    public void setUp() {
        List<CovidCaseModel> covidCaseModelList = new ArrayList<>();

        var case1 = new CovidCaseModel(
                1,
                "123",
                "ivanox",
                "suspek",
                new java.sql.Date(Calendar.getInstance().getTime().getTime()),
                "mahasiswa",
                "FASILKOM",
                new ArrayList<>(),
                0);

        var case2 = new CovidCaseModel(
                2,
                "1234",
                "xonavi",
                "suspek",
                new java.sql.Date(Calendar.getInstance().getTime().getTime()),
                "mahasiswa",
                "FK",
                new ArrayList<>(),
                0);

        covidCaseModelList.add(case1);
        covidCaseModelList.add(case2);

        Mockito.when(covidCaseModelDb.findAll()).thenReturn(covidCaseModelList);
    }

    @Test
    public void getStatusAktifPerfakultasTest() {
        var listFakultasCase = covidTrackerService.getStatusAktifPerfakultas();

        var fasilkomcounter = 0;
        var fkcounter = 0;
        var fibcounter = 0;
        var fisipcounter = 0;
        var ftcounter = 0;
        for (Map.Entry<String, Integer> entry:
             listFakultasCase.entrySet()) {
            switch (entry.getKey()) {
                case "FASILKOM":
                    fasilkomcounter = entry.getValue();
                    break;
                case "FK":
                    fkcounter = entry.getValue();
                    break;
                case "FIB":
                    fibcounter = entry.getValue();
                    break;
                case "FISIP":
                    fisipcounter = entry.getValue();
                    break;
                case "FT":
                    ftcounter = entry.getValue();
                    break;
            }
        }

        assertEquals(20, fasilkomcounter);
        assertEquals(1, fkcounter);
        assertEquals(0, fibcounter);
        assertEquals(0, fisipcounter);
        assertEquals(0, ftcounter);


    }
}

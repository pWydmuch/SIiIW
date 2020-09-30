package lab1.controllers;

import lab1.algorithms.Algorithm;
import lab1.algorithms.EvolutionAlgorithm;
import lab1.crossings.CrossingFactory;
import lab1.dto.BestDto;
import lab1.dto.FormDto;
import lab1.individual.Individual;
import lab1.mutations.MutationFactory;
import lab1.selections.Selection;
import lab1.selections.SelectionFactory;
import lab1.selections.TournamentSelection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;

@Controller
public class MyController {



    @GetMapping("/")
    public String getIndex(FormDto formDto) {
        formDto.setSelection("tour");
        formDto.setMutation("swap");
        formDto.setCross("ox");
        return "index";
    }

    //TODO builder dla evolution, bo nie zawsze trzeba tour size
    @PostMapping("/best")
    public String getBestSolution(FormDto formDto, Model model) {
        System.out.println(formDto);
        Selection selection =  SelectionFactory.createSelection(formDto.getSelection());
        Algorithm algorithm = new EvolutionAlgorithm(formDto.getInstance(),
                formDto.getPopSize(),
                formDto.getGenNr(),
                formDto.getCrossProb(),
                formDto.getMutProb(),
                MutationFactory.createMutation(formDto.getMutation()),
                CrossingFactory.createCrossing(formDto.getCross()),
                selection
               );
        if(selection instanceof TournamentSelection) ((TournamentSelection) selection).setTourSize(formDto.getTourSize());
        formDto.setSelection("tour");
        formDto.setMutation(formDto.getMutation());
        formDto.setCross("ox");
        Individual best = ((EvolutionAlgorithm) algorithm).start();
        BestDto bestDto = new BestDto();
        bestDto.setDistance(String.format("%.0f",best.getFitness()));
        bestDto.setRoute(String.valueOf(best.getRoute()));
        model.addAttribute("best", bestDto);
        return "result";
    }

//    @GetMapping("/best")
//    public String getBest(FormDto formDto, Model model) {
//        System.out.println(formDto);
//        Algorithm algorithm = new EvolutionAlgorithm("berlin11_modified", 100, 100, 0.7, 0.1, 5);
//        Individual best = ((EvolutionAlgorithm) algorithm).start();
//        BestDto bestDto = new BestDto();
//        bestDto.setDistance(String.format("%.0f",best.getFitness()));
//        bestDto.setRoute(String.valueOf(best.getRoute()));
//        model.addAttribute("best", bestDto);
//        return "result";
//    }

//TODO sprawdź tę ścieżke    //    @GetMapping("/download/{fileName:.+}")
    @GetMapping("/file")
    public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response) throws IOException {

        File file = new File("src/main/resources/log.txt");
        if (file.exists()) {

            //get the mimetype
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }

            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
            response.setContentLength((int) file.length());

            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

            FileCopyUtils.copy(inputStream, response.getOutputStream());

        }
    }
}

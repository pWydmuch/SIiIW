package si.zad2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import si.zad2.algorithms.Algorithm;
import si.zad2.algorithms.AlgorithmFactory;
import si.zad2.algorithms.BacktrackAlgorithm;
import si.zad2.dto.FormDto;
import si.zad2.dto.ParamsDto;
import si.zad2.util.Loader;
import si.zad2.value_heuritics.ValueHeuristic;
import si.zad2.value_heuritics.ValueHeuristicFactory;
import si.zad2.variable_heuristics.VariableHeuristicFactory;

import java.util.Arrays;

@Controller
public class MyController {
    private final static String path = "src\\main\\resources\\static\\Sudoku.csv";

    @GetMapping("/")
    public String getIndex(Model model, FormDto formDto) {
        formDto.setInstance(1);
        formDto.setMethod("backtrack");
        formDto.setValuechoice("order");
        formDto.setVarchoice("order");
        return "index";
    }

    @PostMapping("/solution")
    public String solve(Model model, FormDto formDto){
        int instance = formDto.getInstance();
        int[][] array = Loader.load(instance,path,true);
        model.addAttribute("array", array);
        Algorithm algorithm = AlgorithmFactory.getAlgorithm(formDto.getMethod());
        algorithm.setValueHeuristic(ValueHeuristicFactory.getValueHeuristic(formDto.getValuechoice()));
        algorithm.setVariableHeuristic(VariableHeuristicFactory.getVariableHeuristic(formDto.getVarchoice()));
        algorithm.findSolution(instance);
        ParamsDto paramsDto = new ParamsDto(algorithm);
        int[][][] solved = Loader.loadAllSolutions(paramsDto.getSolutionsNr(),"src\\main\\resources\\log.txt");
        model.addAttribute("params", paramsDto);
        model.addAttribute("solved", solved);
        return "result";
    }
}

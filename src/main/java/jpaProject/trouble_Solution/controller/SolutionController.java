package jpaProject.trouble_Solution.controller;

import jpaProject.trouble_Solution.domain.Categories;
import jpaProject.trouble_Solution.domain.GenerationStatus;
import jpaProject.trouble_Solution.domain.Solution;
import jpaProject.trouble_Solution.domain.Worry;
import jpaProject.trouble_Solution.repository.CategoriesRepositoryImpl;
import jpaProject.trouble_Solution.service.SolutionService;
import jpaProject.trouble_Solution.service.WorryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SolutionController {
    private final SolutionService solutionService;
    private final WorryService worryService;
    private final CategoriesRepositoryImpl categoriesRepository;

    @ModelAttribute("generations")
    public GenerationStatus[] generationStatuses(){
        return GenerationStatus.values();
    }

    @ModelAttribute("categories")
    public List<Categories> categories(){
        List<Categories> categoriesList = categoriesRepository.findAll();

        return categoriesList;
    }
    @GetMapping("/worrys/{worryId}")
    public String worry(@PathVariable Long worryId, Model model) {
        Worry worry = worryService.findWorry(worryId);
        List<Solution> solutions = solutionService.findByWorry(worry);

        model.addAttribute("worry", worry);
        model.addAttribute("solutions", solutions);

        return "worrys/worry";
    }

    @PostMapping("/solutions/{solutionId}/accept")
    public String acceptSolution(@PathVariable("solutionId") Long solutionId) {
        solutionService.acceptSolution(solutionId);

        return "redirect:/worrys";
    }

    /*@GetMapping("/worrys/{worryId}")*/
    public String createForm() {
        return "solutions/solutionForm.html";
    }
}

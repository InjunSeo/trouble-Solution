package jpaProject.trouble_Solution.controller;

import jpaProject.trouble_Solution.domain.*;
import jpaProject.trouble_Solution.repository.CategoriesRepositoryImpl;
import jpaProject.trouble_Solution.service.SolutionService;
import jpaProject.trouble_Solution.service.WorryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SolutionController {

    private final SolutionService solutionService;
    private final WorryService worryService;
    private final CategoriesRepositoryImpl categoriesRepository;

    @ModelAttribute("generations")
    public GenerationStatus[] generationStatuses() {
        return GenerationStatus.values();
    }

    @ModelAttribute("categories")
    public List<Categories> categories() {
        List<Categories> categoriesList = categoriesRepository.findAll();

        return categoriesList;
    }

    @ModelAttribute("solvedStatuses")
    public SolvedStatus[] solvedStatuses() {
        return SolvedStatus.values();
    }

    @GetMapping("/worrys/{worryId}")
    public String worry(@PathVariable Long worryId, Model model) {
        Worry worry = worryService.findWorry(worryId);
        List<Solution> solutions = solutionService.findByWorry(worry);
        List<CategoryWorry> categoryWorries = worry.getCategoryWorries();

        WorryUpdateForm worryForm = new WorryUpdateForm();
        List<Long> categoryId = new ArrayList<>();
        for (CategoryWorry categoryWorry : categoryWorries) {
            Categories category = categoryWorry.getCategory();
            Long id = category.getId();
            categoryId.add(id);
        }
        worryForm.setId(worry.getId());
        worryForm.setMember(worry.getMember());
        worryForm.setTitle(worry.getTitle());
        worryForm.setContent(worry.getContent());
        worryForm.setGeneration(worry.getGeneration());
        worryForm.setCategoryId(categoryId);
        worryForm.setSolvedStatus(worry.getSolvedStatus());

        model.addAttribute("worryForm", worryForm);
        model.addAttribute("solutions", solutions);

        return "worrys/worry";
    }

    @PostMapping("/solutions/{solutionId}/accept")
    public String acceptSolution(@PathVariable("solutionId") Long solutionId) {
        solutionService.acceptSolution(solutionId);

        return "redirect:/worrys";
    }

    @GetMapping("/solutions/{worryId}/new")
    public String addSolutionForm(@PathVariable("worryId") Long worryId, Model model) {

        model.addAttribute("solutionForm", new Solution());
        model.addAttribute("worryId", worryId);
        return "solutions/addSolutionForm";
    }

    @PostMapping("/solutions/{worryId}/new")
    public String addSolution(@ModelAttribute("solutionForm") SolutionSaveForm solutionForm) {
        log.info("Solver ID={}", solutionForm.getMember());
        log.info("Solver's solution={}", solutionForm.getContent());

        return "redirect:/worrys/{worryId}";
    }
}

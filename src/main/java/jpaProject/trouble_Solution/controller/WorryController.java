package jpaProject.trouble_Solution.controller;

import jpaProject.trouble_Solution.domain.Categories;
import jpaProject.trouble_Solution.domain.GenerationStatus;
import jpaProject.trouble_Solution.domain.Worry;
import jpaProject.trouble_Solution.repository.CategoriesRepositoryImpl;
import jpaProject.trouble_Solution.service.WorryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WorryController {
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

    @GetMapping("/worrys/new")
    public String createForm(Model model) {
        model.addAttribute("worryForm", new WorryForm());
        return "worrys/createWorryForm";
    }

    @PostMapping("/worrys/new")
    public String create(@Validated @ModelAttribute("worryForm") WorrySaveForm worryForm, BindingResult result) {
        log.info("form.generation={}", worryForm.getGeneration());
        log.info("form.categories={}", worryForm.getCategoryId());

        if (result.hasErrors()) {
            log.info("errors={}", result);
            return "/worrys/createWorryForm";
        }

        Worry worry = new Worry();
        worry.setMember(worryForm.getMember());
        worry.setTitle(worryForm.getTitle());
        worry.setContent(worryForm.getContent());
        worry.setGeneration(worryForm.getGeneration());
        worry.setCreateDate(LocalDateTime.now());
        List<Long> categoryId = worryForm.getCategoryId();
        for (Long aLong : categoryId) {
            Categories categories = categoriesRepository.finById(aLong);
            worry.addCategory(categories);
        }
        worryService.writeWorry(worry);
        return "redirect:/";
    }

    @GetMapping("/worrys")
    public String list(Model model) {
        List<Worry> worries = worryService.findWorries();
        model.addAttribute("worries", worries);
        return "worrys/worryList";
    }

    @GetMapping("worrys/{worryId}/edit")
    public String updateWorryForm(@PathVariable("worryId") Long worryId, Model model) {
        Worry worry = worryService.findWorry(worryId);
        WorryForm form = new WorryForm();
        form.setId(worry.getId());
        form.setTitle(worry.getTitle());
        form.setContent(worry.getContent());
        form.setGeneration(worry.getGeneration());
        form.setMember(worry.getMember());
        form.setCreateDate(worry.getCreateDate());

        model.addAttribute("form", form);
        return "worrys/updateWorryForm";
    }

    @PostMapping("worrys/{worryId}/edit")
    public String updateWorry(@PathVariable Long worryId, @ModelAttribute("form") WorryForm worryForm) {

        log.info("form.generation ={}", worryForm.getGeneration());

        worryService.updateWorry(worryId, worryForm.getTitle(), worryForm.getContent());
        return "redirect:/worrys/{worryId}";
    }

}

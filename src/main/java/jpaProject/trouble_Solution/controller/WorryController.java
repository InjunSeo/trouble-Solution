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
        model.addAttribute("form", new WorryForm());
        return "worrys/createWorryForm";
    }

    @PostMapping("/worrys/new")
    public String create(@ModelAttribute WorryForm form, BindingResult bindingResult) {
        log.info("form.generation={}", form.getGeneration());
        log.info("form.categories={}", form.getCategoryId());
        if (!StringUtils.hasText(form.getTitle())) {
            bindingResult.addError(new FieldError("form", "title", "You must input the title."));
        }
        if (!StringUtils.hasText(form.getContent())) {
            bindingResult.addError(new FieldError("form", "content", "You must write the content of your trouble."));
        }
        if (form.getGeneration() == null) {
            bindingResult.addError(new FieldError("form", "generation", "Check the generation you want to share the trouble."));
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "/worrys/createWorryForm";
        }
        Worry worry = new Worry();
        worry.setMember(form.getMember());
        worry.setTitle(form.getTitle());
        worry.setContent(form.getContent());
        worry.setGeneration(form.getGeneration());
        worry.setCreateDate(LocalDateTime.now());
        List<Long> categoryId = form.getCategoryId();
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
    public String updateWorry(@PathVariable Long worryId, @ModelAttribute("form") WorryForm form) {

        log.info("form.generation ={}", form.getGeneration());

        worryService.updateWorry(worryId, form.getTitle(), form.getContent());
        return "redirect:/worrys/{worryId}";
    }

}

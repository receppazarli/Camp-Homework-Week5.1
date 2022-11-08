package kodlama.io.devs.web.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.devs.business.abstracts.LanguageService;
import kodlama.io.devs.business.requests.languages.CreateLanguageRequest;
import kodlama.io.devs.business.requests.languages.DeleteLanguageRequest;
import kodlama.io.devs.business.requests.languages.UpdateLanguagesRequest;
import kodlama.io.devs.business.responses.languages.GetAllLanguagesResponse;
import kodlama.io.devs.business.responses.languages.GetByIdLanguageResponse;

@RestController
@RequestMapping("/api/languages")
public class LanguageController {

	LanguageService languageService;

	public LanguageController(LanguageService languageService) {
		super();
		this.languageService = languageService;
	}

	@GetMapping("/getall")
	public List<GetAllLanguagesResponse> getAll() {
		return languageService.getAll();
	}

	@PostMapping("/add")
	public void add(CreateLanguageRequest createLanguageRequest) throws Exception {
		this.languageService.add(createLanguageRequest);
	}

	@PutMapping("/update")
	public void update(UpdateLanguagesRequest updateLanguagesRequest) throws Exception {
		this.languageService.update(updateLanguagesRequest);
	}

	@DeleteMapping("/{id}")
	public void delete(DeleteLanguageRequest deleteLanguageRequest) throws Exception {
		this.languageService.delete(deleteLanguageRequest);
	}

	@GetMapping("/getbyid")
	public GetByIdLanguageResponse getById(int id) throws Exception {
		return languageService.getById(id);
	}

}

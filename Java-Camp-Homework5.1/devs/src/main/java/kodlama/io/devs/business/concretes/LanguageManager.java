package kodlama.io.devs.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.devs.business.abstracts.LanguageService;
import kodlama.io.devs.business.requests.languages.CreateLanguageRequest;
import kodlama.io.devs.business.requests.languages.DeleteLanguageRequest;
import kodlama.io.devs.business.requests.languages.UpdateLanguagesRequest;
import kodlama.io.devs.business.responses.languages.GetAllLanguagesResponse;
import kodlama.io.devs.business.responses.languages.GetByIdLanguageResponse;
import kodlama.io.devs.dataAccess.abstracts.LanguageRepository;
import kodlama.io.devs.entities.concretes.Language;

@Service
public class LanguageManager implements LanguageService {

	LanguageRepository languageRepository;

	@Autowired
	public LanguageManager(LanguageRepository languageRepository) {

		this.languageRepository = languageRepository;
	}

	@Override
	public List<GetAllLanguagesResponse> getAll() {

		List<Language> languages = languageRepository.findAll();
		List<GetAllLanguagesResponse> languagesResponse = new ArrayList<GetAllLanguagesResponse>();

		for (Language language : languages) {
			GetAllLanguagesResponse responseItem = new GetAllLanguagesResponse();
			responseItem.setId(language.getId());
			responseItem.setName(language.getName());
			languagesResponse.add(responseItem);
		}

		return languagesResponse;
	}

	@Override
	public void add(CreateLanguageRequest createLanguageRequest)throws Exception {
		Language language = new Language();
		language.setName(createLanguageRequest.getName());
		
		if (isNameExistForAdd(language)) {
			throw new Exception("Programming language with this name already exists");
		}
		if (isNameEmptyForAdd(language)) {
			throw new Exception("Id not found");
		}

		this.languageRepository.save(language);
	}

	@Override
	public void update(UpdateLanguagesRequest updateLanguagesRequest) throws Exception {
		if (!isIdExist(updateLanguagesRequest.getId())) {
			throw new Exception("Id not found");
		}

		Language language = this.languageRepository.findById(updateLanguagesRequest.getId()).get();

		if (isNameEmptyForUpdate(updateLanguagesRequest)) {
			throw new Exception("Name field cannot be empty");
		}
		if (isNameExistForUpdate(updateLanguagesRequest)) {
			throw new Exception("Programming language with this name already exists");
		}

		language.setName(updateLanguagesRequest.getName());
		this.languageRepository.save(language);

	}

	@Override
	public void delete(DeleteLanguageRequest deleteLanguageRequest) throws Exception {
		if (!isIdExist(deleteLanguageRequest.getId())) {
			throw new Exception("Id not found");
		}
		this.languageRepository.deleteById(deleteLanguageRequest.getId());
	}

	@Override
	public GetByIdLanguageResponse getById(int id) throws Exception {

		if (!isIdExist(id)) {
			throw new Exception("Id not found");
		}

		Language language = this.languageRepository.findById(id).get();
		GetByIdLanguageResponse getByIdLanguageResponse = new GetByIdLanguageResponse();
		getByIdLanguageResponse.setName(language.getName());
		return getByIdLanguageResponse;

	}

	private boolean isIdExist(int id) {
		for (GetAllLanguagesResponse languagesResponse : getAll()) {
			if (languagesResponse.getId() == id) {
				return true;
			}
		}
		return false;
	}

	private boolean isNameExistForAdd(Language language) {
		for (GetAllLanguagesResponse languagesResponse : getAll()) {
			if (language.getName().equals(languagesResponse.getName())) {
				return true;
			}
		}
		return false;
	}

	private boolean isNameEmptyForAdd(Language language) {
		if (language.getName().isEmpty()) {
			return true;
		}
		return false;
	}

	private boolean isNameEmptyForUpdate(UpdateLanguagesRequest updateLanguagesRequest) {
		if (updateLanguagesRequest.getName().isEmpty()) {
			return true;
		}
		return false;
	}

	private boolean isNameExistForUpdate(UpdateLanguagesRequest updateLanguagesRequest) {
		for (GetAllLanguagesResponse languagesResponse : getAll()) {
			if (updateLanguagesRequest.getName().equals(languagesResponse.getName())) {
				return true;
			}
		}
		return false;
	}

}

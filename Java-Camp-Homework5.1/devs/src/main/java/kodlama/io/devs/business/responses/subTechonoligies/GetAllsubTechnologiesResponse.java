package kodlama.io.devs.business.responses.subTechonoligies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GetAllsubTechnologiesResponse {
	private int id;
	private int programmingLanguageId;
	private String name;
	private String programmingLanguageName;

}

package kodlama.io.devs.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.devs.entities.concretes.SubTechnologies;

public interface SubTechnologiesRepository extends JpaRepository<SubTechnologies, Integer> {

}

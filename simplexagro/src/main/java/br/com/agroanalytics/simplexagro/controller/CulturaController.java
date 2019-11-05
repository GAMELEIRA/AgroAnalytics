package br.com.agroanalytics.simplexagro.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agroanalytics.simplexagro.domain.Cultura;
import br.com.agroanalytics.simplexagro.domain.Usuario;
import br.com.agroanalytics.simplexagro.repository.CulturaRepository;
import br.com.agroanalytics.simplexagro.repository.UsuarioRepository;

@RestController
@RequestMapping("/cultura")
public class CulturaController {

	@Autowired
	private CulturaRepository culturaRepository;

	@PostMapping
	@Transactional
	public ResponseEntity criarCultura(@RequestBody Cultura cultura) {

		culturaRepository.save(cultura);

		return ResponseEntity.status(HttpStatus.CREATED).body(cultura);

	}

	@GetMapping("/todas-culturas")
	public ResponseEntity<List<Cultura>> buscarTodasCulturas() {

		if (culturaRepository.count() > 0) {

			List<Cultura> cultura = culturaRepository.findAll();

			return ResponseEntity.ok(cultura);

		}
		
		return ResponseEntity.noContent().build();

	}

	@GetMapping("/{id}")
	public ResponseEntity buscarCultura(@PathVariable("id") Long id) {

		Optional<Cultura> cultura = culturaRepository.findById(id);

		if (cultura.isPresent()) {

			return ResponseEntity.ok().body(cultura);

		}
		
			return ResponseEntity.noContent().build();

	}

	@PutMapping("/{id}")
	public ResponseEntity atualizarCultura(@RequestBody @Valid Cultura cultura) {
		
		if(cultura.getId() == null) {
			
			return ResponseEntity.noContent().build();
		
		}
		
		culturaRepository.save(cultura);
		
		return ResponseEntity.ok(cultura);

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluirCultura(@PathVariable("id") Long id) {

		if (culturaRepository.existsById(id)) {

			culturaRepository.deleteById(id);

			return ResponseEntity.ok().build();
		} else {

			return ResponseEntity.notFound().build();
		}

	}
	
	@DeleteMapping("/todas-culturas")
	@Transactional
	public ResponseEntity excluirTodasCulturas() {
		
		if (culturaRepository.count() > 0) {
			
			culturaRepository.deleteAll();

			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
		
		
	}

}

	

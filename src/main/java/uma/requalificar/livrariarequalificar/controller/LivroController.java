package uma.requalificar.livrariarequalificar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uma.requalificar.livrariarequalificar.dto.ListaResposta;
import uma.requalificar.livrariarequalificar.model.Livro;
import uma.requalificar.livrariarequalificar.service.LivroService;


@RestController
@CrossOrigin
public class LivroController 
{
	private final LivroService livroService;

	
	@Autowired
	public LivroController (LivroService livroService)
	{
		this.livroService = livroService;
	}
	
	
    @GetMapping ("/getLivros")
	@CrossOrigin
    public List<Livro> getLivros ()
    {
		return livroService.getLivros ();
    }
   
    
    @GetMapping("/getLivroById/{id}")
    public ResponseEntity<ListaResposta> getLivroById (@PathVariable String id){

    	ListaResposta listaResposta = new ListaResposta();
    	
		if (id.isBlank())
		{
			listaResposta.addMsg ("O ID do livro não pode ser nulo.");
			return ResponseEntity.status (HttpStatus.BAD_REQUEST).body (listaResposta);
		}

        listaResposta = livroService.getLivroById(id);

        if (listaResposta.getOptional().isEmpty()){
        	listaResposta.addMsg ("Livro não encontrado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(listaResposta);
        }else{
        	listaResposta.setStatusOk(true);
            return ResponseEntity.status(HttpStatus.OK).body(listaResposta);
        }
    }
    
    @PostMapping ("/addLivro")
	@CrossOrigin
	public ResponseEntity<ListaResposta> addLivro (@RequestBody Livro livro)
	{
    	ListaResposta listaResposta = new ListaResposta ();

		if (livro.getId () != null)
		{
			listaResposta.addMsg ("Ao adicionar um livro, o ID tem de ser nulo.");
			return ResponseEntity.status (HttpStatus.BAD_REQUEST).body (listaResposta);
		}

		if ( (livro.getTitulo () == null) )
		{
			listaResposta.addMsg ("Título nulo.");
			return ResponseEntity.status (HttpStatus.BAD_REQUEST).body (listaResposta);
		}

		if ( (livro.getData_lancamento () == null) )
		{
			listaResposta.addMsg ("Data de lançamento nula.");
			return ResponseEntity.status (HttpStatus.BAD_REQUEST).body (listaResposta);
		}
		
		if ( (livro.getEdicao () == null) )
		{
			listaResposta.addMsg ("Edição nula.");
			return ResponseEntity.status (HttpStatus.BAD_REQUEST).body (listaResposta);
		}

		if ( (livro.getImagem_capa () == null) )
		{
			listaResposta.addMsg ("Imagem de capa nula.");
			return ResponseEntity.status (HttpStatus.BAD_REQUEST).body (listaResposta);
		}
		
		if ( (livro.getIsbn () == null) )
		{
			listaResposta.addMsg ("ISBN nulo.");
			return ResponseEntity.status (HttpStatus.BAD_REQUEST).body (listaResposta);
		}
		
		if ( (livro.getSinopse () == null) )
		{
			listaResposta.addMsg ("Sinopse nula.");
			return ResponseEntity.status (HttpStatus.BAD_REQUEST).body (listaResposta);
		}
		
		listaResposta = livroService.addLivro (livro);

		if (!listaResposta.isStatusOk() )
		{
			return ResponseEntity.status (HttpStatus.BAD_REQUEST).body (listaResposta);
		} 
		else
		{
			return ResponseEntity.status (HttpStatus.OK).body (listaResposta);
		}

	}
  
    
}

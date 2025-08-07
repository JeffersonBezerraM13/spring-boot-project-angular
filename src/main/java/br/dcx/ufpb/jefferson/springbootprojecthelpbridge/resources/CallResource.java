package br.dcx.ufpb.jefferson.springbootprojecthelpbridge.resources;

import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.domain.entities.Call;
import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.domain.entities.dtos.CallDTO;
import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.services.CallService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping(value = "/calls")
@Tag(name = "Chamados", description = "Gerenciamento dos chamados do sistema")
public class CallResource {

    @Autowired
    private CallService callService;

    /**
     * Lista todos os chamados cadastrados.
     *
     * @return Lista de chamados (DTO)
     */
    @GetMapping
    @Operation(summary = "Lista todos os chamados cadastrados.")
    public ResponseEntity<List<CallDTO>> findAll(){
        List<CallDTO> calls = callService.findAll().stream().map(c->new CallDTO(c)).collect(Collectors.toList());
        return ResponseEntity.ok(calls);
    }

    /**
     * Busca um chamado pelo ID.
     *
     * @param id Identificador do chamado
     * @return Dados do chamado encontrado (DTO)
     */
    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca um chamado pelo ID.")
    public ResponseEntity<CallDTO> findById (@PathVariable Integer id){
        Call obj = callService.findById(id);
        return ResponseEntity.ok(new CallDTO(obj));
    }

    /**
     * Cria um novo chamado.
     * Acesso restrito a usuários com o perfil ADMIN.
     *
     * @param objDto Dados do chamado para criação
     * @return Chamado criado (DTO) com URI de localização
     */
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    @Operation(summary = "Cria um novo chamado.")
    public ResponseEntity<CallDTO> create(@Valid @RequestBody CallDTO objDto){
        Call obj = callService.create(objDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(
                        obj.getId()
                ).toUri();
        return ResponseEntity.created(uri).body(new CallDTO(obj));
    }

    /**
     * Atualiza um chamado existente pelo ID.
     * Acesso restrito a usuários com o perfil ADMIN.
     *
     * @param id Identificador do chamado a ser atualizado
     * @param objDto Novos dados do chamado
     * @return Chamado atualizado (DTO)
     */
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualiza um chamado existente pelo ID.")
    public ResponseEntity<CallDTO> update(@PathVariable Integer id, @Valid @RequestBody CallDTO objDto){
        Call newObj = callService.update(id,objDto);
        return ResponseEntity.ok().body(new CallDTO(newObj));
    }
}

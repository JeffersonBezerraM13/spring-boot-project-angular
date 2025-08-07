package br.dcx.ufpb.jefferson.springbootprojecthelpbridge.resources;

import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.domain.entities.Client;
import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.domain.entities.dtos.ClientDTO;
import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clients")
@Tag(name = "Clientes", description = "Gerenciamento de clientes do sistema")
public class ClientResource {

    @Autowired
    private ClientService ClientService;

    /**
     * Lista todos os clientes cadastrados.
     *
     * @return Lista de clientes (DTO)
     */
    @GetMapping
    @Operation(summary = "Lista todos os clientes cadastrados.")
    public ResponseEntity<List<ClientDTO>> findAll() {
        return ResponseEntity
                .ok()
                .body(ClientService.findAll().stream()
                                .map(x -> new ClientDTO(x))
                        .collect(Collectors.toList()));
    }

    /**
     * Retorna os dados de um cliente específico pelo ID.
     *
     * @param id ID do cliente
     * @return Dados do cliente (DTO)
     */
    @GetMapping(value = "/{id}")
    @Operation(summary = "Retorna os dados de um cliente específico pelo ID.")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Integer id) {
        return ResponseEntity
                .ok()
                .body(new ClientDTO(ClientService.findById(id)));
    }

    /**
     * Cria um novo cliente.
     * Acesso restrito a usuários com o perfil ADMIN.
     *
     * @param objDTO Dados do novo cliente
     * @return Cliente criado (DTO) com URI de localização
     */
    @PostMapping
    @Operation(summary = "Cria um novo cliente.")
    public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO objDTO){
        Client newObj = ClientService.create(objDTO);
        //URI é igual a URL. é a URL de acesso da nova entidade criada
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(
                        newObj.getId()
                ).toUri();
        return ResponseEntity.created(uri).body(new ClientDTO(newObj));
    }

    /**
     * Atualiza os dados de um cliente existente.
     * Acesso restrito a usuários com o perfil ADMIN.
     *
     * @param id
     * @param objDTO
     * @return
     */
    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualiza os dados de um cliente existente.")
    public ResponseEntity<ClientDTO> update(@PathVariable Integer id,@Valid @RequestBody ClientDTO objDTO){
        Client obj = ClientService.update(id,objDTO);
        return ResponseEntity.ok().body(new ClientDTO(obj));
    }

    /**
     * Remove um cliente pelo ID.
     * Acesso restrito a usuários com o perfil ADMIN.
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Remove um cliente pelo ID.")
    public ResponseEntity<ClientDTO> delete(@PathVariable Integer id){
        ClientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

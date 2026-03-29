package ismaeldev.clients.services;

import ismaeldev.clients.dto.ClientDTO;
import ismaeldev.clients.entities.Client;
import ismaeldev.clients.repositories.ClientRepository;
import ismaeldev.clients.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {

        Page<Client> clients = repository.findAll(pageable);

        return clients.map(x -> new ClientDTO(x));
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Client client = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado: " + id));
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        Client client = new Client();
        copyDtoToEntity(dto, client);
        client = repository.save(client);
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        Client client = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado: " + id));
        copyDtoToEntity(dto, client);
        client = repository.save(client);
        return new ClientDTO(client);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente não encontrado: " + id);
        }
        repository.deleteById(id);
    }

    private void copyDtoToEntity(ClientDTO dto, Client client) {
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setIncome(dto.getIncome());
        client.setBirthDate(dto.getBirthDate());
        client.setChildren(dto.getChildren());
    }
}

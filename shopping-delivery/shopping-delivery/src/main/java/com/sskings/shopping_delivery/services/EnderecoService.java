package com.sskings.shopping_delivery.services;

import com.sskings.shopping_delivery.models.EnderecoModel;
import com.sskings.shopping_delivery.repositories.EnderecoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    @Transactional
    public EnderecoModel salvar(EnderecoModel enderecoModel){
        return enderecoRepository.save(enderecoModel);
    }

    public List<EnderecoModel> listar(){
        List<EnderecoModel> enderecos= enderecoRepository.findAll();
        if(enderecos.isEmpty()){
            return new ArrayList<>();
        }
        return enderecos;
    }

    public EnderecoModel buscarPorId(Long id){
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado."));
    }

    @Transactional
    public EnderecoModel atualizar(Long id, EnderecoModel enderecoModel){
        enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado."));
        enderecoModel.setId(id);
        return enderecoRepository.save(enderecoModel);

    }

    @Transactional
    public void removerPorId(Long id){
        enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado."));
        enderecoRepository.deleteById(id);
    }

}

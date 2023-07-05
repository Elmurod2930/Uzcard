package com.example.service;

import com.example.dto.card.CardCreateDTO;
import com.example.dto.card.CardInfo;
import com.example.dto.card.CardResponseDTO;
import com.example.dto.card.CardUpdateDTO;
import com.example.entity.CardEntity;
import com.example.entity.ClientEntity;
import com.example.enums.CardStatus;
import com.example.exps.ItemNotFoundException;
import com.example.mapper.ClientMapper;
import com.example.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final ClientService clientService;

    public CardResponseDTO create(CardCreateDTO dto) {
        CardEntity entity = new CardEntity();
        entity.setBalance(dto.getBalance());
        entity.setNumber(dto.getNumber());
        entity.setExpiredDate(dto.getExpiredDate());
        entity.setClientId(dto.getClientId());
        entity.setCompanyId(dto.getCompanyId());
        cardRepository.save(entity);
        return toResponseDTO(entity);
    }

    public CardResponseDTO toResponseDTO(CardEntity entity) {
        CardResponseDTO dto = new CardResponseDTO();
        dto.setBalance(entity.getBalance());
        dto.setCompany(entity.getCompany());
        dto.setClient(entity.getClient());
        dto.setNumber(entity.getNumber());
        dto.setPhone(entity.getPhone());
        dto.setStatus(entity.getStatus());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setExpiredDate(entity.getExpiredDate());
        return dto;
    }

    public CardResponseDTO changeStatus(String id) {
        CardEntity card = get(id);
        if (true) {
            // true o'rniga company.getCompanyRole.equals(CompanyRole.BANK) bo'lishi kk
            // TODO: 7/5/2023  
            // Bank
            if (card.getStatus().equals(CardStatus.ACTIVE))
                card.setStatus(CardStatus.BLOCK);
            else
                card.setStatus(CardStatus.ACTIVE);
        } else
            card.setStatus(CardStatus.BLOCK);
        cardRepository.save(card);
        return toResponseDTO(card);

    }

    public CardEntity get(String id) {
        Optional<CardEntity> optional = cardRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("card not found");
        }
        return optional.get();
    }

    public CardResponseDTO assignPhone(CardUpdateDTO dto) {
        CardEntity entity = get(dto.getId());
        if (dto.getNumber() != null && !dto.getNumber().isBlank()) {
            entity.setPhone(dto.getPhone());
        }
        cardRepository.save(entity);
        return toResponseDTO(entity);
    }

    public CardInfo getCardById(String id) {
        // TODO: 7/5/2023 Get Card by cardId (PAYMENT - all, If BANK - It can take only card which was created by bank) 
        CardEntity card = get(id);
        return toCardInfo(card);
    }

    public CardInfo toCardInfo(CardEntity entity) {
        ClientEntity client = clientService.get(entity.getClientId());
        CardInfo dto = new CardInfo();
        dto.setId(entity.getId());
        dto.setBalance(entity.getBalance());
        dto.setNumber(entity.getNumber());
        dto.setPhone(entity.getPhone());
        dto.setStatus(entity.getStatus());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setExpiredDate(entity.getExpiredDate());
        dto.setProfile(new ClientMapper(client.getId(), client.getName(), client.getSurname()));
        return dto;
    }

    public List<CardInfo> getCardListByPhone(String phone) {
        // TODO: 7/5/2023 Get Card List by phone (PAYMENT - all, If BANK - It can take only card which was created by bank) 
        List<CardEntity> entityList = cardRepository.findByPhone(phone);
        List<CardInfo> cardInfoList = new LinkedList<>();
        for (CardEntity entity : entityList) {
            cardInfoList.add(toCardInfo(entity));
        }
        return cardInfoList;
    }

    public List<CardInfo> getCardListByClientId(String clientId) {
        // TODO: 7/5/2023   Get Card List By Client id (PAYMENT - all, If BANK - It can take only card which was created by bank)
        List<CardEntity> entityList = cardRepository.findByClientId(clientId);
        List<CardInfo> cardInfoList = new LinkedList<>();
        for (CardEntity entity : entityList) {
            cardInfoList.add(toCardInfo(entity));
        }
        return cardInfoList;
    }

    public CardInfo getCardByNumber(String number) {
        // TODO: 7/5/2023 Get Card By number (PAYMENT - all, If BANK - It can take only card which was created by bank)
        Optional<CardEntity> optional = cardRepository.findByNumber(number);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("not fount card");
        }
        return toCardInfo(optional.get());
    }

    public Long getCardBalanceByNumber(String number) {
        // TODO: 7/5/2023 Get Card balance by number (PAYMENT - all, If BANK - It can take only card which was created by bank) 
        Optional<CardEntity> optional = cardRepository.findByNumber(number);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("not fount card");
        }
        return optional.get().getBalance();
    }
}

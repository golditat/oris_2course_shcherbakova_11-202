package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dto.GoodDTO;
import org.example.entity.GoodEntity;
import org.example.repositories.GoodRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodServiceImpl implements GoodService{

    private GoodRepository repository;
    @Override
    @Transactional
    public void addNewGood(GoodDTO good) {
        GoodEntity goodEntity = GoodEntity.builder()
                .id(good.getId())
                .goodName(good.getGoodName())
                .build();
        repository.save(goodEntity);
    }

    @Override
    public GoodDTO getGood(Long id) {
        GoodEntity goodEntity = repository.getOne(id);
        return GoodDTO.builder()
                .id(goodEntity.getId())
                .goodName(goodEntity.getGoodName())
                .build();
    }
    @Override
    public String getGoodView(Long id) {
        GoodDTO goodDto = getGood(id);
        return String.format("Name: %s",
                goodDto.getGoodName());
    }
    @Override
    public List<String> viewGoods() {
        List<GoodEntity> goodEntities = repository.findAll();
        List<String> goodDTOs = new ArrayList<>();
        for (GoodEntity good : goodEntities) {
            Long userId = good.getId();
            goodDTOs.add(getGoodView(userId));
        }
        return goodDTOs;
    }
}

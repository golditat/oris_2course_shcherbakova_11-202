package org.example.service;

import org.example.dto.GoodDTO;

import java.util.List;

public interface GoodService {

    void addNewGood(GoodDTO good);

    GoodDTO getGood(Long id);

    String getGoodView(Long id);

    List<String> viewGoods();
}

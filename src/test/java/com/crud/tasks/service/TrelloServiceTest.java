package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.client.CreatedTrelloCardDto;
import com.crud.tasks.trello.client.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.client.TrelloListDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @Mock
    private TrelloClient trelloClient;

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private AdminConfig adminConfig;

    @Test
    public void testCreateTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("someName", "test", "bottom", "123");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("001", "testName", "http://testName.com");

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        //When
        CreatedTrelloCardDto returnedCard = trelloService.createdTrelloCard(trelloCardDto);
        //Then
        assertEquals("001", returnedCard.getId());
        assertEquals("testName", returnedCard.getName());
        assertEquals("http://testName.com", returnedCard.getShortUrl());
    }

    @Test
    public void testFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        trelloListDtoList.add(new TrelloListDto("1","list", true));

        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(new TrelloBoardDto("2", "new_list", trelloListDtoList));

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoList);
        //When
        List<TrelloBoardDto> trelloBoardDtoList1 = trelloService.fetchTrelloBoards();
        //Then
        assertEquals(trelloBoardDtoList.size(), trelloBoardDtoList1.size());
        assertEquals(trelloBoardDtoList.get(trelloBoardDtoList.size()-1).getId(),
                trelloBoardDtoList1.get(trelloBoardDtoList1.size()-1).getId());
    }

}

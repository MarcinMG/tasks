package com.crud.tasks.facade;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.trello.client.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloListDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTestSuite {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloListDto> trelloListDtoList1 = new ArrayList<>();
        trelloListDtoList1.add(new TrelloListDto("1.1", "testList1", true));
        trelloListDtoList1.add(new TrelloListDto("1.2", "testList2", false));
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("01", "testBoard1", trelloListDtoList1);

        List<TrelloListDto> trelloListDtoList2 = new ArrayList<>();
        trelloListDtoList2.add(new TrelloListDto("2.1", "testList1", true));
        trelloListDtoList2.add(new TrelloListDto("2.2", "testList2", false));
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("02", "testBoard2", trelloListDtoList2);

        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDto1);
        trelloBoardDtoList.add(trelloBoardDto2);

        //When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);

        //Then
        assertEquals(2, trelloBoardList.size());
        assertEquals(2, trelloBoardList.get(0).getLists().size());
        assertEquals("02", trelloBoardList.get(1).getId());
        assertEquals("2.1", trelloBoardList.get(1).getLists().get(0).getId());
        assertTrue(trelloBoardList.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloList> trelloListList1 = new ArrayList<>();
        trelloListList1.add(new TrelloList("1.1", "testList1", true));
        trelloListList1.add(new TrelloList("1.2", "testList2", false));
        TrelloBoard trelloBoard1 = new TrelloBoard("01", "testBoard1", trelloListList1);

        List<TrelloList> trelloListList2 = new ArrayList<>();
        trelloListList2.add(new TrelloList("2.1", "testList1", true));
        trelloListList2.add(new TrelloList("2.2", "testList2", false));
        TrelloBoard trelloBoard2 = new TrelloBoard("02", "testBoard2", trelloListList2);

        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(trelloBoard1);
        trelloBoardList.add(trelloBoard2);

        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);

        //Then
        assertEquals(2, trelloBoardDtoList.size());
        assertEquals(2, trelloBoardDtoList.get(0).getLists().size());
        assertEquals("02", trelloBoardDtoList.get(1).getId());
        assertEquals("2.1", trelloBoardDtoList.get(1).getLists().get(0).getId());
        assertTrue(trelloBoardDtoList.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        trelloListDtoList.add(new TrelloListDto("1.1", "testList1", true));
        trelloListDtoList.add(new TrelloListDto("1.2", "testList2", false));

        //When
        List<TrelloList> trelloListList = trelloMapper.mapToList(trelloListDtoList);

        //Then
        assertEquals(2, trelloListList.size());
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> trelloListList = new ArrayList<>();
        trelloListList.add(new TrelloList("1.1", "testList1", true));
        trelloListList.add(new TrelloList("1.2", "testList2", false));

        //When
        List<TrelloListDto> trelloListDtoList = trelloMapper.mapToListDto(trelloListList);

        //Then
        assertEquals(2, trelloListList.size());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Card", "test", "top", "001");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("001", trelloCard.getListId());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Card", "test", "top", "002");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("002", trelloCard.getListId());
    }
}

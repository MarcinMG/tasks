package com.crud.tasks.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloValidatorTest {

    @InjectMocks
    private TrelloValidator trelloValidator;

    @Test
    public void testValidateTrelloBoards() {
        //Given
        List<TrelloList> ListOfTrelloLists1 = new ArrayList<>();
        ListOfTrelloLists1.add(new TrelloList("1", "List1", false));
        ListOfTrelloLists1.add(new TrelloList("2", "List2", true));
        TrelloBoard trelloBoard1 = new TrelloBoard("1", "Board1", ListOfTrelloLists1);

        List<TrelloList> ListOfTrelloLists2 = new ArrayList<>();
        ListOfTrelloLists2.add(new TrelloList("1", "List1", true));
        ListOfTrelloLists2.add(new TrelloList("2", "List2", false));
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "Board2", ListOfTrelloLists2);

        List<TrelloBoard> listOfTrelloBoards = new ArrayList<>();
        listOfTrelloBoards.add(trelloBoard1);
        listOfTrelloBoards.add(trelloBoard2);

        //When
        List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(listOfTrelloBoards);

        //Then
        assertEquals(2, filteredBoards.size());
    }
}

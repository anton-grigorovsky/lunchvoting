package ru.topjava.lunchvote.service.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.topjava.lunchvote.model.VoteNote;
import ru.topjava.lunchvote.service.VoteNoteService;

import static ru.topjava.lunchvote.service.test_data.VoteNoteTestData.*;

/**
 * Created by Антон on 01.07.2018.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles("hibernate")
@RunWith(SpringJUnit4ClassRunner.class)
public class VoteNoteServiceImplTest {
    @Autowired
    private VoteNoteService service;

    @Test
    public void getAll()
    {
        assertMatch(service.getAll(), NOTES);
    }

    @Test
    public void create()
    {
        VoteNote created = getCreated();
        service.save(created);
        assertMatch(service.getAll(), VOTE_NOTE_1,
                VOTE_NOTE_2,
                VOTE_NOTE_3,
                VOTE_NOTE_5,
                VOTE_NOTE_6,
                VOTE_NOTE_4,
                created);
    }

    @Test
    public void update()
    {
        VoteNote updated = getUpdated();
        service.save(updated);
        assertMatch(service.getAll(), updated,
                VOTE_NOTE_2,
                VOTE_NOTE_3,
                VOTE_NOTE_5,
                VOTE_NOTE_6,
                VOTE_NOTE_4);
    }
}

package ru.topjava.lunchvote.service.test_data;

import ru.topjava.lunchvote.model.VoteNote;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Антон on 01.07.2018.
 */
public class VoteNoteTestData {

    public static final VoteNote VOTE_NOTE_1 = new VoteNote(LocalDate.of(2015, 5,30), 100007, 100000);
    public static final VoteNote VOTE_NOTE_2 = new VoteNote(LocalDate.of(2015, 5,30), 100008, 100001);
    public static final VoteNote VOTE_NOTE_3 = new VoteNote(LocalDate.of(2015, 5,30), 100009, 100002);
    public static final VoteNote VOTE_NOTE_4 = new VoteNote(LocalDate.of(2015, 5,31), 100007, 100002);
    public static final VoteNote VOTE_NOTE_5 = new VoteNote(LocalDate.of(2015, 5,31), 100010, 100000);
    public static final VoteNote VOTE_NOTE_6 = new VoteNote(LocalDate.of(2015, 5,31), 100010, 100001);

    public static final List<VoteNote> NOTES = Arrays.asList(VOTE_NOTE_1,
            VOTE_NOTE_2,
            VOTE_NOTE_3,
            VOTE_NOTE_5,
            VOTE_NOTE_6,
            VOTE_NOTE_4);

    public static void assertMatch(VoteNote actual, VoteNote expected) {

        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<VoteNote> actual, VoteNote... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<VoteNote> actual, Iterable<VoteNote> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }

    public static VoteNote getCreated() {
        return new VoteNote(LocalDate.now(), 100010, 100000);
    }

    public static VoteNote getUpdated() {
        return new VoteNote(LocalDate.of(2015, 5,30), 100010, 100000);
    }

}

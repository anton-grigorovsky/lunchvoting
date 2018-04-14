package ru.topjava.lunchvote.repository;

import ru.topjava.lunchvote.model.VoteNote;

import java.util.List;

/**
 * Created by Антон on 28.03.2018.
 */
public interface VoteNoteRepository {

    VoteNote save(VoteNote note, int userId);

    List<VoteNote> getAll();


}

package ru.topjava.lunchvote.service;

import ru.topjava.lunchvote.model.VoteNote;

import java.util.List;

/**
 * Created by Антон on 28.03.2018.
 */
public interface VoteNoteService {

    VoteNote save(VoteNote note);

    List<VoteNote> getAll();
}

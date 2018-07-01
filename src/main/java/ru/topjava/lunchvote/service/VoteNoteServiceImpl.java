package ru.topjava.lunchvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.topjava.lunchvote.model.VoteNote;
import ru.topjava.lunchvote.repository.VoteNoteRepository;

import java.util.List;

/**
 * Created by Антон on 01.07.2018.
 */
@Service
public class VoteNoteServiceImpl implements VoteNoteService{

    @Autowired
    VoteNoteRepository repository;

    @Override
    public VoteNote save(VoteNote note) {
        return repository.save(note);
    }

    @Override
    public List<VoteNote> getAll() {
        return repository.getAll();
    }
}

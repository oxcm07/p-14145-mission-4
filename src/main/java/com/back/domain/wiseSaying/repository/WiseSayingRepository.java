package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WiseSayingRepository {
    private final List<WiseSaying> wiseSayings = new ArrayList<>();
    private int index = 1;

    public WiseSaying save(WiseSaying wiseSaying) {
        if (wiseSaying.isNew()) {
            wiseSaying.setId(index++);
            wiseSayings.add(wiseSaying);
        }

        return wiseSaying;
    }

    public List<WiseSaying> findForList(int pageSize, int pageNo) {
        return wiseSayings
                .reversed()
                .stream()
                .skip((pageNo - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public int findIndexById(int id) {
        return IntStream
                .range(0, wiseSayings.size())
                .filter(i -> wiseSayings.get(i).getId() == id)
                .findFirst()
                .orElse(-1);
    }

    public WiseSaying findById(int id) {
        int idx = findIndexById(id);

        if (idx == -1) return null;

        return wiseSayings.get(idx);
    }

    public void delete(WiseSaying wiseSaying) {
        wiseSayings.remove(wiseSaying);
    }

    public List<WiseSaying> findForListByContentContaining(String keyword, int pageSize, int pageNo) {
        return wiseSayings
                .reversed()
                .stream()
                .filter(
                        w -> w.getContent().contains(keyword)
                )
                .skip((pageNo - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public List<WiseSaying> findForListByAuthorContaining(String keyword, int pageSize, int pageNo) {
        return wiseSayings
                .reversed()
                .stream()
                .filter(
                        w -> w.getAuthor().contains(keyword)
                )
                .skip((pageNo - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public List<WiseSaying> findForListByContentContainingOrAuthorContaining(String keyword1, String keyword2, int pageSize, int pageNo) {
        return wiseSayings
                .reversed()
                .stream()
                .filter(
                        w -> w.getContent().contains(keyword1) || w.getAuthor().contains(keyword2)
                )
                .skip((pageNo - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }
}
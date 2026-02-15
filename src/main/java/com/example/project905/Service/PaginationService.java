package com.example.project905.Service;

import com.example.project905.Config.CustomException;
import com.example.project905.Service.MassegeHandler.bandlMassegeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaginationService {

    private final bandlMassegeService messageService;

    public Pageable getPageable(int pageNumber, int pageSize) {

        if (pageNumber < 1) {
            throw new CustomException(
                    messageService.getMassegeEn("invalid.page"),
                    messageService.getMassegeAr("invalid.page")
            );
        }

        if (pageSize < 1) {
            throw new CustomException(
                    messageService.getMassegeEn("invalid.size"),
                    messageService.getMassegeAr("invalid.size")
            );
        }

        return PageRequest.of(pageNumber - 1, pageSize, Sort.by("id").ascending());
    }
}

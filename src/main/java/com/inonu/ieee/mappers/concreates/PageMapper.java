package com.inonu.ieee.mappers.concreates;

import com.inonu.ieee.dtos.page.CreatePageDto;
import com.inonu.ieee.dtos.page.PageDto;
import com.inonu.ieee.mappers.abstracts.IPageMapper;
import com.inonu.ieee.model.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PageMapper implements IPageMapper {
    @Override
    public Page createDtoToPage(CreatePageDto dto) {
        Page page = new Page();
        page.setTitle(dto.getTitle());
        page.setContent(dto.getContent());
        page.setImagePath(dto.getImagePath());
        page.setStatus(dto.getStatus());

        return page;
    }

    @Override
    public List<PageDto> pageListToDtoList(List<Page> pages) {
        List<PageDto> pageDtoList = new ArrayList<>();

        for (Page page : pages) {
            PageDto pageDto = new PageDto();
            pageDto.setTitle(page.getTitle());
            pageDto.setContent(page.getContent());
            pageDto.setImagePath(page.getImagePath());
            pageDto.setId(page.getId());

            pageDtoList.add(pageDto);
        }

        return pageDtoList;
    }

    @Override
    public Page pageDtoToPage(PageDto dto) {
        Page page = new Page();
        page.setTitle(dto.getTitle());
        page.setContent(dto.getContent());
        page.setImagePath(dto.getImagePath());
        page.setId(dto.getId());

        return page;
    }

    @Override
    public PageDto pageToPageDto(Page page) {
        PageDto dto = new PageDto();
        dto.setTitle(page.getTitle());
        dto.setContent(page.getContent());
        dto.setImagePath(page.getImagePath());
        dto.setId(page.getId());

        return dto;
    }
}

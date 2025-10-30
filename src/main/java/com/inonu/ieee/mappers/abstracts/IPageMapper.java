package com.inonu.ieee.mappers.abstracts;

import com.inonu.ieee.dtos.page.CreatePageDto;
import com.inonu.ieee.dtos.page.PageDto;
import com.inonu.ieee.model.Page;

import java.util.List;

public interface IPageMapper {
    public Page createDtoToPage(CreatePageDto dto);
    public List<PageDto> pageListToDtoList(List<Page> pages);
    public Page pageDtoToPage(PageDto dto);
    public PageDto pageToPageDto(Page page);

}

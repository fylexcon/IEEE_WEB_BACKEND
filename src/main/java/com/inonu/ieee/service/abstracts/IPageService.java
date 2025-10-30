package com.inonu.ieee.service.abstracts;

import com.inonu.ieee.dtos.page.CreatePageDto;
import com.inonu.ieee.dtos.page.PageDto;

import java.util.List;
import java.util.UUID;

public interface IPageService {
    public List<PageDto> getPages();
    public PageDto createPages(CreatePageDto dto);
    public PageDto getPageById(UUID id);
    public PageDto updatePage(UUID id, CreatePageDto dto);
    public void deletePage(UUID id);
}

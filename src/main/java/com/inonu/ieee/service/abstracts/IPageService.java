package com.inonu.ieee.service.abstracts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inonu.ieee.dtos.page.CreatePageDto;
import com.inonu.ieee.dtos.page.PageDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface IPageService {
    public List<PageDto> getPages();
    public PageDto createPage(CreatePageDto dto, MultipartFile image);
    public PageDto getPageById(UUID id);
    public PageDto updatePage(UUID id, CreatePageDto dto, MultipartFile image);
    public void deletePage(UUID id);
}

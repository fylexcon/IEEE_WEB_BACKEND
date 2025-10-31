package com.inonu.ieee.service.concreates;

import com.inonu.ieee.dtos.page.CreatePageDto;
import com.inonu.ieee.dtos.page.PageDto;
import com.inonu.ieee.exceptions.EntityNotFoundException;
import com.inonu.ieee.mappers.abstracts.IPageMapper;
import com.inonu.ieee.model.Page;
import com.inonu.ieee.repository.PageRepository;
import com.inonu.ieee.service.abstracts.IPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PageManager implements IPageService {

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private IPageMapper pageMapper;

    @Override
    public List<PageDto> getPages() {
        List<Page> pages = pageRepository.findAll();
        List<PageDto> pageDtos = new ArrayList<>();

        for (Page page : pages)
        {
            PageDto pageDto = pageMapper.pageToPageDto(page);
            pageDtos.add(pageDto);
        }

        return pageDtos;
    }

    @Override
    public PageDto createPage(CreatePageDto dto) {

        Page page = pageRepository.save(pageMapper.createDtoToPage(dto));
        return pageMapper.pageToPageDto(page);
    }

    @Override
    public PageDto getPageById(UUID id) {
        Optional<Page> optional = pageRepository.findById(id);

        if (optional.isEmpty())
            return null;

        return pageMapper.pageToPageDto(optional.get());
    }

    @Override
    public PageDto updatePage(UUID id, CreatePageDto dto) {
        Optional<Page> optional = pageRepository.findById(id);

        if (optional.isEmpty())
            throw new EntityNotFoundException("Page not found.");

        Page page = new Page();
        page.setId(id);
        page.setTitle(dto.getTitle());
        page.setContent(dto.getContent());
        page.setStatus(dto.getStatus());
        page.setImage(dto.getImage());
        page = pageRepository.save(page);

        return pageMapper.pageToPageDto(page);
    }

    @Override
    public void deletePage(UUID id) {
        Optional<Page> optional = pageRepository.findById(id);

        if (optional.isEmpty())
            throw new EntityNotFoundException("Page not found.");
        else
            pageRepository.deleteById(id);
    }
}

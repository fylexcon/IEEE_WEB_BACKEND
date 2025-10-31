package com.inonu.ieee.service.concreates;

import com.inonu.ieee.dtos.page.CreatePageDto;
import com.inonu.ieee.dtos.page.PageDto;
import com.inonu.ieee.exceptions.EntityNotFoundException;
import com.inonu.ieee.mappers.abstracts.IPageMapper;
import com.inonu.ieee.model.Page;
import com.inonu.ieee.repository.PageRepository;
import com.inonu.ieee.service.abstracts.IEventService;
import com.inonu.ieee.service.abstracts.IImageService;
import com.inonu.ieee.service.abstracts.IPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @Autowired
    private IEventService eventService;
    @Autowired
    private IImageService imageService;

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
    public PageDto createPage(CreatePageDto dto, MultipartFile image) {

        String imageName = imageService.uploadImage(image);

        String url = null;
        if (imageName != null && !imageName.isEmpty()) {
            url = ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/images/").path(imageName).toUriString();
        }

        dto.setImage(url);

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
    public PageDto updatePage(UUID id, CreatePageDto dto, MultipartFile image) {
        Optional<Page> optional = pageRepository.findById(id);

        if (optional.isEmpty())
            throw new EntityNotFoundException("Page not found.");

        Page page = optional.get();

        if(!image.isEmpty() && image != null)
        {
            imageService.deleteImage(page.getImage());
            String imageName = imageService.uploadImage(image);
            page.setImage(imageName);
        }

        page.setTitle(dto.getTitle());
        page.setContent(dto.getContent());
        page.setStatus(dto.getStatus());
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

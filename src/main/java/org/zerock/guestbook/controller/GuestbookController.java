package org.zerock.guestbook.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.guestbook.dto.GuestbookDTO;
import org.zerock.guestbook.dto.PageRequestDTO;
import org.zerock.guestbook.service.GuestbookService;

@Controller
@RequestMapping("/guestbook")
@Log4j2
@RequiredArgsConstructor
public class GuestbookController {

    private final GuestbookService service;
    private long gno;

    @GetMapping("/")
    public String index(){
        return "redirect:/guestbook/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        log.info("list.................." + pageRequestDTO);

        model.addAttribute("result", service.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void register(){
        log.info("register get...");
    }

    @PostMapping("/register")
    public String registerPost(GuestbookDTO dto, RedirectAttributes redirectAttributes){
        log.info("dto... "+dto);

        Long gno = service.register(dto);

        redirectAttributes.addFlashAttribute("msg", gno);

        return "redirect:/guestbook/list";
    }

    @GetMapping({"/read", "/modify"})
        public void read(Long gno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model){
        log.info("gno: "+gno);

            GuestbookDTO dto = service.read(gno);

            model.addAttribute("dto", dto);
    }

    @PostMapping("/remove")
    public String remove(long gno, RedirectAttributes redirectAttributes){
        log.info("gno: "+gno);
        service.remove(gno);
        redirectAttributes.addFlashAttribute("msg", gno);

        return "redirect:/guestbook/list";
    }

    @PostMapping("/modify")
    public String modify(GuestbookDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes){
        log.info("post modify...........................................");
        log.info("dto: "+dto);

        service.modify(dto);
        System.out.println(dto.getGno()+"111dasdasdasdasdasdasdasdsadasdsad");
        redirectAttributes.addFlashAttribute("page", requestDTO.getPage());
        redirectAttributes.addFlashAttribute("gno", dto.getGno());

        return "redirect:/guestbook/read";
    }
}

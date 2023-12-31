package com.sskings.webapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sskings.webapi.models.Item;
import com.sskings.webapi.repositories.ItemRepository;
import com.sskings.webapi.services.ItemService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/itens")
public class ItemController {
	
	private final ItemService itemService;
	private final String ITEM_URI = "itens/";

	private ItemController(ItemService itemService) {
		this.itemService = itemService;
	}
	
	@GetMapping("/")
	public ModelAndView list() {
		Iterable<Item> itens = this.itemService.findAll();
		return new ModelAndView(ITEM_URI + "list", "itens", itens);
		
	}
	
	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Item item) {		
		return new ModelAndView(ITEM_URI + "view", "item", item);
	}
	
	@GetMapping("/novo")
	public String createForm(@ModelAttribute Item item) {
		return ITEM_URI + "form";
	}
	
	@PostMapping("/form")
	public ModelAndView create(@Valid Item item, BindingResult result, RedirectAttributes redirect) {
		if(result.hasErrors()) {
			return new ModelAndView(ITEM_URI + "form","formErrors",result.getAllErrors());
		}
		itemService.save(item);
		redirect.addFlashAttribute("globalMessage","Item gravado com sucesso.");
		return new ModelAndView("redirect:/" + ITEM_URI + "{item.id}", "item.id", item.getId());
	}
	
	@GetMapping("alterar/{id}")
	public ModelAndView alterarForm(@PathVariable("id") Item item){
		return new ModelAndView(ITEM_URI + "form", "item", item );
	}
	
	@GetMapping("remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id, RedirectAttributes redirect) {
		this.itemService.deleteById(id);
		Iterable<Item> itens = this.itemService.findAll();
		return new ModelAndView(ITEM_URI + "list", "itens", itens)
				.addObject("globalMessage", "Item removido com sucesso.");
	}
				
				
	
}

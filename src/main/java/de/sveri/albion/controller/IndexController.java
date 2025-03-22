package de.sveri.albion.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import de.sveri.albion.db.AlbionMetadataImporter;

@Controller
public class IndexController {

	@Autowired
	AlbionMetadataImporter albionMetadataImporter;

	@GetMapping
	public String index() {
		return "index";
	}

	@PostMapping("/importMetaData")
	public RedirectView importMetaData() throws IOException {
		albionMetadataImporter.importMetaData();
		return new RedirectView("/");
	}

}

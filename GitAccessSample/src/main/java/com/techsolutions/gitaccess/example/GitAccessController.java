package com.techsolutions.gitaccess.example;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techsolutions.gitaccess.example.dto.RepoData;
import com.techsolutions.gitaccess.example.dto.UserDto;

@Controller
public class GitAccessController {

	@GetMapping("/git")
	public String gitGet(Model model, @RequestParam(value = "formSubmitted", required = false) boolean formSubmitted, @RequestParam(value = "user", required = false) String user) {
		model.addAttribute("formSubmitted", formSubmitted);
		//if(formSubmitted) {
			model.addAttribute("repoData", getRepoData(user));
		//}
		model.addAttribute("user", new UserDto());
		return "git";
	}

	@PostMapping("/git")
	public String gitPost(@Valid UserDto user, BindingResult result, Model model) {
		if (user.getUserName() == null || user.getUserName().isEmpty()) {
			result.rejectValue("userName", "required", "User Name cannot be empty.");
			return "git";
		}
		model.addAttribute("formSubmitted", true);
		model.addAttribute("user", user.getUserName());
		return "redirect:git";
	}
	
	/*public static void main(String[] args) throws IOException {
		final String user = "lagisettitsh";
		final String format = "{0}) {1}- created on {2}";
		int count = 1;
		RepositoryService service = new RepositoryService();
		for (Repository repo : service.getRepositories(user)) {
			System.out.println(repo.getHtmlUrl() + " " + repo.getName());
			System.out.println(MessageFormat.format(format, count++, repo.getName(), repo.getCreatedAt()));
		}
		List<RepoData> data = getRepoData(user);
		for (RepoData repoData : data) {
			System.out.println(repoData.getRepoName());
			System.out.println(repoData.getUrl());
		}
	}*/

	//@ModelAttribute("repoData")
	public static List<RepoData> getRepoData(String user) {
		if(user == null || user.isEmpty()) {
			return null;
		}
		List<RepoData>  data = new ArrayList<>();
		RepositoryService service = new RepositoryService();
		try {
			for (Repository repository : service.getRepositories(user)) {
				RepoData repo = new RepoData();
				repo.setUrl(repository.getHtmlUrl());
				repo.setRepoName(repository.getName());
				data.add(repo);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data.isEmpty() ? null : data;
	}
}

package controllers;

import java.util.List;

import models.World;

import org.springframework.beans.factory.annotation.Autowired;

import play.Logger;
import play.Logger.ALogger;
import play.mvc.Controller;
import play.mvc.Result;
import services.GalaxyService;
import services.UserService;
import views.html.index;
import views.html.login;

@org.springframework.stereotype.Controller
public class Application extends Controller {

	ALogger log = Logger.of(Application.class);

	@Autowired
	GalaxyService galaxyService;

	@Autowired
	UserService userService;

	public Result index() {
		if (galaxyService.getNumberOfWorlds() == 0) {
			galaxyService.makeSomeWorldsAndRelations();
		}

		List<World> worlds = galaxyService.getAllWorlds();
		World first = worlds.get(0);
		World last = worlds.get(worlds.size() - 1);
		List<World> pathWorlds = galaxyService.getWorldPath(first, last);

		log.info("toto");

		if (userService.getNumberOfUsers() == 0) {
			userService.makeSomeUsers();
		}

		return ok(index.render("Your new application is ready."));
	}

	public Result login() {
		return ok(login.render());
	}
}

package org.user.app.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersionController {
	//1)- Using url mapping
	@GetMapping("/personV1")
	public PersonV1 personV1() {
		return new PersonV1("Nanu");
	}
	
	@GetMapping("/personV2")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Ranu", "Molda"));
	}
	
	//2)- Using query parameter
	@GetMapping(value="/person", params="version=1" )
	public PersonV1 personV3() {
		return new PersonV1("Nanu");
	}
	
	@GetMapping(value="/person", params="version=2")
	public PersonV2 personV4() {
		return new PersonV2(new Name("Ranu", "Molda"));
	}
	
	//3)-Using header
	@GetMapping(value="/person", headers="API-X-VERSION=1" )
	public PersonV1 personV5() {
		return new PersonV1("Nanu");
	}
	
	@GetMapping(value="/person", headers="API-X-VERSION=2")
	public PersonV2 personV6() {
		return new PersonV2(new Name("Ranu", "Molda"));
	}
	
	//4)- Using produces
	@GetMapping(value="/person", produces="application/app-v1+json" )
	public PersonV1 personV7() {
		return new PersonV1("Nanu");
	}
	
	@GetMapping(value="/person", produces="application/app-v2+json")
	public PersonV2 personV8() {
		return new PersonV2(new Name("Ranu", "Molda"));
	}
}

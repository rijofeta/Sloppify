package Sloppify.Search;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "search")
@AllArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public String search(@RequestParam("text") String text) {
        return searchService.search(text);
    }
}

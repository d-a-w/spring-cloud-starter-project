package it.dawit.catalog.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import it.dawit.catalog.domain.Catalog;
import it.dawit.catalog.domain.ResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CatalogFallbackController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/catalog/{id}", method = RequestMethod.GET, produces = "application/json")
    @HystrixCommand(fallbackMethod = "catalogFallback")
    public ResponseWrapper<Catalog> getCatalog(@PathVariable int id) {

        if(id < 10){
            throw new RuntimeException("Simulating downstream system failure");
        }
        log.info("Running original getCatalog");
        return new ResponseWrapper<>(new Catalog(id,"demo catalog"), "Response from original method." );
    }

    public ResponseWrapper<Catalog> catalogFallback(int id, Throwable t) {
        return new ResponseWrapper<>(null, "Fallback method for id " + id + ". Original exception: " + t.toString());
    }

}
package holosen.shop.app.controllers.api.site;

import holosen.shop.app.entities.site.Blog;
import holosen.shop.app.entities.site.Nav;
import holosen.shop.app.enums.RespoonseStatus;
import holosen.shop.app.helper.ui.ServiceRespons;
import holosen.shop.app.services.site.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class BlogController {

    @Autowired
    private BlogService service;

    @GetMapping("")
    public ServiceRespons<Blog> search(@RequestParam String keyWord) {
        try {
            List<Blog> result = service.search(keyWord);
            return new ServiceRespons<Blog>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Blog>(e);
        }
    }

    @GetMapping("/getAll")
    public ServiceRespons<Blog> get(@RequestParam Integer pageSize, @RequestParam Integer pageNumber) {
        try {
            List<Blog> result = service.getAll(pageSize, pageNumber);
            long totalCount = service.getAllCount();
            return new ServiceRespons<Blog>(RespoonseStatus.SUCCESS, result, totalCount);
        } catch (Exception e) {
            return new ServiceRespons<Blog>(e);
        }
    }
    @GetMapping("/getAllData")
    public ServiceRespons<Blog> getall(@RequestParam Integer pageSize, @RequestParam Integer pageNumber) {
        try {
            List<Blog> result = service.getAlldata(pageSize, pageNumber);
            long totalCount = service.getAllCountdata();
            return new ServiceRespons<Blog>(RespoonseStatus.SUCCESS, result, totalCount);
        } catch (Exception e) {
            return new ServiceRespons<Blog>(e);
        }
    }
    @GetMapping("/info/{id}")
    public ServiceRespons<Blog> bloginfo(@PathVariable long id) {
        try {
            Blog result = service.getById(id);
            return new ServiceRespons<Blog>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Blog>(e);
        }
    }

    @PostMapping("/")
    public ServiceRespons<Blog> add(@RequestBody Blog data) {
        try {
            Blog result = service.add(data);
            return new ServiceRespons<Blog>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Blog>(e);
        }
    }
    @PutMapping("/")
    public ServiceRespons<Blog> update(@RequestBody Blog data) {
        try {
            Blog result = service.update(data);
            return new ServiceRespons<Blog>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Blog>(e);
        }
    }
    @DeleteMapping("/{id}")
    public ServiceRespons<Boolean> delete(@PathVariable long id) {
        try {
            boolean result = service.delete(id);
            return new ServiceRespons<Boolean>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Boolean>(e);
        }
    }
    @PutMapping("/increaseVisitCount/{id}")
    public ServiceRespons<Blog> increadVisitCount(@PathVariable long id) {
        try {
            Blog result = service.increaseVisitCount(id);
            return new ServiceRespons<Blog>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Blog>(e);
        }
    }

}

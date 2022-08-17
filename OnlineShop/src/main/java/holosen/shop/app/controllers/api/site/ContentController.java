package holosen.shop.app.controllers.api.site;

import holosen.shop.app.entities.site.Content;
import holosen.shop.app.enums.RespoonseStatus;
import holosen.shop.app.helper.ui.ServiceRespons;
import holosen.shop.app.services.site.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Content")
public class ContentController {

    @Autowired
    private ContentService service;

    @GetMapping("/getAll")
    public ServiceRespons<Content> getAll(@RequestParam Integer pageSize,@RequestParam Integer pageNumber) {
        try {
            List<Content> result = service.getAll(pageSize,pageNumber);
            long totalCount = service.getAllCount();
            return new ServiceRespons<Content>(RespoonseStatus.SUCCESS, result,totalCount);
        } catch (Exception e) {
            return new ServiceRespons<Content>(e);
        }
    }
    @GetMapping("/getAllData")
    public ServiceRespons<Content> getAllData() {
        try {
            List<Content> result = service.getAllData();
            return new ServiceRespons<Content>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Content>(e);
        }
    }

    @GetMapping("")
    public ServiceRespons<Content> search(@RequestParam String keyWord) {
        try {
            List<Content> result = service.findByKey(keyWord);
            return new ServiceRespons<Content>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Content>(e);
        }
    }


    @GetMapping("/{id}")
    public ServiceRespons<Content> search(@PathVariable long id) {
        try {
            Content result = service.getById(id);
            return new ServiceRespons<Content>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Content>(e);
        }
    }

    @PostMapping("/")
    public ServiceRespons<Content> add(@RequestBody Content data) {
        try {
            Content result = service.add(data);
            return new ServiceRespons<Content>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Content>(e);
        }
    }
    @PutMapping("/")
    public ServiceRespons<Content> update(@RequestBody Content data) {
        try {
            Content result = service.update(data);
            return new ServiceRespons<Content>(RespoonseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceRespons<Content>(e);
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

}

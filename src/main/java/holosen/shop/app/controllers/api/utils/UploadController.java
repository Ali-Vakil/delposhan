package holosen.shop.app.controllers.api.utils;

import holosen.shop.app.enums.RespoonseStatus;
import holosen.shop.app.helper.ui.ServiceRespons;
import holosen.shop.app.helper.uiModels.people.UploadForm;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/utils/upload")
public class UploadController {
    private static String UPLOAD_DIR = System.getProperty("user.home") + "/files";

    @PostMapping("/uploadMultiFiles")
    public ResponseEntity<?> uploadFileMulti(@ModelAttribute UploadForm form) throws Exception {

        System.out.println("Description:" + form.getDescription());

        String result = null;
        try {

            result = this.saveUploadedFiles(form.getFiles());

        }
        // Here Catch IOException only.
        // Other Exceptions catch by RestGlobalExceptionHandler class.
        catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    // Save Files
    private String saveUploadedFiles(MultipartFile file) throws IOException {

        // Make sure directory exists!
        File uploadDir = new File(UPLOAD_DIR);
        uploadDir.mkdirs();

        StringBuilder sb = new StringBuilder();


            String uploadFilePath = UPLOAD_DIR + "/" + file.getOriginalFilename();

            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadFilePath);
            Files.write(path, bytes);

            sb.append(file.getOriginalFilename());

        return sb.toString();
    }

    @GetMapping("/getAllFiles")
    public ServiceRespons<String> getListFiles() {
        File uploadDir = new File(UPLOAD_DIR);

        File[] files = uploadDir.listFiles();

        List<String> list = new ArrayList<String>();
        for (File file : files) {
            list.add(file.getName());
        }
        return new  ServiceRespons(RespoonseStatus.SUCCESS,list);
    }

    // @filename: abc.zip,..
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws MalformedURLException {
        File file = null;
        if(filename.toLowerCase().startsWith(UPLOAD_DIR.toLowerCase())){
            file= new File(filename);
        }else{
            file = new File(UPLOAD_DIR + "/" + filename);
        }
        if (!file.exists()) {
            throw new RuntimeException("File not found");
        }
        Resource resource = new UrlResource(file.toURI());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(resource);
    }
}

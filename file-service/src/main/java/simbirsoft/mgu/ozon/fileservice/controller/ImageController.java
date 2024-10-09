package simbirsoft.mgu.ozon.fileservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import simbirsoft.mgu.ozon.fileservice.dto.FileResponseDto;
import simbirsoft.mgu.ozon.fileservice.services.ImageService;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<FileResponseDto> upload(MultipartFile file) {
        return ResponseEntity.ok(imageService.upload(file));
    }

    @GetMapping("/{_id}")
    public ResponseEntity<Resource> download(@PathVariable("_id") String _id) {
        return ResponseEntity.ok(imageService.download(_id));
    }

    @DeleteMapping("/{_id}")
    public ResponseEntity<String> delete(@PathVariable("_id") String _id) {
        return ResponseEntity.ok(imageService.delete(_id));
    }

}
